package com.blog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blog.mapper.TagMapper;
import com.blog.model.ArticleTagRltn;
import com.blog.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.common.PageJson;
import com.blog.mapper.ArticleMapper;
import com.blog.model.Article;
import com.blog.service.IArticleService;
/**
 * service 实现类
 * @description
 * @author dengxiangying
 * @date 2018年9月7日
 */
@Service("articleService")
@CacheConfig(cacheNames="article")
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private ArticleMapper articleMapper ;

	@Autowired
	private TagMapper tagMapper ;
	
	/**
	 * 分页查询
	 */
	@Override
	public PageJson findByPage(Article article, PageJson page) throws Exception{
		
		PageJson pageResult = new PageJson();
		
		List<Article> list=articleMapper.selectByPage(article, page);
		
		if (list!=null && !list.isEmpty()) {
			
			int total =articleMapper.selectCounts(article);
			
			pageResult.setItemsData(list);
			
			int pageNum = total%pageResult.getPageSize()==0?total/pageResult.getPageSize():total/pageResult.getPageSize()+1;
			
			pageResult.setPageNum(pageNum);
			
			pageResult.setPageTotal((long)total);
		}
		
		return pageResult;
	}

	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	//@CacheEvict(key = "#article.id")
	public void insert(Article article) {
		
		//设置更改的时间
		article.setArticleUpdtdt(new Date());
		article.setArticleClick(Long.parseLong("0"));
		articleMapper.insert(article);

		int articleId=articleMapper.selectMaxArticleId();

		//增加标签
		for (Integer tagId:article.getArticleTags()) {

			ArticleTagRltn tagRltn = new ArticleTagRltn();
			tagRltn.setTagId(tagId);
			tagRltn.setArticleId(articleId);
			tagRltn.setUpdtTm(new Date());

			tagMapper.insertArticleTagRltn(tagRltn);
		}
	}

	/**
	 * 根据主键查找
	 */
	@Override
	@Cacheable(key = "#id")
	public Article findById(Integer id) throws Exception{
			
	   return articleMapper.selectByPrimaryKey(id);
	}

	/**
	 * 修改
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	@CachePut(key = "#article.id")
	public Article mdfy(Article article) throws Exception{
		
		//设置更改的时间
		article.setArticleUpdtdt(new Date());
		articleMapper.updateByPrimaryKey(article);

		//删除
		tagMapper.deleteArticleTagRltnByArticleId(article.getId());

		//增加标签
		for (Integer tagId:article.getArticleTags()) {

			ArticleTagRltn tagRltn = new ArticleTagRltn();
			tagRltn.setTagId(tagId);
			tagRltn.setArticleId(article.getId());
			tagRltn.setUpdtTm(new Date());

			tagMapper.insertArticleTagRltn(tagRltn);
		}
		
		return articleMapper.selectByPrimaryKey(article.getId());
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(key = "#id")
	public void remove(Integer id) throws Exception{
		
		articleMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public Article addArticleClick(Article article) throws Exception {
		
		article.setArticleClick(article.getArticleClick()+1);
		
		articleMapper.updateByPrimaryKey(article );
		
		return article;
	}

	@Override
	public List findList() {

		List<Article> list = articleMapper.selectList();

//		for (Article article:list) {
//
//			List<Integer> taglist = articleMapper.selectArticleTagById(article.getId());
//
//			article.setArticleTags(taglist);
//
//		}

		return list;
	}

	@Override
	public String findArticleTpNmByTpId(Integer articleTpId) {
		
		return articleMapper.selectArticleTpNmByTpId(articleTpId);
	}

}

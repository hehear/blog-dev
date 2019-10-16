package com.blog.service.impl;

import com.blog.mapper.ArticleMapper;
import com.blog.mapper.ArticleTypeMapper;
import com.blog.model.Article;
import com.blog.model.ArticleType;
import com.blog.service.IArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
/**
 * service 实现类
 * @description
 * @author dengxiangying
 * @date 2019年1月8日
 */
@Service("articleTypeService")
@CacheConfig(cacheNames="articleType")
public class ArticleTypeServiceImpl implements IArticleTypeService {

	@Autowired
	private ArticleTypeMapper articleTypeMapper ;
	@Autowired
	private ArticleMapper articleMapper ;

	@Override
	public List findArticleCategoriesList() {

		//所有的文章类型
		List<ArticleType> list=(List<ArticleType>)articleTypeMapper.selectAll();
		
		return list;
	}

	@Override
	public List findList(ArticleType articleType) {
		//所有的文章类型
		List<ArticleType> list=(List<ArticleType>)articleTypeMapper.selectList(articleType);

		return list;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void add(ArticleType articleType) {

		articleTypeMapper.insert(articleType);

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updt(ArticleType articleType) {

		articleTypeMapper.updateByPrimaryKey(articleType);
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void remove(Integer id) {

		articleTypeMapper.deleteByPrimaryKey(id);

	}

}

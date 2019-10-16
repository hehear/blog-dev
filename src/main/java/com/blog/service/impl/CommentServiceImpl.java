package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.mapper.ArticleMapper;
import com.blog.mapper.ArticleTypeMapper;
import com.blog.mapper.CommentMapper;
import com.blog.model.Article;
import com.blog.model.ArticleType;
import com.blog.model.Comment;
import com.blog.service.IArticleTypeService;
import com.blog.service.ICommentService;
/**
 * service 实现类
 * @description
 * @author dengxiangying
 * @date 2019年1月8日
 */
@Service("commentService")
@CacheConfig(cacheNames="comment")
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private CommentMapper commentMapper ;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insert(Comment comment) throws Exception {
		long a = System.currentTimeMillis();
		comment.setUserId((int)a);
		
		if(comment.getUserNm()==null || comment.getUserNm()=="") {
			comment.setUserNm("stefanie");
		}
		commentMapper.insert(comment);
	}
	@Override
	@Cacheable(key = "#id")
	public Comment findById(Integer id) throws Exception {
		return commentMapper.selectByPrimaryKey(id);
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Comment mdfy(Comment comment) throws Exception {
		commentMapper.updateByPrimaryKey(comment);
		return commentMapper.selectByPrimaryKey(comment.getId());
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void remove(Integer id) throws Exception {
		commentMapper.deleteByPrimaryKey(id);
	}
	@Override
	public List findList() {
		
		return commentMapper.selectList();
	}
	@Override
	public List findListByArticleId(Integer articleId) {
		
		List<Comment> list=commentMapper.selectListByArticleId(articleId);
		for (Comment comment : list) {
			
			List<Comment> cList=commentMapper.selectListByPid(comment.getUserId());
			
			comment.setCommentList(cList);
		}
		
		return list;
	}
	@Override
	public int findCommentCounts(Integer articleId) {
		return commentMapper.selectCommentCounts(articleId);
	}

	

}

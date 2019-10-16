package com.blog.service;

import java.util.List;

import com.blog.model.Comment;

/**
 * service接口
 * @description
 * @author dengxiangying
 * @date 2019年1月8日
 */
public interface ICommentService {

	
	List findList();
	/**
	 * 新增
	 * @param comment
	 */
	void insert(Comment comment) throws Exception;

	/**
	 * 根据主键查找
	 * @param id
	 * @return
	 */
	Comment findById(Integer id) throws Exception;

	/**
	 * 修改
	 * @param comment
	 * @return 
	 */
	Comment mdfy(Comment comment) throws Exception;

	/**
	 * 删除
	 * @param id
	 * @throws Exception 
	 */
	void remove(Integer id) throws Exception;
	
	/**
	 * 根据文章id获取评论列表
	 * @param articleId
	 * @return
	 */
	List findListByArticleId(Integer articleId);
	
	/**
	 * 获得文章评论条数
	 * @param articleId
	 * @return
	 */
	int findCommentCounts(Integer articleId);
	
}

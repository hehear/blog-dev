package com.blog.service;

import java.util.List;

import com.blog.common.PageJson;
import com.blog.model.Article;

/**
 * service接口
 * @description
 * @author dengxiangying
 * @date 2018年9月10日
 */
public interface IArticleService {

	/**
	 * 分页查询
	 * @param areticle 条件查询条件
	 * @param page  页码
	 * @return
	 */
	PageJson findByPage(Article areticle, PageJson page) throws Exception;

	/**
	 * 新增
	 * @param areticle
	 */
	void insert(Article areticle) throws Exception;

	/**
	 * 根据主键查找
	 * @param id
	 * @return
	 */
	Article findById(Integer id) throws Exception;

	/**
	 * 修改
	 * @param areticle
	 * @return 
	 */
	Article mdfy(Article areticle) throws Exception;

	/**
	 * 删除
	 * @param id
	 * @throws Exception 
	 */
	void remove(Integer id) throws Exception;

	/**
	 * 阅读次数增加
	 * @param art
	 * @return
	 */
	Article addArticleClick(Article art) throws Exception;

	/**
	 * 查询文章list
	 * @return
	 */
	List findList();
	
	/**
	 * 根据文章类型id查找类型名称
	 * @param articleTpId
	 * @return
	 */
	String findArticleTpNmByTpId(Integer articleTpId);

}

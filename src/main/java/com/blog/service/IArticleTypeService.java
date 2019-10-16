package com.blog.service;

import com.blog.model.ArticleType;

import java.util.List;

/**
 * service接口
 * @description
 * @author dengxiangying
 * @date 2019年1月8日
 */
public interface IArticleTypeService {

	
	/**
	 * 查询文章分类列表
	 * @return
	 */
	List findArticleCategoriesList();

	/**
	 * 条件查询文章分类列表
	 * @param articleType
	 * @return
	 */
	List findList(ArticleType articleType);

	/**
	 * 新增文章类型
	 * @param articleType
	 */
    void add(ArticleType articleType);

	/**
	 * 修改文章类型
	 * @param articleType
	 */
	void updt(ArticleType articleType);

	/**
	 * 删除文章类型
	 * @param id
	 */
	void remove(Integer id);
}

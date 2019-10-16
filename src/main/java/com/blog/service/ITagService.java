package com.blog.service;

import com.blog.model.Tag;

import java.util.List;

/**
 * service接口
 * @description
 * @author dengxiangying
 * @date 2019年1月8日
 */
public interface ITagService {

	
	/**
	 * 查询文章分类列表
	 * @return
	 */
	List findArticleTagList();

	/**
	 * 删除
	 * @param id
	 */
    void remove(Integer id);

	/**
	 * 修改
	 * @param
	 */
	void updt(Tag tag);

	/**
	 * 新增
	 * @param
	 */
	void add(Tag tag);

	/**
	 * 条件查询
	 * @param
	 * @return
	 */
	List findList(Tag tag);
}

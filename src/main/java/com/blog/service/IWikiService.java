package com.blog.service;

import com.blog.model.Tag;
import com.blog.model.Wiki;

import java.util.List;

/**
 * service接口
 * @description
 * @author dengxiangying
 * @date 2019年1月8日
 */
public interface IWikiService {


	/**
	 * 删除
	 * @param id
	 */
    void remove(Integer id);

	/**
	 * 修改
	 * @param
	 */
	void updt(Wiki wiki);

	/**
	 * 新增
	 * @param
	 */
	void add(Wiki wiki);

	/**
	 * 条件查询
	 * @param
	 * @return
	 */
	List findList(Wiki wiki);

	/**
	 * 最大wikiid
	 * @return
	 */
	Integer findMaxWikiId();

}

package com.blog.rest;

import java.util.List;

import javax.annotation.Resource;

import com.blog.model.ArticleType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blog.common.CommonRest;
import com.blog.common.PageJson;
import com.blog.common.SimpleMessage;
import com.blog.model.Article;
import com.blog.service.IArticleTypeService;

/**
 * rest
 * @description
 * @author dengxiangying
 * @date 2019年1月8日
 */
@RestController
@RequestMapping("/articleType")
@SuppressWarnings("all")
public class ArticleTypeRest extends CommonRest{
	

	@Resource(name="articleTypeService")
	private IArticleTypeService articleTypeService;
	

	/**
	 * 查询
	 * @return
	 */
	@RequestMapping("/getArticleCategoriesList")
	public SimpleMessage<Object> getArticleCategoriesList(){

		SimpleMessage<Object> sm = new SimpleMessage<>();
		try {

			List list = articleTypeService.findArticleCategoriesList();

			sm.setList(list);

		} catch (Exception e) {
			//封装报错信息
			sm.setMessage(e.getMessage());
			sm.setCode("500");
			return error(e);
		}

		return sm;
	}

	/**
	 * 查询
	 * @return
	 */
	@RequestMapping(value="/list",method = RequestMethod.POST)
	public SimpleMessage<Object> getList(ArticleType articleType){

		SimpleMessage<Object> sm = new SimpleMessage<>();
		try {

			List list = articleTypeService.findList(articleType);

			sm.setList(list);

		} catch (Exception e) {
			//封装报错信息
			sm.setMessage(e.getMessage());
			sm.setCode("500");
			return error(e);
		}

		return sm;
	}

	/**
	 * 保存
	 * @return
	 */
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public SimpleMessage<Object> save(ArticleType articleType){

		SimpleMessage<Object> sm = new SimpleMessage<>();
		try {

			if(articleType.getId()==null){
				articleTypeService.add(articleType);
			}else{
				articleTypeService.updt(articleType);
			}

		} catch (Exception e) {
			//封装报错信息
			sm.setMessage(e.getMessage());
			sm.setCode("500");
			return error(e);
		}

		return sm;
	}

	/**'
	 * 删除
	 * @param
	 * @return
	 */
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public SimpleMessage<Object> delete(Integer id){

		SimpleMessage<Object> sm = new SimpleMessage<>();

		try {

			articleTypeService.remove(id);

		} catch (Exception e) {
			//封装报错信息
			sm.setMessage(e.getMessage());
			sm.setCode("500");
			error(e);
		}
		return sm;
	}
}

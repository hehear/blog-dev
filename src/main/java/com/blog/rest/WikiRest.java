package com.blog.rest;

import com.blog.common.CommonRest;
import com.blog.common.SimpleMessage;
import com.blog.controller.ArticleController;
import com.blog.model.Tag;
import com.blog.model.Wiki;
import com.blog.service.ITagService;
import com.blog.service.IWikiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * rest
 * @description
 * @author dengxiangying
 * @date 2019年6月25日
 */
@RestController
@RequestMapping("/wiki")
@SuppressWarnings("all")
public class WikiRest extends CommonRest{

	private Logger logger= LoggerFactory.getLogger(WikiRest.class);


	@Resource(name="wikiService")
	private IWikiService wikiService;


	/**
	 * 查询
	 * @return
	 */
	@RequestMapping(value="/list",method = RequestMethod.POST)
	public SimpleMessage<Object> getList(Wiki wiki){

		SimpleMessage<Object> sm = new SimpleMessage<>();
		try {

			List list = wikiService.findList(wiki);

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
	@GetMapping("/getWikiId")
	public SimpleMessage<Object> getWikiId(){

		logger.info("getWikiId**********");

		SimpleMessage<Object> sm = new SimpleMessage<>();
		try {

			Integer wikiId = wikiService.findMaxWikiId();

			sm.setRecord(wikiId);

		} catch (Exception e) {
			//封装报错信息
			sm.setMessage(e.getMessage());
			sm.setCode("500");
			return error(e);
		}

		return sm;
	}

	/**
	 * 新增
	 * @return
	 */
	@RequestMapping(value="/wikiAdd",method = RequestMethod.POST)
	public SimpleMessage<Object> add(@RequestBody Wiki wiki){

		SimpleMessage<Object> sm = new SimpleMessage<>();

		try {
			wiki.setUpdtTm(new Timestamp(System.currentTimeMillis()));

			//保存入库
			wikiService.add(wiki);

		} catch (Exception e) {
			//封装报错信息
			sm.setMessage(e.getMessage());
			sm.setCode("500");
			return error(e);
		}
		return sm;
	}

	/**
	 * 修改
	 * @return
	 */
	@RequestMapping(value="/wikiUpdate",method = RequestMethod.POST)
	public SimpleMessage<Object> update(@RequestBody Wiki wiki){

		SimpleMessage<Object> sm = new SimpleMessage<>();

		try {

			wikiService.updt(wiki);

			sm.setRecord(wiki);


		} catch (Exception e) {
			//封装报错信息
			sm.setMessage(e.getMessage());
			sm.setCode("500");
			error(e);
		}
		return sm;
	}

	/**
	 * 删除
	 * @param
	 * @return
	 */
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public SimpleMessage<Object> delete(Integer id){

		SimpleMessage<Object> sm = new SimpleMessage<>();

		try {

			wikiService.remove(id);

		} catch (Exception e) {
			//封装报错信息
			sm.setMessage(e.getMessage());
			sm.setCode("500");
			error(e);
		}
		return sm;
	}

}

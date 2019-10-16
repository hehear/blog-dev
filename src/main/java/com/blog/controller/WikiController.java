package com.blog.controller;

import com.blog.model.Wiki;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * 文章wiki标签controller
 * @author dengxiangying
 * @date  2019-09-12
 * @version V2.0
 */
@RestController
@RequestMapping("/wiki")
public class WikiController {
	 

	private Logger logger=LoggerFactory.getLogger(WikiController.class);


	@RequestMapping("/")
	public ModelAndView index(){

		logger.info("访问wiki界面{}",new Date());

		return new ModelAndView("wiki/index");
	}

	@RequestMapping("/add")
	public ModelAndView add(){

		logger.info("访问wiki add界面{}",new Date());

		return new ModelAndView("wiki/add");
	}

	@RequestMapping("/update")
	public ModelAndView update(Wiki wiki){

		logger.info("访问wiki update界面{}",new Date());

		return new ModelAndView("wiki/update").addObject("id",wiki.getId()).addObject("wikiId",wiki.getWikiId())
				.addObject("wikiPid",wiki.getWikiPid()).addObject("wikiName",wiki.getWikiName())
				.addObject("wikiContent",wiki.getWikiContent()).addObject("wikiContentMd",wiki.getWikiContentMd());
	}


}

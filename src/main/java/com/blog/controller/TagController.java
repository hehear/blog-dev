package com.blog.controller;

import com.blog.model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * 文章标签controller
 * @author dengxiangying
 * @date  2019-07-04
 * @version V2.0
 */
@RestController
@RequestMapping("/tag")
public class TagController {
	 

	private Logger logger=LoggerFactory.getLogger(TagController.class);


	@RequestMapping("/")
	public ModelAndView index(){

		logger.info("访问tag界面{}",new Date());

		return new ModelAndView("articleTag/index");
	}



}

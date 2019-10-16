package com.blog.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 首页controller
 * @author dengxiangying
 * @date  2019年1月9日
 * @version V1.12.0
 */
@RestController
@RequestMapping("/")  
public class IndexController {  
	 

	private Logger logger=LoggerFactory.getLogger(IndexController.class); 


	@RequestMapping("/")
	public ModelAndView index(){
		 
		logger.info("访问首页界面{}",new Date());

		return new ModelAndView("index/index");
	}
	 
	@RequestMapping("/login.do")
	public ModelAndView login(){
		 
		logger.info("访问登陆界面{}",new Date());

		return new ModelAndView("login/login");
	}



 

}

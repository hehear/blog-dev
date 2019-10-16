package com.blog.controller;

import com.blog.model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * 文章类型controller
 * @author dengxiangying
 * @date  2019-06-22
 * @version V2.0
 */
@RestController
@RequestMapping("/articleType")
public class ArticleTypeController {
	 

	private Logger logger=LoggerFactory.getLogger(ArticleTypeController.class);


	@RequestMapping("/")
	public ModelAndView index(){

		logger.info("访问articleType界面{}",new Date());

		return new ModelAndView("articleType/index");
	}


	@RequestMapping("/update")
	public ModelAndView update(Article article){
		 
		logger.info("访问articleTypeUpdate界面{}",new Date());
		
		System.out.println(article.getArticleName());

		return new ModelAndView("articleType/articleTypeUpdate").addObject("id", article.getId()).addObject("articleName", article.getArticleName())
				.addObject("articleTpId", article.getArticleTpId()).addObject("articleTpNm",article.getArticleTpNm())
				.addObject("articleShort",article.getArticleShort()).addObject("articleContent", article.getArticleContent())
				.addObject("articleClick", article.getArticleClick()).addObject("articleContentMarkdown", article.getArticleContentMarkdown())
				.addObject("articleHtmlName",article.getArticleHtmlName()).addObject("articleComments", article.getArticleComments());
	}


	@RequestMapping("/list")
	public ModelAndView list(){
		
		logger.info("访问articleadd界面{}",new Date());
		
		return new ModelAndView("articleType/articleTypeList");
	}


}

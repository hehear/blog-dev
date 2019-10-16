package com.blog.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.blog.model.Article;

/**
 * 文章controller
 * @author dengxiangying
 * @date  2018年12月4日
 * @version V1.12.0
 */
@RestController
@RequestMapping("/article")  
public class ArticleController {  
	 

	private Logger logger=LoggerFactory.getLogger(ArticleController.class);

	@RequestMapping("/content")
	public ModelAndView index(Article article){
		 
		logger.info("访问articleContent界面{}",new Date());

		return new ModelAndView("article/articleContent").addObject("article", article).addObject("a", "test123");
	}
	
	@RequestMapping("/articleUpdate")
	public ModelAndView update(Article article){
		 
		logger.info("访问articleUpdate界面{}",new Date());
		
		System.out.println(article.getArticleName());

		return new ModelAndView("article/articleUpdate").addObject("id", article.getId()).addObject("articleName", article.getArticleName())
				.addObject("articleTpId", article.getArticleTpId()).addObject("articleTpNm",article.getArticleTpNm())
				.addObject("articleKeywords", article.getArticleKeywords())
				.addObject("articleShort",article.getArticleShort()).addObject("articleContent", article.getArticleContent())
				.addObject("articleClick", article.getArticleClick()).addObject("articleContentMarkdown", article.getArticleContentMarkdown())
				.addObject("articleHtmlName",article.getArticleHtmlName()).addObject("articleComments", article.getArticleComments());
	}
	
	@RequestMapping("/articleAdd")
	public ModelAndView add(){
		
		logger.info("访问articleadd界面{}",new Date());
		
		return new ModelAndView("article/articleAdd");
	}
	
	@RequestMapping("/list")
	public ModelAndView list(){
		
		logger.info("访问articleadd界面{}",new Date());
		
		return new ModelAndView("article/articleList");
	}
	
	@RequestMapping("/records")
	public ModelAndView records(){
		 
		logger.info("访问归档界面{}",new Date());

		return new ModelAndView("article/articleRecords");
	}
	
	@RequestMapping("/categories")
	public ModelAndView categories(){
		
		logger.info("访问分类界面{}",new Date());
		
		return new ModelAndView("article/articleCategories");
	}
	@RequestMapping("/articleCategoriesList")
	public ModelAndView articleCategoriesList(){
		
		logger.info("访问分类明细列表界面{}",new Date());
		
		return new ModelAndView("article/articleCategoriesDetail");
	}

}

package com.blog.rest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blog.common.CommonRest;
import com.blog.common.PageJson;
import com.blog.common.SimpleMessage;
import com.blog.model.Article;
import com.blog.service.IArticleService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.utility.DateUtil;
import io.netty.util.internal.MathUtil;

/**
 * rest
 * @description
 * @author dengxiangying
 * @date 2018年9月10日
 */
@RestController
@RequestMapping("/article")
@SuppressWarnings("all")
public class ArticleRest extends CommonRest{
	

	@Resource(name="articleService")
	private IArticleService articleService;
	
    @Resource
    Configuration cfg;
	
	/**
	 * 查询(分页)
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping("/getByPage")
	public SimpleMessage<Object> getByPage( int pageIndex,Article article){
		 
		SimpleMessage<Object> sm = new SimpleMessage<>();
		try {
			
			PageJson pager = articleService.findByPage( article,new PageJson(pageIndex) );
			
			sm.setPage(pager);
			
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
	@RequestMapping("/getList")
	public SimpleMessage<Object> getList(){
		 
		SimpleMessage<Object> sm = new SimpleMessage<>();
		try {
			
			List list = articleService.findList();
			
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
	 * 根据主键查询
	 * @param areaNo
	 * @return
	 */
	@RequestMapping(value="/getById",method = RequestMethod.POST)
	public SimpleMessage<Object> getById(Integer id ){
		 
		SimpleMessage<Object> sm = new SimpleMessage<>();
		try {
			
			Article article = articleService.findById(id);
			
			sm.setRecord(article);
			
		} catch (Exception e) {
			//封装报错信息
			sm.setMessage(e.getMessage());
			sm.setCode("500");
			return error(e);
		}
		
		return sm;
	}
	/**
	 * 阅读次数+1
	 * @param art
	 * @return
	 */
	@RequestMapping(value="/addArticleClick",method = RequestMethod.POST)
	public SimpleMessage<Object> addArticleClick(Article art){
		
		SimpleMessage<Object> sm = new SimpleMessage<>();
		try {
			
			Article article = articleService.addArticleClick(art);
			
			sm.setRecord(article);
			
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
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public SimpleMessage<Object> add(@RequestBody Article article){
        
    	SimpleMessage<Object> sm = new SimpleMessage<>();
    	
    	try {
    		String htmlName = System.currentTimeMillis( )+"";
    		article.setArticleHtmlName(htmlName);
    		article.setArticleUpdtdt(new Timestamp(System.currentTimeMillis()) );
    		//保存入库
    		articleService.insert(article);

		} catch (Exception e) {
			//封装报错信息
			sm.setMessage(e.getMessage());
			sm.setCode("500");
			return error(e);
		}
        return sm;
    }
    
    
    private Map<String, Object> getTemplateMap(Article article) {
    	
    	Map<String,Object> map = new HashMap<String,Object>();
    	System.out.println("id  *************");
    	System.out.println(article.getArticleTpId());
    	String articleTpNm = articleService.findArticleTpNmByTpId(article.getArticleTpId());
    	System.out.println(articleTpNm);
    	article.setArticleTpNm(articleTpNm);
    	
    	map.put("articleName", article.getArticleName());
		map.put("id", article.getId());
		map.put("articleName", article.getArticleName());
		map.put("articleTpId", article.getArticleTpId());
		map.put("articleTpNm",article.getArticleTpNm());
		map.put("articleShort",article.getArticleShort());
		map.put("articleContent", article.getArticleContent());
		map.put("articleClick", article.getArticleClick());
		map.put("articleUpdtdt", article.getArticleUpdtdt());
		map.put("articleContentMarkdown", article.getArticleContentMarkdown());
		
		return map;
	}

	private void generataHtmlFile(String ftlFilePath,String htmlFileName,Map<String,Object> map){
        try {
            Template temp = cfg.getTemplate(ftlFilePath); 
            //以classpath下面的static目录作为静态页面的存储目录，同时命名生成的静态html文件名称
            String path=this.getClass().getResource("/").toURI().getPath()+"static/"+htmlFileName;
            
            File f = new File(path.substring(path.indexOf("/")));
            
            if(f.exists()) {
            	f.delete();
            }
            
            Writer file = new FileWriter(f);
            temp.process(map, file);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    /**
     * 修改
     * @return
     */
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public SimpleMessage<Object> update(@RequestBody Article article){
        
    	SimpleMessage<Object> sm = new SimpleMessage<>();
    	
    	try {
    		
    		article=articleService.mdfy(article);

    		sm.setRecord(article);
    		

    	} catch (Exception e) {
			//封装报错信息
			sm.setMessage(e.getMessage());
			sm.setCode("500");
			error(e);
		}
        return sm;
    }
    
    /**'
     * 删除
     * @param areaNo
     * @return
     */
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public SimpleMessage<Object> delete(Integer id){
        
    	SimpleMessage<Object> sm = new SimpleMessage<>();
    	
    	try {
    		
    		articleService.remove(id);
    		
    		Article article = articleService.findById(id);
    		
//            String path=this.getClass().getResource("/").toURI().getPath()+"static/"+article.getArticleHtmlName()==null?"":article.getArticleHtmlName();
//            
//            File f = new File(path.substring(path.indexOf("/")));
//            
//            if(f.exists()) {
//            	f.delete();
//            }
    		
		} catch (Exception e) {
			//封装报错信息
			sm.setMessage(e.getMessage());
			sm.setCode("500");
			error(e);
		}
        return sm;
    }

}

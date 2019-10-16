package com.blog.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blog.common.CommonRest;
import com.blog.common.SimpleMessage;
import com.blog.model.Article;
import com.blog.model.Comment;
import com.blog.service.ICommentService;

/**
 * 评论rest
 * @author dengxiangying
 * 
 */
@RestController
@RequestMapping("/comment")
@SuppressWarnings("all")
public class CommentRest extends CommonRest{
	

	@Resource(name="commentService")
	private ICommentService commentService;
	
	/**
	 * 查询
	 * @return
	 */
	@RequestMapping("/getCommentList")
	public SimpleMessage<Object> getList(Integer articleId){
		 
		System.out.println(articleId+"&&&&&&&&");
		SimpleMessage<Object> sm = new SimpleMessage<>();
		try {
			
			List list = commentService.findListByArticleId(articleId);
			
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
	@RequestMapping("/getCommentCounts")
	public SimpleMessage<Object> getCommentCounts(Integer articleId){
		
		System.out.println(articleId+"&&&&&&&&");
		SimpleMessage<Object> sm = new SimpleMessage<>();
		try {
			
			int counts = commentService.findCommentCounts(articleId);
			
			sm.setRecord(counts);
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
			
			Comment comment = commentService.findById(id);
			
			sm.setRecord(comment);
			
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
    public SimpleMessage<Object> add(Comment comment){
        
    	SimpleMessage<Object> sm = new SimpleMessage<>();
    	
    	try {
    		//保存入库
    		commentService.insert(comment);
    		
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
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public SimpleMessage<Object> update(Comment comment){
        
    	SimpleMessage<Object> sm = new SimpleMessage<>();
    	
    	try {
    		
    		comment=commentService.mdfy(comment);

    		sm.setRecord(comment);
    		
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
    		
    		commentService.remove(id);
    		
		} catch (Exception e) {
			//封装报错信息
			sm.setMessage(e.getMessage());
			sm.setCode("500");
			error(e);
		}
        return sm;
    }


	
}

package com.blog.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blog.common.CommonRest;
import com.blog.common.SimpleMessage;
import com.blog.model.Article;
import com.blog.model.Comment;
import com.blog.service.ICommentService;
import com.blog.service.IUserService;

/**
 * 评论rest
 * @author dengxiangying
 * 
 */
@RestController
@RequestMapping("/user")
@SuppressWarnings("all")
public class UserRest extends CommonRest{
	

	@Resource(name="userService")
	private IUserService userService;
	
	@Autowired
	private AuthenticationManager myAuthenticationManager;
	
    /**
     * 登录验证
     * @param userId
     * @return
     */
    @RequestMapping(value="/loginIn",method = RequestMethod.POST)
    public SimpleMessage<Object> loginIn(String username,String password,HttpServletRequest request){
        
    	SimpleMessage<Object> sm = new SimpleMessage<>();
    	
		UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(username, password);
    	
    	try {
    		 System.out.println("******username:"+username);
    		 System.out.println("******password:"+password);
    		
    		 //使用SpringSecurity拦截登陆请求 进行认证和授权            
    		 Authentication authenticate = myAuthenticationManager.authenticate(upat);             
    		 SecurityContextHolder.getContext().setAuthentication(authenticate);            
    		 //使用redis session共享            
    		 HttpSession session = request.getSession();            
    		 session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆

		} catch (Exception e) {
			//封装报错信息
			e.printStackTrace();
			sm.setMessage("用户名或密码错误！");
			sm.setCode("500");
			return sm;
		}
        return sm;
    }
    /**
     * 当前登录人
     * @param userId
     * @return
     */
    @RequestMapping(value="/getCurrtUser",method = RequestMethod.POST)
    public SimpleMessage<Object> getCurrtUser(HttpServletRequest request){
    	
    	SimpleMessage<Object> sm = new SimpleMessage<>();
    	
    	try {
    		
			SecurityContext ctx = SecurityContextHolder.getContext();
		    Authentication auth = ctx.getAuthentication();

		    User user = (User) auth.getPrincipal();    	
		    
		    sm.setRecord(user.getUsername());
    	} catch (Exception e) {
    		//封装报错信息
    		sm.setMessage("用户名或密码错误！");
    		sm.setCode("500");
    		error(e);
    	}
    	return sm;
    }


	
}

package com.blog.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blog.exception.BizException;

/**
 * 公共rest,对异常信息进行统一处理
 * @author dengxiangying
 * @param <T>
 *
 */
public class CommonRest<T> {
	
	//对boolean值返回结果统一定义
	protected String SUCCESS="success";
	protected String ERROR="error";
	
	protected String ERROR_MSG="系统繁忙，请联系管理员";
	
	private Logger logger=LoggerFactory.getLogger(CommonRest.class); 
	
	public SimpleMessage<T> error(Throwable e){
		SimpleMessage<T> sm=new SimpleMessage<T>();
		sm.setCode("500");
		//对应业务异常，只打印为info信息
		if(e instanceof BizException){
			logger.info(e.getMessage()); 
			 
		}else{
			logger.error(e.getMessage(),e); 
		} 
		sm.setMessage(ERROR_MSG);
		 
		return sm;
	}

}

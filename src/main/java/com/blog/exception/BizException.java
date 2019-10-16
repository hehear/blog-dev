package com.blog.exception;

/**
 * 业务异常定义
 * 
 * @author dengxiangying
 * @date 2018-08-08
 *
 */
public class BizException extends RuntimeException{ 
	 
	private static final long serialVersionUID = 1L;

	public BizException(){
		super();
	}
	
	public BizException(String message){
	   super(message);	
	}
	
	public BizException(Throwable err){
		super(err);
	}
	
	public BizException(String message,Throwable err){
		super(message,err);
	}

}

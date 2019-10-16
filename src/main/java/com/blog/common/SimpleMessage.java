package com.blog.common;

import java.util.List;

/**
 * 相应统一返回数据定义
 * @author TL
 *
 */
public class SimpleMessage<T> {
	private String code;
	private String message;
	private List<T> list;
	//分页
	private PageJson page;
	
	private Object record;
	private List<T> records;
	
	public Object getRecord() {
		return record;
	}

	public void setRecord(Object record) {
		this.record = record;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public SimpleMessage(){
		code="200";
		message="success";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public PageJson getPage() {
		return page;
	}

	public void setPage(PageJson page) {
		this.page = page;
	}
	
}

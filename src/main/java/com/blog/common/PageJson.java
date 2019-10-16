package com.blog.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @description page 用于封装分页
 * @author dengxiangying
 * @date 2018年9月5日
 */
@SuppressWarnings("all")
public class PageJson implements Serializable,Cloneable {
	
	
	public static final String SORT_ASC = "ASC";
	public static final String SORT_DESC = "DESC";
	public static final Integer DEFAULT_PAGING_NUM = Integer.valueOf(1);
	public static final Integer DEFAULT_PAGING_SIZE = Integer.valueOf(6);
	public static final Integer DEFAULT_PAGING_INDEX = Integer.valueOf(1);
	
	private Long 	pageTotal   = Long.valueOf(0);//总记录数
	private String 	pageTotalData   = "";//汇总数据
	private Integer pageSize    = DEFAULT_PAGING_SIZE; //每页显示页数
	private Integer pageIndex   = DEFAULT_PAGING_INDEX;  //起始页index
	private Integer pageFirstIndex   = DEFAULT_PAGING_INDEX;  //分页的开始index
	private Integer pageNum     = DEFAULT_PAGING_NUM;    //总共多少页
	private List    itemsData = new ArrayList(0);//页面对象值
	private String  sortField =""; 
	private String  sortType = "ASC";//排序规则
	
	
	public PageJson(){
		
	}
	
	public PageJson(Integer pages){
		
		this.pageIndex=pages;
		this.pageFirstIndex=(pageIndex - 1) * this.pageSize;
	}
	
	
	//提供构造方法
	public Integer getPageSize() {
		return pageSize;
	}
	public Long getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(Long pageTotal) {
		this.pageTotal = pageTotal;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public List getItemsData() {
		return itemsData;
	}
	public void setItemsData(List itemsData) {
		this.itemsData = itemsData;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public String getPageTotalData() {
		return pageTotalData;
	}
	public void setPageTotalData(String pageTotalData) {
		this.pageTotalData = pageTotalData;
	}
	
	public int getFirstIndex() {
		if (pageIndex <= 0) {
			throw new IndexOutOfBoundsException();
		}
		return (pageIndex - 1) * pageSize;
	}
	
	public Integer getPageFirstIndex() {
		return pageFirstIndex;
	}
	public void setPageFirstIndex(Integer pageFirstIndex) {
		this.pageFirstIndex = pageFirstIndex;
	}
	
}

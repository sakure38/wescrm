package com.we.scrm.domain;


public class Department extends AbstractEntity{

	private Integer pid;//部门id
	private Integer parentid;//父id
	private String name;//名称
	private String nameEn;//
	private Integer sort;//排序

	public Integer getPid(){
		return pid;
	}
	public void setPid(Integer pid){
		this.pid = pid;
	}

	public Integer getParentid(){
		return parentid;
	}
	public void setParentid(Integer parentid){
		this.parentid = parentid;
	}

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getNameEn(){
		return nameEn;
	}
	public void setNameEn(String nameEn){
		this.nameEn = nameEn;
	}

	public Integer getSort(){
		return sort;
	}
	public void setSort(Integer sort){
		this.sort = sort;
	}


}

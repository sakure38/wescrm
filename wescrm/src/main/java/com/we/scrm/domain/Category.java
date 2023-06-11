package com.we.scrm.domain;

public class Category extends AbstractEntity{

	private String name;//名称
	private Integer code;//
	private Integer parentCode;//父级别code
	private Integer sort;//排序

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}

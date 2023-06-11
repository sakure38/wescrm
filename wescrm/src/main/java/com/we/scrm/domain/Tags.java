package com.we.scrm.domain;

import com.we.scrm.common.enums.TagsTypeEnum;


public class Tags extends AbstractEntity{

	private String content;//标签
	private String type;//标签类型
	private String username;//用户名

	//飞数据库字段
	private String typeName;//类型名称

	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}

	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type = type;
	}

	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}

	public String getTypeName() {
		this.typeName = TagsTypeEnum.getNameByCode(this.type);
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}

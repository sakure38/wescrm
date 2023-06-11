package com.we.scrm.domain;

public class CustomerTags extends AbstractEntity{

	private Long customerId;//客户id
	private Long tagId;//标签id

	//非数据库字段
	private String tagContent;
	private String tagType;

	public Long getCustomerId(){
		return customerId;
	}
	public void setCustomerId(Long customerId){
		this.customerId = customerId;
	}

	public Long getTagId(){
		return tagId;
	}
	public void setTagId(Long tagId){
		this.tagId = tagId;
	}

	public String getTagContent() {
		return tagContent;
	}

	public void setTagContent(String tagContent) {
		this.tagContent = tagContent;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}
}

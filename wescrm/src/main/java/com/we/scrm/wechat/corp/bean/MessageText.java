package com.we.scrm.wechat.corp.bean;

/**
*@author QiCong
*文本消息
**/

public class MessageText {
	
	final String TEXT = "text";
	
	private String touser;
	private String toparty;
	private String totag;
	private String msgtype = TEXT;
	private Integer agentid;
	private Content text;
	
	private Integer safe;
	private Integer enable_id_trans;
	private Integer enable_duplicate_check;
	private Integer duplicate_check_interval;
	
	
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getToparty() {
		return toparty;
	}
	public void setToparty(String toparty) {
		this.toparty = toparty;
	}
	public String getTotag() {
		return totag;
	}
	public void setTotag(String totag) {
		this.totag = totag;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
		if(!TEXT.equals(msgtype)) {
			this.msgtype = TEXT;
		}
	}
	public Integer getAgentid() {
		return agentid;
	}
	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}
	public Content getText() {
		return text;
	}
	public void setText(Content text) {
		this.text = text;
	}
	public Integer getSafe() {
		return safe;
	}
	public void setSafe(Integer safe) {
		this.safe = safe;
	}
	public Integer getEnable_id_trans() {
		return enable_id_trans;
	}
	public void setEnable_id_trans(Integer enable_id_trans) {
		this.enable_id_trans = enable_id_trans;
	}
	public Integer getEnable_duplicate_check() {
		return enable_duplicate_check;
	}
	public void setEnable_duplicate_check(Integer enable_duplicate_check) {
		this.enable_duplicate_check = enable_duplicate_check;
	}
	public Integer getDuplicate_check_interval() {
		return duplicate_check_interval;
	}
	public void setDuplicate_check_interval(Integer duplicate_check_interval) {
		this.duplicate_check_interval = duplicate_check_interval;
	}
	
	public void prepareContent(String content) {
		this.setText(new Content(content));
	}
	
}

class Content{
	
	private String content;

	Content(String content){
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
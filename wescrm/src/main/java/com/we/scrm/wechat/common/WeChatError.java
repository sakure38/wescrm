package com.we.scrm.wechat.common;
/**
* @author QiCong
* @version 2021年3月18日
* 微信生态返回消息的基类封装
**/

public class WeChatError {
	
	private Integer errcode;
	private String errmsg;
	
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}

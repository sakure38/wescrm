package com.we.scrm.web.api.vo;

import org.apache.commons.lang.StringUtils;

import com.we.scrm.wechat.common.util.WechatCacheUtil;

/**
*@author QiCong
*@version 2021-05-26
* 需要验证登录需继承此vo
**/

public abstract class AbstractVO {
	
	private String openid;
	private String sessionKey;
	private String parentOpenid;//上级分销openid
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	public String getParentOpenid() {
		return parentOpenid;
	}
	public void setParentOpenid(String parentOpenid) {
		this.parentOpenid = parentOpenid;
	}

	public boolean valid() {
		if(!StringUtils.isBlank(openid) && !StringUtils.isBlank(sessionKey)) {
			return sessionKey.equals(WechatCacheUtil.getSessionKey(openid));
		}
		return false;
	}
	
}


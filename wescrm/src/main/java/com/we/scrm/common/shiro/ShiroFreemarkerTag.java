package com.we.scrm.common.shiro;

import com.we.scrm.common.freemarker.FreemarkerTag;
import com.we.scrm.common.freemarker.FreemarkerTagModel;

import java.util.Map;

/**
*@author QiCong
*@version 2021-04-17
*定义一个 shiro 标签，判断用户是否登录
**/

@SuppressWarnings("rawtypes")
@FreemarkerTag(value = "shiro")
public class ShiroFreemarkerTag implements FreemarkerTagModel{
	
	static final String TYPE  = "type";
	static final String USER = "user";
	
	@Override
	public Object getParams(Map params) {
		String type = getString(params, TYPE);
		
		if(USER.equals(type)) {//返回当前登录用户信息
//			return getAuthUser(params);
		}
		
		return null;
	}
	
//	public AuthUser getAuthUser(Map params) {
//		return ShiroContext.getSessionUser();
//	}
	
}


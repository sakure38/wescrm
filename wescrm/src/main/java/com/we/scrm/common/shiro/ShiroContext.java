package com.we.scrm.common.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.we.scrm.domain.AuthUser;

/**
*@author QiCong
*@version 2021-04-16
**/
public class ShiroContext {
	
	public static final String SESSION_KEY = "_shiro_session_";
	public static final String COOKIE_NAME = "_shiro_cookie_";
	public static final String ENCRYPT_KEY = "_wechatSocialCrm";//length=16
	public static final String IDENTIFY_CODE = "_identify_code";


	public static String getSessionUsername(){
		return getSessionUser().getUsername();
	}

	public static Long getUserId(){
		return getSessionUser().getId();
	}

	public static String getCorpUserid(){
		return getSessionUser().getCorpuserid();
	}

	public static AuthUser getSessionUser(){
//		return mockUser();
		Session session = getSession();
		AuthUser authUser = (AuthUser)session.getAttribute(SESSION_KEY);
		if(null != authUser){
			authUser.setSalt(null);
			authUser.setPassword(null);
		}
		return authUser;
	}

	public static void updateSessionUser(AuthUser authUser){
		Session session = getSession();
		session.setAttribute(SESSION_KEY, authUser);
	}

	public static boolean isLogin(){
		return null != getSession().getAttribute(SESSION_KEY);
	}

	public  static  void logout(){
		SecurityUtils.getSubject().logout();
	}

	public static Session getSession(){
		return (Session)SecurityUtils.getSubject().getSession();
	}

	//mock user
	public static AuthUser mockUser(){
		AuthUser user = new AuthUser();
		user.setId(1L);
		user.setUsername("qicong");
		user.setCorpuserid("qicong");
		return user;
	}

}



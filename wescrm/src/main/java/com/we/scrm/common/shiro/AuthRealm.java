package com.we.scrm.common.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.we.scrm.common.util.HashUtil;
import com.we.scrm.domain.AuthUser;
import com.we.scrm.service.AuthUserService;

/**
*@author QiCong
*@version 2021-04-15
**/

public class AuthRealm extends AuthorizingRealm {
	
	Logger logger = LoggerFactory.getLogger(AuthRealm.class);

	@Autowired
	AuthUserService authUserService;
	
	//返回用户的角色:权限
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
		
		Set<String> permissions = new HashSet<String>();
		permissions.add("user:add");
		sai.setStringPermissions(permissions);
		
		return sai;
	}

	//实现用户登录
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();
		String password = new String((char[])token.getCredentials());

		//实现登录，访问数据库
		AuthUser authUser = authUserService.getByUsername(username);
		if(null == authUser){
			throw  new UnknownAccountException();
		}
		//密码校验
		String expertPassword = HashUtil.hmacSha256(password, authUser.getSalt());
		if(authUser.getPassword().equals(expertPassword)){
			SecurityUtils.getSubject().getSession().setAttribute(ShiroContext.SESSION_KEY, authUser);
		}else{
			throw  new UnknownAccountException();
		}
		
		return new SimpleAuthenticationInfo(username,password,getName());
	}

}

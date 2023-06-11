package com.we.scrm.common.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
*@author QiCong
*@version 2021-04-15
**/

@Configuration
public class ShiroConfiguration {
	
	Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);
	
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean sffb = new ShiroFilterFactoryBean();
		sffb.setSecurityManager(securityManager);
		sffb.setLoginUrl("/login");
		
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/res/**", "anon");
		filterChainDefinitionMap.put("/api/**", "anon");
		filterChainDefinitionMap.put("/error/**", "anon");
		filterChainDefinitionMap.put("/idcode", "anon");
		filterChainDefinitionMap.put("/file/attachment/**", "anon");
		
//		filterChainDefinitionMap.put("/**", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		sffb.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		return sffb;
	}
	
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager swsm = new DefaultWebSecurityManager();
		swsm.setRealm(authRealm());
		swsm.setSessionManager(sessionManager());
//		swsm.setRememberMeManager(rememberMeManager());
		return swsm;
	}
	
	@Bean
	public AuthRealm authRealm() {
		return new AuthRealm();
	}
	
	//去掉jsessionid小尾巴
	@Bean
	public DefaultWebSessionManager sessionManager() {
		//SessionIdUrlRewritingEnabled的默认值是false
		return new DefaultWebSessionManager();
	}
	
}


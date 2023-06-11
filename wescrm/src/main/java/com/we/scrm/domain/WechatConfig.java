package com.we.scrm.domain;

public class WechatConfig extends AbstractEntity{

	private String wid;//唯一id
	private String name;//账号名称
	private String appid;//公众号id
	private String appsecret;//公众号secret
	private String url;//公众号url
	private String token;//公众号token
	private String corpid;//企业微信id
	private String agentid;//企业微信应用id
	private String corsecret;//企业微信secret
	private String mappid;//小程序id
	private String mappsecret;//小程序secret
	private Long userid;//用户id

	public String getWid(){
		return wid;
	}
	public void setWid(String wid){
		this.wid = wid;
	}

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getAppid(){
		return appid;
	}
	public void setAppid(String appid){
		this.appid = appid;
	}

	public String getAppsecret(){
		return appsecret;
	}
	public void setAppsecret(String appsecret){
		this.appsecret = appsecret;
	}

	public String getUrl(){
		return url;
	}
	public void setUrl(String url){
		this.url = url;
	}

	public String getToken(){
		return token;
	}
	public void setToken(String token){
		this.token = token;
	}

	public String getCorpid(){
		return corpid;
	}
	public void setCorpid(String corpid){
		this.corpid = corpid;
	}

	public String getAgentid(){
		return agentid;
	}
	public void setAgentid(String agentid){
		this.agentid = agentid;
	}

	public String getCorsecret(){
		return corsecret;
	}
	public void setCorsecret(String corsecret){
		this.corsecret = corsecret;
	}

	public String getMappid(){
		return mappid;
	}
	public void setMappid(String mappid){
		this.mappid = mappid;
	}

	public String getMappsecret(){
		return mappsecret;
	}
	public void setMappsecret(String mappsecret){
		this.mappsecret = mappsecret;
	}

	public Long getUserid(){
		return userid;
	}
	public void setUserid(Long userid){
		this.userid = userid;
	}


}

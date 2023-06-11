package com.we.scrm.wechat.corp.bean;

import java.util.List;

/**
*@author QiCong
*@version 2021-05-21
*授权范围
**/

public class AgentScope {

	private Integer agentid;
	private List<String> allow_user;
	private List<Integer> allow_party;
	private List<Integer> allow_tag;
	
	
	public Integer getAgentid() {
		return agentid;
	}
	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}
	public List<String> getAllow_user() {
		return allow_user;
	}
	public void setAllow_user(List<String> allow_user) {
		this.allow_user = allow_user;
	}
	public List<Integer> getAllow_party() {
		return allow_party;
	}
	public void setAllow_party(List<Integer> allow_party) {
		this.allow_party = allow_party;
	}
	public List<Integer> getAllow_tag() {
		return allow_tag;
	}
	public void setAllow_tag(List<Integer> allow_tag) {
		this.allow_tag = allow_tag;
	}
}

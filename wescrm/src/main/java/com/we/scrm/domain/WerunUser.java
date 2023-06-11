package com.we.scrm.domain;

public class WerunUser extends AbstractEntity{

	private String openid;//
	private Integer steps;//总步数

	public String getOpenid(){
		return openid;
	}
	public void setOpenid(String openid){
		this.openid = openid;
	}

	public Integer getSteps(){
		return steps;
	}
	public void setSteps(Integer steps){
		this.steps = steps;
	}


}

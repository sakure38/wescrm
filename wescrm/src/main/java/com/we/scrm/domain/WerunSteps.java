package com.we.scrm.domain;

public class WerunSteps extends AbstractEntity{

	private String openid;//openid
	private Integer year;//年
	private Integer month;//月
	private Integer dates;//日
	private Integer steps;//步数
	private Integer killSteps;//已消耗步数

	public String getOpenid(){
		return openid;
	}
	public void setOpenid(String openid){
		this.openid = openid;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDates() {
		return dates;
	}

	public void setDates(Integer dates) {
		this.dates = dates;
	}

	public Integer getSteps() {
		return steps;
	}

	public void setSteps(Integer steps) {
		this.steps = steps;
	}

	public Integer getKillSteps() {
		return killSteps;
	}

	public void setKillSteps(Integer killSteps) {
		this.killSteps = killSteps;
	}
}

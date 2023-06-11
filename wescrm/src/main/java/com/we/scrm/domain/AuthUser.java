package com.we.scrm.domain;

import com.we.scrm.common.enums.UserStatusEnum;

import java.util.Date;


public class AuthUser extends AbstractEntity{

	private String username;//用户名
	private String name;//姓名
	private String corpuserid;//企业微信userid
	private String password;//密码
	private String salt;//salt
	private Integer gender;//性别
	private String header;//头像
	private String mobile;//手机号码
	private Integer status;//状态：正常（1），禁用（2）
	private Integer role;//角色
	private Date birthday;//生日
	private String position;//职位
	private String wechat;//微信号
	private String qq;//qq号
	private Date loginAt;//最后一次登录时间
	private String ip;//最后一次登录IP

	private String statusName;

	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getCorpuserid(){
		return corpuserid;
	}
	public void setCorpuserid(String corpuserid){
		this.corpuserid = corpuserid;
	}

	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}

	public String getSalt(){
		return salt;
	}
	public void setSalt(String salt){
		this.salt = salt;
	}

	public Integer getGender(){
		return gender;
	}
	public void setGender(Integer gender){
		this.gender = gender;
	}

	public String getHeader(){
		return header;
	}
	public void setHeader(String header){
		this.header = header;
	}

	public String getMobile(){
		return mobile;
	}
	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getRole(){
		return role;
	}
	public void setRole(Integer role){
		this.role = role;
	}

	public Date getBirthday(){
		return birthday;
	}
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}

	public String getPosition(){
		return position;
	}
	public void setPosition(String position){
		this.position = position;
	}

	public String getWechat(){
		return wechat;
	}
	public void setWechat(String wechat){
		this.wechat = wechat;
	}

	public String getQq(){
		return qq;
	}
	public void setQq(String qq){
		this.qq = qq;
	}

	public Date getLoginAt() {
		return loginAt;
	}
	public void setLoginAt(Date loginAt) {
		this.loginAt = loginAt;
	}

	public String getIp(){
		return ip;
	}
	public void setIp(String ip){
		this.ip = ip;
	}

	public String getStatusName() {
		this.statusName = UserStatusEnum.getNameByCode(this.status);
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}

package com.we.scrm.domain;

import com.we.scrm.common.enums.CustomerLevelEnum;
import com.we.scrm.common.enums.RebateTypeEnum;

import java.math.BigDecimal;
import java.util.Date;


public class Customer extends AbstractEntity{

	private Long userId;//所属员工id
	private String nickname;//昵称
	private String name;//姓名
	private String mobile;//手机号
	private String wxid;//微信id
	private String openid;//微信公众号openid
	private String copenid;//企业号openid
	private String mopenid;//小程序openid
	private String unionid;//unionid
	private String email;//邮箱
	private String address;//地址
	private Integer gender;//性别
	private Date birthday;//生日
	private String card;//证件类型
	private String cardNo;//证件号码
	private String bank;//银行
	private String bankNo;//银行卡号
	private String bankSite;//银行网点
	private BigDecimal money;//用户余额
	private Integer point;//用户积分
	private Integer level;//0-普通用户，1-普通会员，2-白银，3-黄金，4-白金会员，5-黑金会员，6-钻石
	private Integer rebateType;//佣金级别：0-普通用户，1-1级，2-2级
	private BigDecimal rebateMoney;//总佣金
	private Long parentId;//上级分销商id
	private String parentName;//上级分销商姓名
	private BigDecimal orderMoney;//总订单金额
	private Date orderTime;//最后一次下单时间
	private String remark;//备注
	private Integer userCount;//拉新用户数量

	private String levelName;
	private String rebateTypeName;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWxid() {
		return wxid;
	}

	public void setWxid(String wxid) {
		this.wxid = wxid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCopenid() {
		return copenid;
	}

	public void setCopenid(String copenid) {
		this.copenid = copenid;
	}

	public String getMopenid() {
		return mopenid;
	}

	public void setMopenid(String mopenid) {
		this.mopenid = mopenid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankSite() {
		return bankSite;
	}

	public void setBankSite(String bankSite) {
		this.bankSite = bankSite;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getRebateType() {
		return rebateType;
	}

	public void setRebateType(Integer rebateType) {
		this.rebateType = rebateType;
	}

	public BigDecimal getRebateMoney() {
		return rebateMoney;
	}

	public void setRebateMoney(BigDecimal rebateMoney) {
		this.rebateMoney = rebateMoney;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public BigDecimal getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public String getLevelName() {
		this.levelName = CustomerLevelEnum.getNameByCode(this.level);
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getRebateTypeName() {
		rebateTypeName = RebateTypeEnum.getNameByCode(this.rebateType);
		return rebateTypeName;
	}

	public void setRebateTypeName(String rebateTypeName) {
		this.rebateTypeName = rebateTypeName;
	}
}

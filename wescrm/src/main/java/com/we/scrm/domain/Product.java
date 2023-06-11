package com.we.scrm.domain;

import java.math.BigDecimal;


public class Product extends AbstractEntity{

	private String pid;//系统唯一id
	private String name;//名称
	private BigDecimal price;//销售价格
	private BigDecimal rebate;//返佣百分比
	private Integer status;//上下架状态：0下架,1上架
	private Long picture;//商品图片
	private Integer category;//分类
	private String categoryName;//分类名称
	private String description;//商品描述
	private Long mainPicture;//主图
	private Integer recommend;//是否推荐

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getRebate() {
		return rebate;
	}

	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getPicture() {
		return picture;
	}

	public void setPicture(Long picture) {
		this.picture = picture;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getMainPicture() {
		return mainPicture;
	}

	public void setMainPicture(Long mainPicture) {
		this.mainPicture = mainPicture;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
}

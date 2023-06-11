package com.we.scrm.domain;

import java.math.BigDecimal;
import java.util.Date;


public class Orders extends AbstractEntity{

	private String orderId;//订单id
	private Long customerId;//下单人id
	private Long rebateCustomerId;//获取佣金用户id
	private Long userId;//用户id
	private String productId;//商品ID
	private String productName;//商品名称
	private Integer productCount;//商品数量
	private BigDecimal productPrice;//购买商品单价
	private BigDecimal productRebate;//返佣百分比
	private Long productPicture;//商品图片id
	private Integer payMethod;//支付方式：1-现金，2-余额，3-微信，4-支付宝，5-网银，6-公交卡
	private Date payTime;//支付时间
	private BigDecimal money;//订单金额
	private Integer status;//订单状态：1-待支付，2-已支付，3-待发货，4-待收货，5-已完成，6-退单，7-完成退单
	private String address;//订单地址
	private Integer point;//使用积分
	private String invoice;//发票信息
	private String shippingSn;//快递单号
	private Date shippingTime;//发货时间
	private Date receiveTime;//收货时间
	private String remark;//备注

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getRebateCustomerId() {
		return rebateCustomerId;
	}

	public void setRebateCustomerId(Long rebateCustomerId) {
		this.rebateCustomerId = rebateCustomerId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public BigDecimal getProductRebate() {
		return productRebate;
	}

	public void setProductRebate(BigDecimal productRebate) {
		this.productRebate = productRebate;
	}

	public Long getProductPicture() {
		return productPicture;
	}

	public void setProductPicture(Long productPicture) {
		this.productPicture = productPicture;
	}

	public Integer getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getShippingSn() {
		return shippingSn;
	}

	public void setShippingSn(String shippingSn) {
		this.shippingSn = shippingSn;
	}

	public Date getShippingTime() {
		return shippingTime;
	}

	public void setShippingTime(Date shippingTime) {
		this.shippingTime = shippingTime;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}

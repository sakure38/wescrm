package com.we.scrm.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
*@author QiCong
*@version 2021-04-16
*订单状态：
*1-待支付，2-已支付，3-待发货，4-待收货，5-已完成，6-退单，7-完成退单
**/

public enum OrderStatusEnum {
	
	UNPAY(1, "待支付"),
	
	PAYED(2, "已支付"),
	
	UNSHIP(3, "待发货"),
	
	CONFIRM(4, "待收货"),
	
	FINISHED(5, "已完成"),
	
	REFUNDING(6, "退单中"),
	
	REFUND(7, "完成退单")
	;
	
	private Integer code;
	private String name;
	
	OrderStatusEnum(Integer code, String name){
		this.code = code;
		this.name = name;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
    public static String getNameByCode(Integer value){
        for(OrderStatusEnum item : OrderStatusEnum.values()){
            if(item.code.equals(value)){
                return item.getName();
            }
        }
        return null;
    }
	
    public static Map<Integer, String> toMap(){
    	Map<Integer, String> map = new HashMap<Integer,String>();
    	for(OrderStatusEnum item : OrderStatusEnum.values()){
    		map.put(item.code, item.name);
        }
    	return map;
    }
}

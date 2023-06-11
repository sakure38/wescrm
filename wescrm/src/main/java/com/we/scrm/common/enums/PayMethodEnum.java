package com.we.scrm.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
*@author QiCong
*@version 2021-04-16
*支付方式：
*1-现金，2-余额，3-微信，4-支付宝，5-网银，6-公交卡
**/

public enum PayMethodEnum {
	
	CASH(1,"现金"),
	
	BALANCE(2, "余额"),
	
	WECHAT(3, "微信"),
	
	ALIPAY(4, "支付宝"),
	
	ONLINEBANK(5, "网银"),
	
	BUSCARD(6, "公交卡")
	;
	
	private Integer code;
	private String name;
	
	PayMethodEnum(Integer code, String name){
		this.code = code;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getCode() {
		return code;
	}
	
    public static String getNameByCode(Integer value){
        for(PayMethodEnum item : PayMethodEnum.values()){
            if(item.code.equals(value)){
                return item.getName();
            }
        }
        return null;
    }
    
    public static Map<Integer, String> toMap(){
    	Map<Integer, String> map = new HashMap<Integer,String>();
    	for(PayMethodEnum item : PayMethodEnum.values()){
    		map.put(item.code, item.name);
        }
    	return map;
    }
	
}


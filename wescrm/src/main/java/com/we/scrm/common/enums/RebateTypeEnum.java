package com.we.scrm.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
*@author QiCong
*@version 2021-04-16
*佣金级别：
*0-普通用户，1-1级，2-2级
**/

public enum RebateTypeEnum {
	
	NORMAL(0,"普通用户"),
	
	ONE(1, "一级会员"),
	
	TWO(2, "二级会员")
	;
	
	private Integer code;
	private String name;
	
	RebateTypeEnum(Integer code, String name){
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
        for(RebateTypeEnum item : RebateTypeEnum.values()){
            if(item.code.equals(value)){
                return item.getName();
            }
        }
        return null;
    }
    
    public static Map<Integer, String> toMap(){
    	Map<Integer, String> map = new HashMap<Integer,String>();
    	for(RebateTypeEnum item : RebateTypeEnum.values()){
    		map.put(item.code, item.name);
        }
    	return map;
    }
	
}

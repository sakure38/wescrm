package com.we.scrm.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
*@author QiCong
*@version 2021-04-16
*会员级别：
*0-普通用户，1-普通会员，2-白银，3-黄金，4-白金会员，5-黑金会员，6-钻石
**/

public enum CustomerLevelEnum {
	
	NORMAL(0,"普通用户"),
	
	CUSTOMER(1, "会员"),
	
	SILVER(2, "白银会员"),
	
	GOLD(3, "黄金会员"),
	
	WHITEGOLD(4, "白金会员"),
	
	BLANKGOLD(5, "黑金会员"),
	
	DIAMOND(6, "钻石会员"),
	
	;
	
	private Integer code;
	private String name;
	
	CustomerLevelEnum(Integer code, String name){
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
        for(CustomerLevelEnum item : CustomerLevelEnum.values()){
            if(item.code.equals(value)){
                return item.getName();
            }
        }
        return null;
    }
    
    public static Map<Integer, String> toMap(){
    	Map<Integer, String> map = new HashMap<Integer,String>();
    	for(CustomerLevelEnum item : CustomerLevelEnum.values()){
    		map.put(item.code, item.name);
        }
    	return map;
    }
	
}

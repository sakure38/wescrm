package com.we.scrm.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
*@author QiCong
*@version 2021-04-16
*1-可用 2-禁用
**/

public enum UserStatusEnum {
	
	ENABLE(1,"可用"),
	
	DISABLE(2, "禁用"),
	
	;
	
	private Integer code;
	private String name;
	
	UserStatusEnum(Integer code, String name){
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
        for(UserStatusEnum item : UserStatusEnum.values()){
            if(item.code.equals(value)){
                return item.getName();
            }
        }
        return null;
    }
    
    public static Map<Integer, String> toMap(){
    	Map<Integer, String> map = new HashMap<Integer,String>();
    	for(UserStatusEnum item : UserStatusEnum.values()){
    		map.put(item.code, item.name);
        }
    	return map;
    }
	
}


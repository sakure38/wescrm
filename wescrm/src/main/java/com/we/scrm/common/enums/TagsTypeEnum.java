package com.we.scrm.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
*@author QiCong
*@version 2021-04-16
**/

public enum TagsTypeEnum {
	
	DEFAULT("default","默认"),
	
	PRIMARY("primary","观察中"),
	
	SUCCESS("success","成功"),
	
	INFO("info","公海客户"),
	
	WARNING("warning","警告"),
	
	DANGER("danger","差评"),
	
	;
	
	private String code;
	private String name;
	
	TagsTypeEnum(String code, String name){
		this.code = code;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCode() {
		return this.code;
	}
	
    public static String getNameByCode(String value){
        for(TagsTypeEnum item : TagsTypeEnum.values()){
            if(item.code.equals(value)){
                return item.getName();
            }
        }
        return null;
    }
    public static Map<String, String> toMap(){
    	Map<String, String> map = new HashMap<String,String>();
    	for(TagsTypeEnum item : TagsTypeEnum.values()){
    		map.put(item.code, item.name);
        }
    	return map;
    }
}


package com.we.scrm.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
*@author QiCong
*@version 2021-04-16
*证件类型
**/

public enum CardTypeEnum {
	
	IDENTITY(1,"身份证"),
	
	OFFICER(2, "军官证"),
	
	;
	
	private Integer code;
	private String name;
	
	CardTypeEnum(Integer code, String name){
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
        for(CardTypeEnum item : CardTypeEnum.values()){
            if(item.code.equals(value)){
                return item.getName();
            }
        }
        return null;
    }
    
    public static Map<Integer, String> toMap(){
    	Map<Integer, String> map = new HashMap<Integer,String>();
    	for(CardTypeEnum item : CardTypeEnum.values()){
    		map.put(item.code, item.name);
        }
    	return map;
    }
	
    public static List<JSONObject> toJsonList(){
    	List<JSONObject> list = new ArrayList<JSONObject>();
    	for(CardTypeEnum item : CardTypeEnum.values()){
    		JSONObject jsObj = new JSONObject();
    		jsObj.put("code", item.code);
    		jsObj.put("name", item.name);
    		list.add(jsObj);
        }
    	return list;
    }
    
}


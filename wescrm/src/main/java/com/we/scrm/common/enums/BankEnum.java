package com.we.scrm.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
*@author QiCong
*@version 2021-04-16
*银行
**/

public enum BankEnum {
	
	BOC(1,"中国银行"),
	
	ICBC(2, "工商银行"),
	
	CMB(3, "招商银行"),
	
	ABC(4, "农业银行"),
	
	;
	
	private Integer code;
	private String name;
	
	BankEnum(Integer code, String name){
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
        for(BankEnum item : BankEnum.values()){
            if(item.code.equals(value)){
                return item.getName();
            }
        }
        return null;
    }
    
    public static Map<Integer, String> toMap(){
    	Map<Integer, String> map = new HashMap<Integer,String>();
    	for(BankEnum item : BankEnum.values()){
    		map.put(item.code, item.name);
        }
    	return map;
    }
    
    public static List<JSONObject> toJsonList(){
    	List<JSONObject> list = new ArrayList<JSONObject>();
    	for(BankEnum item : BankEnum.values()){
    		JSONObject jsObj = new JSONObject();
    		jsObj.put("code", item.code);
    		jsObj.put("name", item.name);
    		list.add(jsObj);
        }
    	return list;
    }
	
}


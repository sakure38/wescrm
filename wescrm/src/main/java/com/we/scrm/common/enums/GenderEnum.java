package com.we.scrm.common.enums;

/**
*@author QiCong
*@version 2021-04-16
*性别：
*0-女，1-男，2-未知
**/

public enum GenderEnum {
	
	FEMALE(0,"女"),
	
	MALE(1, "男"),
	
	;
	
	private Integer code;
	private String name;
	
	GenderEnum(Integer code, String name){
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
        for(GenderEnum item : GenderEnum.values()){
            if(item.code.equals(value)){
                return item.getName();
            }
        }
        return null;
    }
	
}


package com.we.scrm.common.enums;

/**
*@author QiCong
*@version 2021-04-16
*状态：
*0-无效，1-有效
**/

public enum StatusEnum {

	DISABLE(0,"无效"),

	ENABLE(1, "有效"),

	;

	private Integer code;
	private String name;

	StatusEnum(Integer code, String name){
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
        for(StatusEnum item : StatusEnum.values()){
            if(item.code.equals(value)){
                return item.getName();
            }
        }
        return null;
    }
	
}


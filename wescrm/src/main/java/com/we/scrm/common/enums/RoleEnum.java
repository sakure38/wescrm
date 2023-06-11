package com.we.scrm.common.enums;

/**
*@author QiCong
*@version 2021-04-16
**/

public enum RoleEnum {
	
	VISITOR(0),
	
	ADMIN(1);
	
	public final int value;
	
	RoleEnum(int role){
		this.value = role;
	}

}

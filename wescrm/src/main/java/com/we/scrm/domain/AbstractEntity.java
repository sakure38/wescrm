package com.we.scrm.domain;

import java.util.Date;

public abstract class AbstractEntity{
	
	public static final int VAR_ENUM = 32;
	public static final int VAR_CHAR_DATE = 10;
	public static final int VAR_CHAR_HASH = 64;
	public static final int VAR_CHAR_EMAIL = 100;
	public static final int VAR_CHAR_TAGS = 100;
	public static final int VAR_CHAR_NAME = 100;
	public static final int VAR_CHAR_MIME = 100;
	public static final int VAR_CHAR_AUTH_ID = 255;
	public static final int VAR_CHAR_AUTH_TOKEN = 255;
	public static final int VAR_CHAR_DESCRIPTION = 1000;
	public static final int VAR_CHAR_URL = 1000;
	public static final int VAR_CHAR_SVG = 8192; // 8K
	public static final int TEXT = 65535; // 64K
	public static final int MEDIUM_TEXT = 1048575; //1M (512K = 524287)
	
	
	/**
	 * id
	 */
	private Long id;
	
	/**
	 * 创建时间
	 */
	private Date createAt;
	
	/**
	 * 更新时间
	 */
	private Date updateAt;
	
	//准备创建时间
	public void prepareCreate() {
		this.setCreateAt(new Date());
		this.setUpdateAt(new Date());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	
}


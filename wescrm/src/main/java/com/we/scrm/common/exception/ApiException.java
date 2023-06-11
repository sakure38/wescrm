package com.we.scrm.common.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @author QiCong
 * @version 2021年4月4日
 **/
public class ApiException extends RuntimeException {
	private static final long serialVersionUID = 9207748433621642532L;

	public final ApiError error;
	public final String data;

	public ApiException(ApiError error) {
		super(error.name());
		this.error = error;
		this.data = null;
	}

	public ApiException(ApiError error, String data, String message) {
		super(message);
		this.error = error;
		this.data = data;
	}

	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<>();
		map.put("error", this.error.name());
		map.put("data", this.data);
		map.put("message", this.getMessage());
		return map;
	}
}

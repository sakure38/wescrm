package com.we.scrm.domain;

import com.we.scrm.common.enums.ResourceEncodingEnum;
import com.we.scrm.common.exception.ApiError;
import com.we.scrm.common.exception.ApiException;

import java.util.Base64;


public class Resource extends AbstractEntity{

	//Hash of the binary data.
	private String hash;

	//Content encoding. e.g. "BASE64".
	private ResourceEncodingEnum encoding;

	private String content;

	public byte[] decode() {
		if (encoding == ResourceEncodingEnum.BASE64) {
			return Base64.getDecoder().decode(this.content);
		}
		throw new ApiException(ApiError.OPERATION_FAILED, null, "Could not decode content data.");
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public ResourceEncodingEnum getEncoding() {
		return encoding;
	}

	public void setEncoding(ResourceEncodingEnum encoding) {
		this.encoding = encoding;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


}

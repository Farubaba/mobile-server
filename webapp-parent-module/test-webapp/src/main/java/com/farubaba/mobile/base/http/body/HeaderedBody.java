package com.farubaba.mobile.base.http.body;

import java.util.HashMap;
import java.util.Map;

public class HeaderedBody extends Body {
	
	/**
	 * okhttp源码中需要一个map<String,String>而不需要ListMultimap，所以使用Map实现
	 */
	private Map<String,String> headers;
	//private ListMultimap<String,String> headers = MultimapBuilder.hashKeys().arrayListValues().build();
	private Body bodyContent;
	
	@Override
	public HeaderedBody setMimeType(String mimeType) {
		super.setMimeType(mimeType);
		return this;
	}
	
	public Map<String, String> getHeaders() {
		return headers == null ? new HashMap<String,String>() : headers;
	}
	
	public HeaderedBody setHeaders(Map<String, String> headers) {
		this.headers = headers;
		return this;
	}
	
	public Body getBodyContent() {
		return bodyContent;
	}
	
	public Body setBodyContent(Body bodyContent) {
		this.bodyContent = bodyContent;
		return this;
	}
	
}

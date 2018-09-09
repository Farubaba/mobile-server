package com.farubaba.mobile.base.http.body;

public class StringRequestBody extends Body {
	
	/**
	 * 小于1M，完全存储在内存的字符串
	 */
	private String bodyContent;

	public StringRequestBody() {
		
	}
	
	public StringRequestBody(String mimeType, String bodyContent){
		this.setMimeType(mimeType);
		this.setBodyContent(bodyContent);
	}
	
	@Override
	public StringRequestBody setMimeType(String mimeType) {
		super.setMimeType(mimeType);
		return this;
	}
	
	public String getBodyContent() {
		return bodyContent;
	}

	public StringRequestBody setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
		return this;
	}

}

package com.farubaba.mobile.base.http.body;

import java.io.InputStream;

public class StreamRequestBody extends Body {
	private InputStream bodyContent;
	
	public StreamRequestBody() {
		
	}
	
	public StreamRequestBody(String mimeType, InputStream bodyContent){
		this.setMimeType(mimeType);
		this.setBodyContent(bodyContent);
	}
	
	@Override
	public StreamRequestBody setMimeType(String mimeType) {
		super.setMimeType(mimeType);
		return this;
	}
	
	public InputStream getBodyContent() {
		return bodyContent;
	}

	public StreamRequestBody setBodyContent(InputStream bodyContent) {
		this.bodyContent = bodyContent;
		return this;
	}
}

package com.farubaba.mobile.base.http.body;

import java.util.HashMap;
import java.util.Map;

public class FormRequestBody extends Body {
	
	private Map<String, String> bodyContent;
	private boolean encoded;

	@Override
	public FormRequestBody setMimeType(String mimeType) {
		super.setMimeType(mimeType);
		return this;
	}
	
	public Map<String, String> getBodyContent() {
		return bodyContent == null ? new HashMap<String, String>() : bodyContent ;
	}

	public FormRequestBody setBodyContent(Map<String, String> bodyContent) {
		this.bodyContent = bodyContent;
		return this;
	}

	public boolean isEncoded() {
		return encoded;
	}

	public FormRequestBody setEncoded(boolean encoded) {
		this.encoded = encoded;
		return this;
	}
	
}

package com.farubaba.mobile.base.http.body;

public abstract class Body{
	
	private String mimeType;

	public String getMimeType() {
		return mimeType;
	}

	public Body setMimeType(String mimeType) {
		this.mimeType = mimeType;
		return this;
	}

}

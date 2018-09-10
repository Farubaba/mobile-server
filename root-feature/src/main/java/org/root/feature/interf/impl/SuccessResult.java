package org.root.feature.interf.impl;

import org.root.feature.interf.IModel;

public class SuccessResult<T> implements IModel {
	
	private String apiVersion;
	private T data;
	public String getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}

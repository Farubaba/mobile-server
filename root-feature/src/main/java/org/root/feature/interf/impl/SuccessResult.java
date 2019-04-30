package org.root.feature.interf.impl;

import org.root.feature.interf.IModel;

public class SuccessResult<M> implements IModel {
	
	private String apiVersion;
	private M data;
	public String getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	public M getData() {
		return data;
	}
	public void setData(M data) {
		this.data = data;
	}
	
}

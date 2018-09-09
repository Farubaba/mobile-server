package com.farubaba.mobile.base.http.model;

/**
 * 判断请求是否成功， 状态码 200
 * @author violet
 *
 */
public class StateAwareModel extends ErrorDetectModel {
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}

package com.farubaba.mobile.base.http.model;

import java.util.List;

/**
 * 返回的json字符串有如下特点：<br>
 * 
 * 1.包含一个key=error的错误数组。ErrorDetectModel[]<br>
 * 2.包含一个表示是否请求成功的属性: success<br>
 * 3.包含错误代码：code<br>
 * 4.包含错误信息: message<br>
 * 5.包含，服务器定义好的，要求客户端用来做显示的信息:display<br>
 * 
 * @author violet
 *
 */
public class ArrayErrorModel extends StateAwareModel{
	private List<ErrorDetectModel> error;

	public List<ErrorDetectModel> getError() {
		return error;
	}

	public void setError(List<ErrorDetectModel> error) {
		this.error = error;
	}
	
}

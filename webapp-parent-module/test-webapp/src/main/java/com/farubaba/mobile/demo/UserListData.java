package com.farubaba.mobile.demo;

import java.util.List;

import com.farubaba.mobile.base.http.model.StringErrorModel;
import com.farubaba.mobile.server.model.User;

public class UserListData extends StringErrorModel {
	
	private List<User> data;
	private boolean suc;

	public boolean isSuc() {
		return suc;
	}

	public void setSuc(boolean suc) {
		this.suc = suc;
	}

	public List<User> getData() {
		return data;
	}

	public void setData(List<User> data) {
		this.data = data;
	}
	
}

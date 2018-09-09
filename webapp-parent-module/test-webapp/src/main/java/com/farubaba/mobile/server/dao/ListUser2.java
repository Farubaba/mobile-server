package com.farubaba.mobile.server.dao;

import java.util.List;

import com.farubaba.mobile.base.http.model.IModel;
import com.farubaba.mobile.server.model.User;

public class ListUser2  implements IModel{
	private List<User> data;

	public List<User> getData() {
		return data;
	}

	public void setData(List<User> data) {
		this.data = data;
	}

}

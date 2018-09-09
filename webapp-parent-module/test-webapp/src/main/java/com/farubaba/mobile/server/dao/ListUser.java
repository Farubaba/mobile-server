package com.farubaba.mobile.server.dao;

import java.util.List;

import com.farubaba.mobile.base.http.model.IModel;
import com.farubaba.mobile.server.model.User;

public class ListUser implements IModel{
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}

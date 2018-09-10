package org.business.domain.dao;

import java.util.List;

import org.business.domain.model.User;
import org.root.feature.interf.IModel;

public class ListUser implements IModel{
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}

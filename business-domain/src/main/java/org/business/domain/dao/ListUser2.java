package org.business.domain.dao;

import java.util.List;

import org.business.domain.model.User;
import org.root.feature.interf.IModel;

public class ListUser2  implements IModel{
	private List<User> data;

	public List<User> getData() {
		return data;
	}

	public void setData(List<User> data) {
		this.data = data;
	}

}

package com.farubaba.mobile.server.dao;

import com.farubaba.mobile.server.model.User;

public interface UserDao {
	User login(String username,String password);
}

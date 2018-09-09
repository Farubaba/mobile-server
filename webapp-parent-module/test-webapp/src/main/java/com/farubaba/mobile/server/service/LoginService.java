package com.farubaba.mobile.server.service;

import com.farubaba.mobile.server.model.User;

public interface LoginService{
	String login(String userName, String password);
	User userLogin(String userName, String password);
}

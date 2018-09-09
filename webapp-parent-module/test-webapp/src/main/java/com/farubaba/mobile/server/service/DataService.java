package com.farubaba.mobile.server.service;

import java.util.List;

import com.farubaba.mobile.server.model.User;

public interface DataService {
	
	public List<User> getUsers(String version);
	
	public String getUserListJson(String version);
	
}

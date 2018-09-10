package org.business.domain.service;

import java.util.List;

import org.business.domain.model.User;

public interface DataService {
	
	public List<User> getUsers(String version);
	
	public String getUserListJson(String version);
	
}

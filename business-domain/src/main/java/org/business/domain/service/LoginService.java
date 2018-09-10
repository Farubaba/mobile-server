package org.business.domain.service;

import org.business.domain.model.User;

public interface LoginService{
	String login(String userName, String password);
	User userLogin(String userName, String password);
}

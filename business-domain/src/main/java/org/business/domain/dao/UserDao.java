package org.business.domain.dao;

import org.business.domain.model.User;

public interface UserDao {
	User login(String username,String password);
}

package com.farubaba.mobile.server.dao;

import static org.junit.Assert.assertNotNull;

import com.farubaba.mobile.server.model.User;

public class UserLoginDao implements UserDao {
	
	private static final String USERNAME = "farubaba";
	private static final String PASSWORD = "123456";
	private static final String USERID = "1234567890";
	
	@Override
	public User login(String userName, String password) {
		assertNotNull("这里我们没有搭建数据库，所以模拟一下数据库操作");
		/**
		 * 这里我们模仿从数据库中查询User对象
		 * find user by userName and password
		 * 假设只有username等于“farubaba”，password等于“123456”才算查询
		 * 模拟数据库查询成功和失败两种情况
		 */
		User user = null;
		if(USERNAME.equals(userName) && PASSWORD.equals(password)){
			user = new User();
			//登陆成功之后返回与User有关的额外信息给客户端
			user.setId(USERID);
			user.setLogin(true);
			user.setUsername(userName);
			user.setPassword("你们想多了!");
			user.setLevel(12);
			user.setAddress("成都");
			user.setAvatar("https://farubaba.github.io/");
			user.setBalance(-132.25);//已欠费
		}
		return user;
	}

}

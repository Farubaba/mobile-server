package org.business.domain.service;

import org.business.domain.dao.UserDao;
import org.business.domain.dao.UserLoginDao;
import org.business.domain.model.User;
import org.root.feature.interf.impl.ErrorResult;
import org.root.feature.json.JsonFactory;
import org.root.feature.json.JsonService;


public class UserLoginService implements LoginService{
	
	protected JsonService jsonService = JsonFactory.getJsonService();
	protected UserDao userDao = new UserLoginDao();

	@Override
	public String login(String userName, String password) {
		User user = userDao.login(userName, password);
		//登陆逻辑判断，并返回给客户端不同的response内容
		if(user == null || !user.isLogin()){
			ErrorResult  error = new ErrorResult(1404, "USER_NOT_FOUNT", "用户："+ userName +" 不存在, 或者密码："+ password +" 错误！");
			return jsonService.toJsonString(error);
		}
		return jsonService.toJsonString(user);
	}

	@Override
	public User userLogin(String userName, String password) {
		User user = userDao.login(userName, password);
		return user;
	}

}

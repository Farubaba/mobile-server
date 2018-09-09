package com.farubaba.mobile.server.service;

import com.farubaba.mobile.server.model.Book;
import com.farubaba.mobile.server.model.User;

public class SysConfigService implements ConfigService {

	@Override
	public User sysConfig(String version) {
		
		
		User user = new User();
		user.setId("0");
		user.setUsername("json username");
		user.setLevel(1);
		if("v1".equals(version)){
			user.setAddress("cd");
		}else{
			user.setAddress("成都");
		}
		user.setBalance(-100.0);
		user.setAvatar("https://farubaba.github.io");
		user.setPassword("12345678");
		user.setVersion(version);
		//add book
		Book b = new Book();
		b.setName("java");
		b.setType("software");
		b.setAuthor("master");
		b.setPrice(100.00);
		user.setFavoriteBook(b);
		Book b2 = new Book();
		b2.setName("nodejs");
		b2.setType("forground end");
		b2.setAuthor("master");
		user.getBooks().add(b);
		user.getBooks().add(b2);
		
		user.getBookMap().put("java", b);
		user.getBookMap().put("nodejs", b2);
		return user;
	}

}

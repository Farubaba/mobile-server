package com.farubaba.mobile.server.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.farubaba.mobile.server.model.Book;
import com.farubaba.mobile.server.model.User;

public class DataCenterImpl implements DataCenter {

	@Override
	public List<User> getUserList(String apiVersion) {
		List<User> users = new ArrayList<User>();
		users.add(getUser("1","farubaba","云海仙门",apiVersion));
		users.add(getUser("2", "叶小钗", "成都",apiVersion));
		return users;
	}

	@Override
	public User getUser(String id, String name, String address,String apiVersion) {
		User user = new User();
		user.setId(id);
		user.setUsername(name);
		user.setLevel(1);
		user.setAddress(address);
		user.setBalance(-100.0);
		user.setAvatar("https://farubaba.github.io");
		user.setPassword("12345678");
		user.setVersion(apiVersion);
		//add book
		user.setFavoriteBook(getBook("3", "nodejs", "forground",apiVersion));
		user.setBooks(getBookList(apiVersion));
		user.setBookMap(getBookMap(apiVersion));
		return user;
	}


	@Override
	public Map<String, User> getUserMap(String apiVersion) {
		Map<String, User> map = new HashMap<String, User>();
		map.put("farubaba", getUser("1","farubaba","云海仙门",apiVersion));
		map.put("yexiaochai", getUser("2", "叶小钗", "成都",apiVersion));
		return map;
	}

	@Override
	public Book getBook(String id, String name, String type,String apiVersion) {
		Book b = new Book();
		b.setId(id);
		b.setName(name);
		b.setType(type);
		b.setAuthor("master");
		b.setPrice(100.00);
		b.setApiVersion(apiVersion);
		return b;
	}

	@Override
	public List<Book> getBookList(String apiVersion) {
		List<Book> books = new ArrayList<Book>();
		Book b1 = getBook("1","java","software",apiVersion);
		Book b2 = getBook("2","光荣与梦想","history",apiVersion);
		books.add(b1);
		books.add(b2);
		return books;
	}

	@Override
	public Map<String, Book> getBookMap(String apiVersion) {
		Map<String, Book> map = new HashMap<String, Book>();
		Book b1 = getBook("1","java","software",apiVersion);
		Book b2 = getBook("2","光荣与梦想","history",apiVersion);
		map.put("java", b1);
		map.put("history", b2);
		return map;
	}

}

package com.farubaba.mobile.server.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.farubaba.mobile.base.http.model.IModel;

public class User implements IModel {

	private String id;
	private String username;
	private String password;
	private boolean login;
	// User Login 相关信息
	private int level;
	private String avatar;
	private String address;
	private double balance;
	private String version;
	
	private Book favoriteBook;
	private List<Book> books = new ArrayList<Book>();
	private Map<String,Book> bookMap = new HashMap<String, Book>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public Book getFavoriteBook() {
		return favoriteBook;
	}

	public void setFavoriteBook(Book favoriteBook) {
		this.favoriteBook = favoriteBook;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}	

	public Map<String, Book> getBookMap() {
		return bookMap;
	}

	public void setBookMap(Map<String, Book> bookMap) {
		this.bookMap = bookMap;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", login=" + login + ", level="
				+ level + ", avatar=" + avatar + ", address=" + address + ", balance=" + balance + ", version = "+ version +" ]";
	}

}

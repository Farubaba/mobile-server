package com.farubaba.mobile.server.model;

import com.farubaba.mobile.base.http.model.IModel;

public class Book implements IModel{
	private String id;
	private String name;
	private String author;
	private String type;
	private double price;
	private String publisher;
	private Double discutPrice; 
	private String apiVersion;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Double getDiscutPrice() {
		return discutPrice;
	}
	public void setDiscutPrice(Double discutPrice) {
		this.discutPrice = discutPrice;
	}
	public String getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
}

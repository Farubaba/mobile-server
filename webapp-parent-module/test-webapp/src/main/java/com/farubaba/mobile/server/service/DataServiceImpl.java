package com.farubaba.mobile.server.service;

import java.util.List;

import com.farubaba.mobile.server.dao.DataCenterImpl;
import com.farubaba.mobile.server.dao.DataCenter;
import com.farubaba.mobile.server.model.SuccessResult;
import com.farubaba.mobile.server.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataServiceImpl implements DataService {

	private DataCenter dataCenter = new DataCenterImpl(); 
	private Gson gson = new GsonBuilder().create();
	
	@Override
	public List<User> getUsers(String apiVersion) {
		return dataCenter.getUserList(apiVersion);
	}

	@Override
	public String getUserListJson(String apiVersion) {
		return gson.toJson(getUsers(apiVersion));
	}

}

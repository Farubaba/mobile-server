package org.business.domain.service;

import java.util.List;

import org.business.domain.dao.DataCenter;
import org.business.domain.dao.DataCenterImpl;
import org.business.domain.model.User;

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

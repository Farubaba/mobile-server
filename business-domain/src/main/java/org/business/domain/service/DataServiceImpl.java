package org.business.domain.service;

import java.util.List;

import org.business.domain.dao.DataCenter;
import org.business.domain.dao.DataCenterImpl;
import org.business.domain.model.User;
import org.root.feature.json.JsonFactory;
import org.root.feature.json.JsonService;

public class DataServiceImpl implements DataService {

	private DataCenter dataCenter = new DataCenterImpl(); 
	private JsonService<?> gson = JsonFactory.getJsonService();
	
	@Override
	public List<User> getUsers(String apiVersion) {
		return dataCenter.getUserList(apiVersion);
	}

	@Override
	public String getUserListJson(String apiVersion) {
		return gson.toJsonString(getUsers(apiVersion));
	}

}

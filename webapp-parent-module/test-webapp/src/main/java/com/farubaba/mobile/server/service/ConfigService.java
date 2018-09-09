package com.farubaba.mobile.server.service;

import com.farubaba.mobile.server.model.User;

public interface ConfigService {
	User sysConfig(String version);
}

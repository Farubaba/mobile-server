package org.business.domain.service;

import org.business.domain.model.User;

public interface ConfigService {
	User sysConfig(String version);
}

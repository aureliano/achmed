package com.github.aureliano.achmed.os.service;

import com.github.aureliano.achmed.resources.properties.ServiceProperties;

public abstract class BaseService implements IService {

	protected ServiceProperties properties;
	
	public BaseService() {
		super();
	}

	public void setServiceProperties(ServiceProperties properties) {
		this.properties = properties;
	}

	public ServiceProperties getServiceProperties() {
		return this.properties;
	}
}
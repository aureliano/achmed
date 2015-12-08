package com.github.aureliano.achmed.os.service;

import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.resources.properties.ServiceProperties;

public abstract class BaseService implements IService {

	private ServiceProperties properties;
	
	public BaseService() {
		super();
	}

	public CommandResponse start() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public CommandResponse stop() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public CommandResponse restart() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public boolean isRunning() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public void setServiceProperties(ServiceProperties properties) {
		this.properties = properties;
	}

	public ServiceProperties getServiceProperties() {
		return this.properties;
	}
}
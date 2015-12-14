package com.github.aureliano.achmed.os.service;

import com.github.aureliano.achmed.exception.ServiceResourceException;
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
	
	protected void assertServiceIsRunning() {
		if (!this.isRunning()) {
			throw new ServiceResourceException(
				"Expected to get service " + this.properties.getName() + " running but it is not."
			);
		}
	}
	
	protected void assertServiceIsStopped() {
		if (this.isRunning()) {
			throw new ServiceResourceException(
				"Expected to get service " + this.properties.getName() + " stopped but it is still running."
			);
		}
	}
	
	protected void assertServiceIsEnabledInBootstrap() {
		if (!this.isEnabledInBootstrap()) {
			throw new ServiceResourceException(
				"Expected to get service " + this.properties.getName() + " enabled in bootstrap but it is not."
			);
		}
	}
	
	protected void assertServiceIsDisabledInBootstrap() {
		if (this.isEnabledInBootstrap()) {
			throw new ServiceResourceException(
				"Expected to get service " + this.properties.getName() + " disabled in bootstrap but it is not."
			);
		}
	}
}
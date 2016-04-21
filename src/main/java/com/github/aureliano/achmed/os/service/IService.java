package com.github.aureliano.achmed.os.service;

import com.github.aureliano.achmed.client.command.CommandResponse;
import com.github.aureliano.achmed.resources.properties.ServiceProperties;

public interface IService {

	public abstract CommandResponse start();
	
	public abstract CommandResponse stop();
	
	public abstract CommandResponse restart();
	
	public abstract boolean isRunning();
	
	public abstract CommandResponse enableBootstrap();
	
	public abstract CommandResponse disableBootstrap();
	
	public abstract boolean isEnabledInBootstrap();
	
	public abstract void setServiceProperties(ServiceProperties properties);
	
	public abstract ServiceProperties getServiceProperties();
}
package com.github.aureliano.achmed.resources;

import java.util.logging.Logger;

import com.github.aureliano.achmed.AppConfiguration;
import com.github.aureliano.achmed.os.service.IService;
import com.github.aureliano.achmed.resources.properties.IResourceProperties;
import com.github.aureliano.achmed.resources.properties.ServiceProperties;

public class ServiceResource implements IResource {

	private static final Logger logger = Logger.getLogger(ServiceResource.class.getName());
	
	private ServiceProperties properties;
	
	public ServiceResource() {
		this.properties = new ServiceProperties();
	}
	
	public ServiceResource(ServiceProperties properties) {
		this.properties = properties;
	}
	
	public void apply() {
		this.properties.configureAttributes();
		logger.fine("Resource description: " + this.properties.get("description"));
		logger.info(" >>> Apply service resource " + this.properties.getName());
		
		IService service = AppConfiguration.instance().getOperatingSystem().getDefaultServiceManager();
		service.setServiceProperties(this.properties);
		
		this.ensure(service);
		this.configureBootstrap(service);
	}
	
	public void setProperties(IResourceProperties properties) {
		this.properties = (ServiceProperties) properties;
	}
	
	public IResourceProperties getProperties() {
		return this.properties;
	}

	public ResourceType type() {
		return ResourceType.SERVICE;
	}
	
	private void ensure(IService service) {
		if (this.properties.getEnsure()) {
			if (!service.isRunning()) {
				service.start();
			}
		} else {
			if (service.isRunning()) {
				service.stop();
			}
		}
	}
	
	private void configureBootstrap(IService service) {
		if (this.properties.getEnable() == null) {
			return; 
		 } else if (this.properties.getEnable()) {
			 service.enableBootstrap();
		 } else {
			 service.disableBootstrap();
		 }
	}
}
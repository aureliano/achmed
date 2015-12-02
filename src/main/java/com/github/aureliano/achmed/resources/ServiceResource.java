package com.github.aureliano.achmed.resources;

import com.github.aureliano.achmed.resources.properties.IResourceProperties;
import com.github.aureliano.achmed.resources.properties.ServiceProperties;

public class ServiceResource implements IResource {

	private ServiceProperties properties;
	
	public ServiceResource() {
		super();
	}
	
	public ServiceResource(ServiceProperties properties) {
		this.properties = properties;
	}
	
	public void apply() {
		throw new UnsupportedOperationException("Not implemented yet.");
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
}
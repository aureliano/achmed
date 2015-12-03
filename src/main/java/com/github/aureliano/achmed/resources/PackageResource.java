package com.github.aureliano.achmed.resources;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.resources.properties.IResourceProperties;
import com.github.aureliano.achmed.resources.properties.PackageProperties;

public class PackageResource implements IResource {

	private static final Logger logger = Logger.getLogger(PackageResource.class);
	
	private PackageProperties properties;
	
	public PackageResource() {
		this.properties = new PackageProperties();
	}
	
	public PackageResource(PackageProperties properties) {
		this.properties = properties;
	}
	
	public void apply() {
		logger.info(" >>> Apply package resource to: " + this.properties.getName());
		
		this.properties.configureAttributes();
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	public void setProperties(IResourceProperties properties) {
		this.properties = (PackageProperties) properties;
	}
	
	public IResourceProperties getProperties() {
		return this.properties;
	}

	public ResourceType type() {
		return ResourceType.PACKAGE;
	}
}
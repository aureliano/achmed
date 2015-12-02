package com.github.aureliano.achmed.resources;

import com.github.aureliano.achmed.resources.properties.IResourceProperties;
import com.github.aureliano.achmed.resources.properties.PackageProperties;


public class PackageResource implements IResource {

	private PackageProperties properties;
	
	public PackageResource() {
		super();
	}
	
	public PackageResource(PackageProperties properties) {
		this.properties = properties;
	}
	
	public void apply() {
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
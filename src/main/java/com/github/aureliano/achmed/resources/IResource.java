package com.github.aureliano.achmed.resources;

import com.github.aureliano.achmed.resources.properties.IResourceProperties;

public interface IResource {

	public abstract void apply();
	
	public abstract ResourceType type();
	
	public abstract void setProperties(IResourceProperties properties);
	
	public abstract IResourceProperties getProperties();
}
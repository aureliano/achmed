package com.github.aureliano.achmed.resources;

import com.github.aureliano.achmed.resources.properties.IResourceProperties;

public interface IResource {

	public abstract void apply(IResourceProperties properties);
	
	public abstract ResourceType type();
	
	public abstract IResourceProperties getProperties();
}
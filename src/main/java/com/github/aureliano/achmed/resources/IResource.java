package com.github.aureliano.achmed.resources;

import com.github.aureliano.achmed.resources.properties.IResourceProperties;

public interface IResource {

	public abstract void make(IResourceProperties properties);
	
	public abstract ResourceType type();
}
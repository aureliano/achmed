package com.github.aureliano.achmed.resources.properties;

public interface IResourceProperties {

	public abstract IResourceProperties put(String key, Object value);
	
	public abstract Object get(String key);
	
	public abstract void configureAttributes();
}
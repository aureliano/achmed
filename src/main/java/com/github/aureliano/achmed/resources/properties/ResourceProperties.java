package com.github.aureliano.achmed.resources.properties;

import java.util.HashMap;
import java.util.Map;

public class ResourceProperties implements IResourceProperties {

	private Map<String, Object> properties;
	
	public ResourceProperties() {
		this.properties = new HashMap<String, Object>();
	}

	public IResourceProperties put(String key, Object value) {
		this.properties.put(key, value);
		return this;
	}

	public Object get(String key) {
		return this.properties.get(key);
	}
}
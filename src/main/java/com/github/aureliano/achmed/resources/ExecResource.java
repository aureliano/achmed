package com.github.aureliano.achmed.resources;

import java.util.Map;

import com.github.aureliano.achmed.resources.properties.ExecProperties;
import com.github.aureliano.achmed.resources.properties.IResourceProperties;

public class ExecResource implements IResource {

	private ExecProperties properties;
	
	public ExecResource(ExecProperties properties) {
		this.properties = properties;
		this.properties.configureAttributes();
	}
	
	public ExecResource(Map<String, Object> propertiesMap) {
		this.properties = new ExecProperties();
		
		for (String key : propertiesMap.keySet()) {
			this.properties.put(key, propertiesMap.get(key));
		}
		
		this.properties.configureAttributes();
	}

	public void apply(IResourceProperties properties) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	public IResourceProperties getProperties() {
		return this.properties;
	}

	public ResourceType type() {
		return ResourceType.EXEC;
	}
}
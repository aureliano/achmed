package com.github.aureliano.achmed.resources;

import com.github.aureliano.achmed.resources.properties.ExecProperties;
import com.github.aureliano.achmed.resources.properties.IResourceProperties;

public class ExecResource implements IResource {

	private ExecProperties properties;
	
	public ExecResource() {
		super();
	}
	
	public ExecResource(ExecProperties properties) {
		this.properties = properties;
	}

	public void apply() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	public void setProperties(IResourceProperties properties) {
		this.properties = (ExecProperties) properties;
	}
	
	public IResourceProperties getProperties() {
		return this.properties;
	}

	public ResourceType type() {
		return ResourceType.EXEC;
	}
}
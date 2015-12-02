package com.github.aureliano.achmed.resources;

import com.github.aureliano.achmed.resources.properties.IResourceProperties;

public class ExecResource implements IResource {

	public ExecResource() {
		super();
	}

	public void apply(IResourceProperties properties) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public ResourceType type() {
		return ResourceType.EXEC;
	}
}
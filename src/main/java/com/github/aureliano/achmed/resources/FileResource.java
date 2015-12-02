package com.github.aureliano.achmed.resources;

import com.github.aureliano.achmed.resources.properties.FileProperties;
import com.github.aureliano.achmed.resources.properties.IResourceProperties;

public class FileResource implements IResource {
	
	private FileProperties properties;
	
	public FileResource() {
		super();
	}

	public FileResource(FileProperties properties) {
		this.properties = properties;
	}
	
	public void apply() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	public void setProperties(IResourceProperties properties) {
		this.properties = (FileProperties) properties;
	}
	
	public IResourceProperties getProperties() {
		return this.properties;
	}

	public ResourceType type() {
		return ResourceType.FILE;
	}
}
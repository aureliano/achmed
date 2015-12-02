package com.github.aureliano.achmed.resources;

import com.github.aureliano.achmed.exception.NoSuchResourceException;

public final class ResourceFactory {

	private ResourceFactory() {
		super();
	}
	
	public static IResource createResource(ResourceType type) {
		switch (type) {
			case FILE : return new FileResource();
			case EXEC : return new ExecResource();
			case PACKAGE : return new PackageResource();
			case SERVICE : return new ServiceResource();
			default : throw new NoSuchResourceException("There is no such resource implementation to '" + type + "' type.");
		}
	}
	
	public static IResource createResource(String typeName) {
		ResourceType type = null;
		try {
			type = ResourceType.valueOf(typeName.toUpperCase());
		} catch (IllegalArgumentException ex) {
			throw new NoSuchResourceException("There is no such resource implementation to '" + typeName + "' type.");
		}
		
		return createResource(type);
	}
}
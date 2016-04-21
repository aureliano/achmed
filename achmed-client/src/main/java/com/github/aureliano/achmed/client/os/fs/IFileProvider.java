package com.github.aureliano.achmed.client.os.fs;

import com.github.aureliano.achmed.resources.properties.FileProperties;

public interface IFileProvider {

	public abstract void setFileMode();
	
	public abstract void setFileOwner();
	
	public abstract void ensureFilePresence();
	
	public abstract void ensureFileAbsence();
	
	public abstract void setFileProperties(FileProperties properties);
	
	public abstract FileProperties getFileProperties();
}
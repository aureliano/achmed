package com.github.aureliano.achmed.os.fs;

import com.github.aureliano.achmed.resources.properties.FileProperties;

public interface IFileProvider {

	public abstract void setFileMode();
	
	public abstract void setFileOwner();
	
	public abstract void copyFile();
	
	public abstract void copyFolder();
	
	public abstract void setFileProperties(FileProperties properties);
	
	public abstract FileProperties getFileProperties();
}
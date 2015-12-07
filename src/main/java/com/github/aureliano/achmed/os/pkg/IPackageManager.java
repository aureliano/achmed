package com.github.aureliano.achmed.os.pkg;

import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.resources.properties.PackageProperties;

public interface IPackageManager {

	public abstract CommandResponse install();
	
	public abstract CommandResponse uninstall();
	
	public abstract String latest();
	
	public abstract void setPackageProperties(PackageProperties properties);
	
	public abstract PackageProperties getPackageProperties();
}
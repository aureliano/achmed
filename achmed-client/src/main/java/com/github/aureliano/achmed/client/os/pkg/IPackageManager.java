package com.github.aureliano.achmed.client.os.pkg;

import com.github.aureliano.achmed.client.command.CommandResponse;
import com.github.aureliano.achmed.resources.properties.PackageProperties;

public interface IPackageManager {

	public abstract CommandResponse install();
	
	public abstract CommandResponse uninstall();
	
	public abstract String latest();
	
	public abstract boolean isInstalled();
	
	public abstract void setPackageProperties(PackageProperties properties);
	
	public abstract PackageProperties getPackageProperties();
}
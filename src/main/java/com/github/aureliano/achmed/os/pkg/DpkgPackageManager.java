package com.github.aureliano.achmed.os.pkg;

import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.resources.properties.PackageProperties;

public class DpkgPackageManager implements IPackageManager {

	private PackageProperties properties;
	
	public DpkgPackageManager() {
		super();
	}

	public CommandResponse install() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public CommandResponse uninstall() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public String latest() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public void setPackageProperties(PackageProperties properties) {
		this.properties = properties;
	}

	public PackageProperties getPackageProperties() {
		return this.properties;
	}
}
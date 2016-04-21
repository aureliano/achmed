package com.github.aureliano.achmed.client.os.pkg;

import com.github.aureliano.achmed.client.command.CommandResponse;
import com.github.aureliano.achmed.resources.properties.PackageProperties;

public class GemPackageManager implements IPackageManager {

	private PackageProperties properties;
	
	public GemPackageManager() {
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
	
	public boolean isInstalled() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public void setPackageProperties(PackageProperties properties) {
		this.properties = properties;
	}

	public PackageProperties getPackageProperties() {
		return this.properties;
	}
}
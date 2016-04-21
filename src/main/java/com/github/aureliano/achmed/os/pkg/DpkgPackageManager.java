package com.github.aureliano.achmed.os.pkg;

import java.util.List;

import com.github.aureliano.achmed.client.command.CommandFacade;
import com.github.aureliano.achmed.client.command.CommandResponse;
import com.github.aureliano.achmed.client.exception.PackageResourceException;
import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.resources.properties.PackageProperties;

public class DpkgPackageManager implements IPackageManager {

	private static final String DPKG_QUERY = "/usr/bin/dpkg-query";

	protected PackageProperties properties;
	
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
	
	public boolean isInstalled() {
		final String desiredStatus = "install ok installed";
		CommandResponse res = CommandFacade.executeCommand(DPKG_QUERY, "-s", this.properties.getName());
		
		if (!res.isOK()) {
			// query exits with no zero status when package is not found.
			return false;
		}
		
		List<String> match = StringHelper.match("Status:\\s(\\S+\\s\\S+\\s\\S+)", res.getOutput());
		if (match.isEmpty()) {
			throw new PackageResourceException("Failed to match dpkg-query [" + res.getOutput() + "]");
		}
		
		return desiredStatus.equalsIgnoreCase(match.get(1));
	}

	public void setPackageProperties(PackageProperties properties) {
		this.properties = properties;
	}

	public PackageProperties getPackageProperties() {
		return this.properties;
	}
}
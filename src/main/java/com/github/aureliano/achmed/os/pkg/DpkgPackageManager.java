package com.github.aureliano.achmed.os.pkg;

import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.exception.PackageResourceException;
import com.github.aureliano.achmed.helper.StringHelper;
import com.github.aureliano.achmed.resources.properties.PackageProperties;

public class DpkgPackageManager implements IPackageManager {
	
	private static final String DPKG_QUERY = "/usr/bin/dpkg-query";
	private static final String QUERY_FORMAT = "'${status} ${version}\\n'";

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
	
	public boolean isInstalled() {
		final String desiredStatus = "installed";
		CommandResponse res = CommandFacade.executeCommand(
			DPKG_QUERY, "-W", "-f", QUERY_FORMAT, this.properties.getName()
		);
		
		if (!res.isOK()) {
			throw new PackageResourceException(res);
		}
		
		String[] match = StringHelper.match("^(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S*)$", res.getOutput());
		if (match == null) {
			throw new PackageResourceException("Failed to match dpkg-query " + res.getCommand());
		}
		
		return desiredStatus.equalsIgnoreCase(match[3]);
	}

	public void setPackageProperties(PackageProperties properties) {
		this.properties = properties;
	}

	public PackageProperties getPackageProperties() {
		return this.properties;
	}
}
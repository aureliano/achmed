package com.github.aureliano.achmed.os.pkg;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.exception.PackageResourceException;
import com.github.aureliano.achmed.helper.StringHelper;
import com.github.aureliano.achmed.resources.properties.PackageProperties;

public class DpkgPackageManager implements IPackageManager {
	
	private static final Logger logger = Logger.getLogger(DpkgPackageManager.class);
	
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
			logger.warn("Command [ " + res.getCommand() + " ] returned non zero status: " + res.getExitStatusCode() + " Error message: " + res.getError());
			return false;
		}
		
		String[] match = StringHelper.match("Status:\\s(\\S+\\s\\S+\\s\\S+)", res.getOutput());
		if (match == null) {
			throw new PackageResourceException("Failed to match dpkg-query [" + res.getOutput() + "]");
		}
		
		return desiredStatus.equalsIgnoreCase(match[1]);
	}

	public void setPackageProperties(PackageProperties properties) {
		this.properties = properties;
	}

	public PackageProperties getPackageProperties() {
		return this.properties;
	}
}
package com.github.aureliano.achmed.resources;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.AppConfiguration;
import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.exception.PackageResourceException;
import com.github.aureliano.achmed.os.pkg.IPackageManager;
import com.github.aureliano.achmed.os.pkg.PackageManagerFactory;
import com.github.aureliano.achmed.resources.properties.IResourceProperties;
import com.github.aureliano.achmed.resources.properties.PackageProperties;

public class PackageResource implements IResource {

	private static final Logger logger = Logger.getLogger(PackageResource.class);
	
	private PackageProperties properties;
	
	public PackageResource() {
		this.properties = new PackageProperties();
	}
	
	public PackageResource(PackageProperties properties) {
		this.properties = properties;
	}
	
	public void apply() {
		this.properties.configureAttributes();
		logger.info(" >>> Apply package resource to: " + this.properties.getName());
		
		IPackageManager packageManager = this.resolvePackageManager();
		packageManager.setPackageProperties(this.properties);
		CommandResponse res = null;
		
		if ("absent".equalsIgnoreCase(this.properties.getEnsure())) {
			res = this.uninstall(packageManager);
		} else {
			res = this.install(packageManager);
		}
		
		if ((res != null) && (!res.isOK())) {
			throw new PackageResourceException(res);
		}
	}
	
	private IPackageManager resolvePackageManager() {
		IPackageManager packageManager = null;
		
		if (this.properties.getProvider() != null) {
			packageManager = PackageManagerFactory.createPackageManager(this.properties.getProvider());
		} else {
			packageManager = AppConfiguration.instance().getOperatingSystem().getDefaultPackageManager();
		}
		
		packageManager.setPackageProperties(this.properties);
		return packageManager;
	}
	
	private CommandResponse install(IPackageManager packageManager) {
		if (packageManager.isInstalled()) {
			logger.info("Package " + this.properties.getName() + " is already installed. Skipping!");
			return null;
		}
		
		return packageManager.install();
	}
	
	private CommandResponse uninstall(IPackageManager packageManager) {
		if (!packageManager.isInstalled()) {
			logger.info("Package " + this.properties.getName() + " is not installed. Skipping!");
			return null;
		}
		
		return packageManager.uninstall();
	}
	
	public void setProperties(IResourceProperties properties) {
		this.properties = (PackageProperties) properties;
	}
	
	public IResourceProperties getProperties() {
		return this.properties;
	}

	public ResourceType type() {
		return ResourceType.PACKAGE;
	}
}
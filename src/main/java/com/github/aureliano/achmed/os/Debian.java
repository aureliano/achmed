package com.github.aureliano.achmed.os;

import com.github.aureliano.achmed.os.pkg.IPackageManager;
import com.github.aureliano.achmed.os.pkg.PackageManagerFactory;
import com.github.aureliano.achmed.os.service.DebianService;
import com.github.aureliano.achmed.os.service.IService;
import com.github.aureliano.achmed.types.OperatingSystemFamily;
import com.github.aureliano.achmed.types.PackageProvider;

public class Debian extends Linux {

	private IPackageManager packageManager;
	private IService serviceManager;
	
	public Debian() {
		this.packageManager = PackageManagerFactory.createPackageManager(PackageProvider.APT);
		this.serviceManager = new DebianService();
	}

	public IPackageManager getDefaultPackageManager() {
		return this.packageManager;
	}
	
	public IService getDefaultServiceManager() {
		return this.serviceManager;
	}

	public OperatingSystemFamily getOperatingSystemFamily() {
		return OperatingSystemFamily.DEBIAN;
	}
}
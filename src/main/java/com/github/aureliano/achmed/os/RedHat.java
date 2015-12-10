package com.github.aureliano.achmed.os;

import com.github.aureliano.achmed.os.pkg.IPackageManager;
import com.github.aureliano.achmed.os.pkg.PackageManagerFactory;
import com.github.aureliano.achmed.os.service.IService;
import com.github.aureliano.achmed.os.service.RedHatService;
import com.github.aureliano.achmed.types.OperatingSystemFamily;
import com.github.aureliano.achmed.types.PackageProvider;

public class RedHat extends Linux {

	private IPackageManager packageManager;
	private IService serviceManager;
	
	public RedHat() {
		this.packageManager = PackageManagerFactory.createPackageManager(PackageProvider.YUM);
		this.serviceManager = new RedHatService();
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
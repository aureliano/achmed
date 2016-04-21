package com.github.aureliano.achmed.os;

import com.github.aureliano.achmed.client.types.OperatingSystemFamily;
import com.github.aureliano.achmed.client.types.PackageProvider;
import com.github.aureliano.achmed.os.pkg.IPackageManager;
import com.github.aureliano.achmed.os.pkg.PackageManagerFactory;
import com.github.aureliano.achmed.os.service.IService;
import com.github.aureliano.achmed.os.service.RedHatService;

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
	
	@Override
	public Linux prototype() {
		RedHat os = new RedHat();
		
		os.packageManager = this.packageManager;
		os.serviceManager = this.serviceManager;
		
		return os;
	}
}
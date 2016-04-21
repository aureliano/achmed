package com.github.aureliano.achmed.os;

import com.github.aureliano.achmed.client.types.OperatingSystemFamily;
import com.github.aureliano.achmed.client.types.PackageProvider;
import com.github.aureliano.achmed.os.pkg.IPackageManager;
import com.github.aureliano.achmed.os.pkg.PackageManagerFactory;
import com.github.aureliano.achmed.os.service.DebianService;
import com.github.aureliano.achmed.os.service.IService;

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
	
	@Override
	public Linux prototype() {
		Debian os = new Debian();
		
		os.packageManager = this.packageManager;
		os.serviceManager = this.serviceManager;
		
		return os;
	}
}
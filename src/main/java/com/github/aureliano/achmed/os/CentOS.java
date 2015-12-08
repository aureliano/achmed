package com.github.aureliano.achmed.os;

import com.github.aureliano.achmed.os.pkg.IPackageManager;
import com.github.aureliano.achmed.os.pkg.PackageManagerFactory;
import com.github.aureliano.achmed.types.OperatingSystemFamily;
import com.github.aureliano.achmed.types.PackageProvider;

public class CentOS extends Linux {

	private IPackageManager packageManager;
	
	public CentOS() {
		this.packageManager = PackageManagerFactory.createPackageManager(PackageProvider.YUM);
	}

	public IPackageManager getDefaultPackageManager() {
		return this.packageManager;
	}

	public OperatingSystemFamily getOperatingSystemFamily() {
		return OperatingSystemFamily.RED_HAT;
	}
}
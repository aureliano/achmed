package com.github.aureliano.achmed.os;

import com.github.aureliano.achmed.os.pkg.IPackageManager;
import com.github.aureliano.achmed.os.pkg.PackageManagerFactory;
import com.github.aureliano.achmed.resources.types.OS;
import com.github.aureliano.achmed.resources.types.OperatingSystemFamily;
import com.github.aureliano.achmed.resources.types.PackageProvider;

public class Ubuntu implements IOperatingSystem {
	
	private IPackageManager packageManager;
	
	public Ubuntu() {
		this.packageManager = PackageManagerFactory.createPackageManager(PackageProvider.APT);
	}

	public IPackageManager getDefaultPackageManager() {
		return this.packageManager;
	}

	public OS getOperatingSystem() {
		return OS.LINUX;
	}

	public OperatingSystemFamily getOperatingSystemFamily() {
		return OperatingSystemFamily.UBUNTU;
	}
}
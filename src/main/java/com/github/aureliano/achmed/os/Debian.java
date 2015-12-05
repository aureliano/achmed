package com.github.aureliano.achmed.os;

import com.github.aureliano.achmed.os.pkg.IPackageManager;
import com.github.aureliano.achmed.os.pkg.PackageManagerFactory;
import com.github.aureliano.achmed.types.OS;
import com.github.aureliano.achmed.types.OperatingSystemFamily;
import com.github.aureliano.achmed.types.PackageProvider;

public class Debian implements IOperatingSystem {

	private IPackageManager packageManager;
	
	public Debian() {
		this.packageManager = PackageManagerFactory.createPackageManager(PackageProvider.APT);
	}

	public IPackageManager getDefaultPackageManager() {
		return this.packageManager;
	}

	public OS getOperatingSystem() {
		return OS.LINUX;
	}

	public OperatingSystemFamily getOperatingSystemFamily() {
		return OperatingSystemFamily.DEBIAN;
	}
	
	public String getPsCommand() {
		return "ps -ef";
	}
}
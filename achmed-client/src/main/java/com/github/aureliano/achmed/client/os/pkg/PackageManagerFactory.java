package com.github.aureliano.achmed.client.os.pkg;

import com.github.aureliano.achmed.client.exception.UnsupportedPackageManagerException;
import com.github.aureliano.achmed.client.types.PackageProvider;

public class PackageManagerFactory {

	private PackageManagerFactory() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
	
	public static IPackageManager createPackageManager(PackageProvider pkg) {
		switch (pkg) {
			case APT : return new AptPackageManager();
			case GEM : return new GemPackageManager();
			case YUM : return new YumPackageManager();
			case DPKG: return new DpkgPackageManager();
			default : throw new UnsupportedPackageManagerException("There is no such package manager implementation to '" + pkg + "' type.");
		}
	}
}
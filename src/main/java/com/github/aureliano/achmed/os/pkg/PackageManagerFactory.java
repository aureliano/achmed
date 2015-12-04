package com.github.aureliano.achmed.os.pkg;

import com.github.aureliano.achmed.exception.UnsupportedPackageManagerException;
import com.github.aureliano.achmed.resources.types.PackageProvider;

public class PackageManagerFactory {

	private PackageManagerFactory() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
	
	public static IPackageManager createPackageManager(PackageProvider pkg) {
		switch (pkg) {
			case APT : return new AptPackageManager();
			case GEM : return new GemPackageManager();
			case YUM : return new YumPackageManager();
			default : throw new UnsupportedPackageManagerException("There is no such package manager implementation to '" + pkg + "' type.");
		}
	}
}
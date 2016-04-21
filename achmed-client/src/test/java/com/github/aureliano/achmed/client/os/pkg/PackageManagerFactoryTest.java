package com.github.aureliano.achmed.client.os.pkg;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aureliano.achmed.client.os.pkg.AptPackageManager;
import com.github.aureliano.achmed.client.os.pkg.DpkgPackageManager;
import com.github.aureliano.achmed.client.os.pkg.GemPackageManager;
import com.github.aureliano.achmed.client.os.pkg.IPackageManager;
import com.github.aureliano.achmed.client.os.pkg.PackageManagerFactory;
import com.github.aureliano.achmed.client.os.pkg.YumPackageManager;
import com.github.aureliano.achmed.client.types.PackageProvider;

public class PackageManagerFactoryTest {

	@Test
	public void testCreatePackageManager() {
		IPackageManager pkg = null;
		
		pkg = PackageManagerFactory.createPackageManager(PackageProvider.APT);
		assertTrue(pkg instanceof AptPackageManager);
		
		pkg = PackageManagerFactory.createPackageManager(PackageProvider.YUM);
		assertTrue(pkg instanceof YumPackageManager);
		
		pkg = PackageManagerFactory.createPackageManager(PackageProvider.DPKG);
		assertTrue(pkg instanceof DpkgPackageManager);
		
		pkg = PackageManagerFactory.createPackageManager(PackageProvider.GEM);
		assertTrue(pkg instanceof GemPackageManager);
	}
}
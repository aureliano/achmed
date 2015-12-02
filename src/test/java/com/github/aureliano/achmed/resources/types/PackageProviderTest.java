package com.github.aureliano.achmed.resources.types;

import static org.junit.Assert.assertEquals;

import static com.github.aureliano.achmed.resources.types.PackageProvider.*;
import static com.github.aureliano.achmed.resources.types.OperatingSystemFamily.*;

import org.junit.Test;

public class PackageProviderTest {

	@Test
	public void testOperatingSystemFamily() {
		assertEquals(DEBIAN, APT.getOperatingSystemFamily());
		assertEquals(RPM, YUM.getOperatingSystemFamily());
		assertEquals(APP, GEM.getOperatingSystemFamily());
	}
	
	@Test
	public void testPackageManagement() {
		assertEquals("apt-get", APT.getPackageManagement());
		assertEquals("yum", YUM.getPackageManagement());
		assertEquals("gem", GEM.getPackageManagement());
	}
}
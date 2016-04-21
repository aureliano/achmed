package com.github.aureliano.achmed.types;

import static org.junit.Assert.assertEquals;

import static com.github.aureliano.achmed.client.types.OperatingSystemFamily.*;
import static com.github.aureliano.achmed.client.types.PackageProvider.*;

import org.junit.Test;

public class PackageProviderTest {

	@Test
	public void testOperatingSystemFamily() {
		assertEquals(DEBIAN, APT.getOperatingSystemFamily());
		assertEquals(RED_HAT, YUM.getOperatingSystemFamily());
		assertEquals(DEBIAN, DPKG.getOperatingSystemFamily());
		assertEquals(APP, GEM.getOperatingSystemFamily());
	}
	
	@Test
	public void testPackageManagement() {
		assertEquals("apt-get", APT.getPackageManagement());
		assertEquals("yum", YUM.getPackageManagement());
		assertEquals("dpkg", DPKG.getPackageManagement());
		assertEquals("gem", GEM.getPackageManagement());
	}
}
package com.github.aureliano.achmed.resources.types;

import static com.github.aureliano.achmed.resources.types.OperatingSystemFamily.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OperatingSystemFamilyTest {

	@Test
	public void testGetOperatingSystem() {
		assertEquals(OS.LINUX, DEBIAN.getOperatingSystem());
		assertEquals(OS.LINUX, RPM.getOperatingSystem());
		assertEquals(null, APP.getOperatingSystem());
		assertEquals(OS.LINUX, UBUNTU.getOperatingSystem());
	}

	@Test
	public void testGetAncestor() {
		assertEquals(null, DEBIAN.getAncestor());
		assertEquals(null, RPM.getAncestor());
		assertEquals(null, APP.getAncestor());
		assertEquals(DEBIAN, UBUNTU.getAncestor());
	}
}
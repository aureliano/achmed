package com.github.aureliano.achmed.types;

import static com.github.aureliano.achmed.client.types.OperatingSystemFamily.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.aureliano.achmed.client.types.OS;

public class OperatingSystemFamilyTest {

	@Test
	public void testGetOperatingSystem() {
		assertEquals(OS.LINUX, DEBIAN.getOperatingSystem());
		assertEquals(OS.LINUX, RED_HAT.getOperatingSystem());
		assertEquals(null, APP.getOperatingSystem());
		assertEquals(OS.LINUX, UBUNTU.getOperatingSystem());
	}

	@Test
	public void testGetAncestor() {
		assertEquals(null, DEBIAN.getAncestor());
		assertEquals(null, RED_HAT.getAncestor());
		assertEquals(null, APP.getAncestor());
		assertEquals(DEBIAN, UBUNTU.getAncestor());
	}
}
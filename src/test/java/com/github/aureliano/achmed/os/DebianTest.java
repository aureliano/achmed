package com.github.aureliano.achmed.os;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aureliano.achmed.client.types.OS;
import com.github.aureliano.achmed.client.types.OperatingSystemFamily;
import com.github.aureliano.achmed.os.pkg.AptPackageManager;

public class DebianTest {
	
	@Test
	public void testGetDefaultPackageManager() {
		Debian os = new Debian();
		assertTrue(os.getDefaultPackageManager() instanceof AptPackageManager);
	}

	@Test
	public void testGetOperatingSystem() {
		Debian os = new Debian();
		assertEquals(OS.LINUX, os.getOperatingSystem());
	}
	
	@Test
	public void testGetOperatingSystemFamily() {
		Debian os = new Debian();
		assertEquals(OperatingSystemFamily.DEBIAN, os.getOperatingSystemFamily());
	}
}
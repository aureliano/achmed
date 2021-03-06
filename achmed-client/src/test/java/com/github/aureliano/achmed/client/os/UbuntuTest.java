package com.github.aureliano.achmed.client.os;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aureliano.achmed.client.os.Ubuntu;
import com.github.aureliano.achmed.client.os.pkg.AptPackageManager;
import com.github.aureliano.achmed.client.types.OS;
import com.github.aureliano.achmed.client.types.OperatingSystemFamily;

public class UbuntuTest {
	
	@Test
	public void testGetDefaultPackageManager() {
		Ubuntu os = new Ubuntu();
		assertTrue(os.getDefaultPackageManager() instanceof AptPackageManager);
	}

	@Test
	public void testGetOperatingSystem() {
		Ubuntu os = new Ubuntu();
		assertEquals(OS.LINUX, os.getOperatingSystem());
	}
	
	@Test
	public void testGetOperatingSystemFamily() {
		Ubuntu os = new Ubuntu();
		assertEquals(OperatingSystemFamily.UBUNTU, os.getOperatingSystemFamily());
	}
}
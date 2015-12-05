package com.github.aureliano.achmed.os;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aureliano.achmed.os.pkg.YumPackageManager;
import com.github.aureliano.achmed.types.OS;
import com.github.aureliano.achmed.types.OperatingSystemFamily;

public class CentOSTest {
	
	@Test
	public void testGetDefaultPackageManager() {
		CentOS os = new CentOS();
		assertTrue(os.getDefaultPackageManager() instanceof YumPackageManager);
	}

	@Test
	public void testGetOperatingSystem() {
		CentOS os = new CentOS();
		assertEquals(OS.LINUX, os.getOperatingSystem());
	}
	
	@Test
	public void testGetOperatingSystemFamily() {
		CentOS os = new CentOS();
		assertEquals(OperatingSystemFamily.RED_HAT, os.getOperatingSystemFamily());
	}
}
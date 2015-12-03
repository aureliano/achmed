package com.github.aureliano.achmed.os;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.aureliano.achmed.resources.types.OS;
import com.github.aureliano.achmed.resources.types.OperatingSystemFamily;

public class CentOSTest {

	@Test
	public void testGetOperatingSystem() {
		CentOS os = new CentOS();
		assertEquals(OS.LINUX, os.getOperatingSystem());
	}
	
	@Test
	public void testGetOperatingSystemFamily() {
		CentOS os = new CentOS();
		assertEquals(OperatingSystemFamily.RPM, os.getOperatingSystemFamily());
	}
}
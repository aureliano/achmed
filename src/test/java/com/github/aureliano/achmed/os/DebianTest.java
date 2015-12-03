package com.github.aureliano.achmed.os;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.aureliano.achmed.resources.types.OS;
import com.github.aureliano.achmed.resources.types.OperatingSystemFamily;

public class DebianTest {

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
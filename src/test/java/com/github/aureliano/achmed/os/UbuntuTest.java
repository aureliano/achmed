package com.github.aureliano.achmed.os;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.aureliano.achmed.resources.types.OS;
import com.github.aureliano.achmed.resources.types.OperatingSystemFamily;

public class UbuntuTest {

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
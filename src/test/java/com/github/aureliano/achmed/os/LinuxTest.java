package com.github.aureliano.achmed.os;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.aureliano.achmed.os.pkg.IPackageManager;
import com.github.aureliano.achmed.types.OperatingSystemFamily;

public class LinuxTest {

	@Test
	public void testGetPsCommand() {
		final String cmd = "ps -ef";
		assertEquals(cmd, this.createDefaultOs().getPsCommand());
	}
	
	private Linux createDefaultOs() {
		return new Linux() {
			
			public OperatingSystemFamily getOperatingSystemFamily() {
				return null;
			}
			
			public IPackageManager getDefaultPackageManager() {
				return null;
			}
		};
	}
}
package com.github.aureliano.achmed.os;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.aureliano.achmed.exception.ServiceResourceException;
import com.github.aureliano.achmed.os.pkg.IPackageManager;
import com.github.aureliano.achmed.types.OS;
import com.github.aureliano.achmed.types.OperatingSystemFamily;

public class LinuxTest {

	private Linux linux;
	
	public LinuxTest() {
		this.linux = this.createDefaultOs();
	}
	
	@Test
	public void testGetOperatingSystem() {
		assertEquals(OS.LINUX, this.linux.getOperatingSystem());
	}
	
	@Test
	public void testGetPsCommand() {
		final String cmd = "ps -ef";
		assertEquals(cmd, this.linux.getPsCommand());
	}
	
	@Test(expected = ServiceResourceException.class)
	public void testGetPidWithNullPattern() {
		this.linux.getPid(null);
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
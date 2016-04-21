package com.github.aureliano.achmed.os;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import com.github.aureliano.achmed.client.exception.ServiceResourceException;
import com.github.aureliano.achmed.client.types.OS;
import com.github.aureliano.achmed.client.types.OperatingSystemFamily;
import com.github.aureliano.achmed.os.pkg.IPackageManager;
import com.github.aureliano.achmed.os.service.IService;

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
	
	@Test(expected = ServiceResourceException.class)
	public void testGetPidWithEmptyPattern() {
		this.linux.getPid("");
	}
	
	@Test
	public void testMatchPid() {
		StringBuilder b = null;
		try (Scanner s = new Scanner(new File("src/test/resources/os/process-table"))) {
			b = new StringBuilder();
			
			while (s.hasNextLine()) {
				b.append(s.nextLine()).append("\n");
			}
		} catch (FileNotFoundException ex) {
			throw new RuntimeException(ex);
		}
		
		String regex = "^\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\S+\\s\\S+\\s+\\S+\\s+\\./eclipse$";
		Integer pid = this.linux.matchPid(b.toString(), regex);
		
		assertEquals(new Integer(2586), pid);
	}
	
	private Linux createDefaultOs() {
		return new Linux() {
			
			public OperatingSystemFamily getOperatingSystemFamily() {
				return null;
			}
			
			public IPackageManager getDefaultPackageManager() {
				return null;
			}

			public IService getDefaultServiceManager() {
				return null;
			}
			
			public Linux prototype() {
				return null;
			}
		};
	}
}
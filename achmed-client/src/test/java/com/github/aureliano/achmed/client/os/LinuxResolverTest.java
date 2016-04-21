package com.github.aureliano.achmed.client.os;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aureliano.achmed.client.os.CentOS;
import com.github.aureliano.achmed.client.os.Debian;
import com.github.aureliano.achmed.client.os.IOperatingSystem;
import com.github.aureliano.achmed.client.os.LinuxResolver;
import com.github.aureliano.achmed.client.os.Ubuntu;

public class LinuxResolverTest {

	@Test
	public void testResolveLinux() {
		IOperatingSystem os = LinuxResolver.resolveLinux("Debian");
		assertTrue(os instanceof Debian);
		
		os = LinuxResolver.resolveLinux("Ubuntu");
		assertTrue(os instanceof Ubuntu);
		
		os = LinuxResolver.resolveLinux("CentOS");
		assertTrue(os instanceof CentOS);
	}
}
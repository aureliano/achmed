package com.github.aureliano.achmed.os;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
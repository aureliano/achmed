package com.github.aureliano.achmed.resources.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.github.aureliano.achmed.types.DebianConfigFilesStatus;
import com.github.aureliano.achmed.types.PackageProvider;

public class PackagePropertiesTest {
	
	@Test
	public void testDefaultValues() {
		PackageProperties pkg = new PackageProperties();
		
		assertEquals("install", pkg.getEnsure());
		assertEquals(DebianConfigFilesStatus.KEEP, pkg.getConfigFiles());
		assertTrue(pkg.getInstallOptions().isEmpty());
		assertTrue(pkg.getUninstallOptions().isEmpty());
	}

	@Test
	public void testConfigureAttributes() {
		PackageProperties pkg = (PackageProperties) new PackageProperties()
			.put("name", "pkg")
			.put("provider", PackageProvider.APT)
			.put("configFiles", DebianConfigFilesStatus.REPLACE)
			.put("ensure", "latest")
			.put("installOptions", Arrays.asList("-i test"))
			.put("uninstallOptions", Arrays.asList("-u test"))
			.put("source", "src")
			.configureAttributes();
		
		assertEquals("pkg", pkg.getName());
		assertEquals(PackageProvider.APT, pkg.getProvider());
		assertEquals(DebianConfigFilesStatus.REPLACE, pkg.getConfigFiles());
		assertEquals("latest", pkg.getEnsure());
		assertEquals(Arrays.asList("-i test"), pkg.getInstallOptions());
		assertEquals(Arrays.asList("-u test"), pkg.getUninstallOptions());
		assertEquals("src", pkg.getSource());
		
		pkg.setProvider(null);
		assertNull(pkg.getProvider());
		
		pkg.put("provider", "apt").configureAttributes();
		assertEquals(PackageProvider.APT, pkg.getProvider());
		
		pkg.setConfigFiles(null);
		assertNull(pkg.getConfigFiles());
		
		pkg.put("configfiles", "replace").configureAttributes();
		assertEquals(DebianConfigFilesStatus.REPLACE, pkg.getConfigFiles());
	}

	@Test
	public void testHashCode() {
		PackageProperties p1 = new PackageProperties();
		PackageProperties p2 = new PackageProperties();
		
		assertEquals(p1.hashCode(), p2.hashCode());
		
		p1.setName("pkg");
		assertFalse(p1.hashCode() == p2.hashCode());
		
		p2.setName("pkg");
		assertTrue(p1.hashCode() == p2.hashCode());
		
		p1.setProvider(PackageProvider.APT);
		assertFalse(p1.hashCode() == p2.hashCode());
		
		p2.setProvider(PackageProvider.APT);
		assertTrue(p1.hashCode() == p2.hashCode());
	}
	
	@Test
	public void testEquals() {
		PackageProperties p1 = new PackageProperties();
		PackageProperties p2 = new PackageProperties();
		
		assertEquals(p1, p2);
		
		p1.setName("pkg");
		assertFalse(p1.equals(p2));
		
		p2.setName("pkg");
		assertTrue(p1.equals(p2));
		
		p1.setProvider(PackageProvider.APT);
		assertFalse(p1.equals(p2));
		
		p2.setProvider(PackageProvider.APT);
		assertTrue(p1.equals(p2));
	}
}
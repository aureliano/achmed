package com.github.aureliano.achmed.resources.properties;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.github.aureliano.achmed.resources.types.PackageProvider;

public class PackagePropertiesTest {

	@Test
	public void testConfigureAttributes() {
		PackageProperties pkg = (PackageProperties) new PackageProperties()
			.put("name", "pkg")
			.put("provider", PackageProvider.APT)
			.put("ensure", "latest")
			.put("installOptions", Arrays.asList("-i test"))
			.put("uninstallOptions", Arrays.asList("-u test"))
			.put("source", "src")
			.configureAttributes();
		
		Assert.assertEquals("pkg", pkg.getName());
		Assert.assertEquals(PackageProvider.APT, pkg.getProvider());
		Assert.assertEquals("latest", pkg.getEnsure());
		Assert.assertEquals(Arrays.asList("-i test"), pkg.getInstallOptions());
		Assert.assertEquals(Arrays.asList("-u test"), pkg.getUninstallOptions());
		Assert.assertEquals("src", pkg.getSource());
	}

	@Test
	public void testHashCode() {
		PackageProperties p1 = new PackageProperties();
		PackageProperties p2 = new PackageProperties();
		
		Assert.assertEquals(p1.hashCode(), p2.hashCode());
		
		p1.setName("pkg");
		Assert.assertFalse(p1.hashCode() == p2.hashCode());
		
		p2.setName("pkg");
		Assert.assertTrue(p1.hashCode() == p2.hashCode());
		
		p1.setProvider(PackageProvider.APT);
		Assert.assertFalse(p1.hashCode() == p2.hashCode());
		
		p2.setProvider(PackageProvider.APT);
		Assert.assertTrue(p1.hashCode() == p2.hashCode());
	}
	
	@Test
	public void testEquals() {
		PackageProperties p1 = new PackageProperties();
		PackageProperties p2 = new PackageProperties();
		
		Assert.assertEquals(p1, p2);
		
		p1.setName("pkg");
		Assert.assertFalse(p1.equals(p2));
		
		p2.setName("pkg");
		Assert.assertTrue(p1.equals(p2));
		
		p1.setProvider(PackageProvider.APT);
		Assert.assertFalse(p1.equals(p2));
		
		p2.setProvider(PackageProvider.APT);
		Assert.assertTrue(p1.equals(p2));
	}
}
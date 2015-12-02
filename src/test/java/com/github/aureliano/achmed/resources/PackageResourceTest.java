package com.github.aureliano.achmed.resources;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.github.aureliano.achmed.resources.properties.PackageProperties;
import com.github.aureliano.achmed.resources.types.PackageProvider;

public class PackageResourceTest {

	@Test(expected = UnsupportedOperationException.class)
	public void testApply() {
		PackageResource r = new PackageResource();
		r.apply();
	}
	
	@Test
	public void testType() {
		PackageResource r = new PackageResource();
		assertEquals(ResourceType.PACKAGE, r.type());
	}
	
	@Test
	public void testGetProperties() {
		PackageResource r = new PackageResource(this.createProperties());
		
		PackageProperties p = (PackageProperties) r.getProperties();
		this.checkProperties(p);
		
		r = new PackageResource();
		r.setProperties(this.createProperties());
		
		p = (PackageProperties) r.getProperties();
		this.checkProperties(p);
	}

	private PackageProperties createProperties() {
		PackageProperties p = new PackageProperties();
		
		p.setName("pkg");
		p.setProvider(PackageProvider.APT);
		p.setEnsure("latest");
		p.setInstallOptions(Arrays.asList("-i test"));
		p.setUninstallOptions(Arrays.asList("-u test"));
		p.setSource("src");
		
		return p;
	}

	private void checkProperties(PackageProperties p) {
		assertEquals("pkg", p.getName());
		assertEquals(PackageProvider.APT, p.getProvider());
		assertEquals("latest", p.getEnsure());
		assertEquals(Arrays.asList("-i test"), p.getInstallOptions());
		assertEquals(Arrays.asList("-u test"), p.getUninstallOptions());
		assertEquals("src", p.getSource());
	}
}
package com.github.aureliano.achmed.resources;

import org.junit.Test;

import com.github.aureliano.achmed.exception.NoSuchResourceException;

public class ResourceFactoryTest {

	@Test(expected = NoSuchResourceException.class)
	public void testCreateFileResource() {
		ResourceFactory.createResource(ResourceType.FILE);
	}
	
	@Test(expected = NoSuchResourceException.class)
	public void testCreateFileResourceByTypeName() {
		ResourceFactory.createResource("file");
	}

	@Test(expected = NoSuchResourceException.class)
	public void testCreateExecResource() {
		ResourceFactory.createResource(ResourceType.EXEC);
	}
	
	@Test(expected = NoSuchResourceException.class)
	public void testCreateExecResourceByTypeName() {
		ResourceFactory.createResource("exec");
	}

	@Test(expected = NoSuchResourceException.class)
	public void testCreatePackageResource() {
		ResourceFactory.createResource(ResourceType.PACKAGE);
	}
	
	@Test(expected = NoSuchResourceException.class)
	public void testCreatePackageResourceByTypeName() {
		ResourceFactory.createResource("package");
	}

	@Test(expected = NoSuchResourceException.class)
	public void testCreateServiceResource() {
		ResourceFactory.createResource(ResourceType.SERVICE);
	}
	
	@Test(expected = NoSuchResourceException.class)
	public void testCreateServiceResourceByTypeName() {
		ResourceFactory.createResource("service");
	}
	
	@Test(expected = NoSuchResourceException.class)
	public void testCreateResourceByEmptyTypeName() {
		ResourceFactory.createResource("");
	}
	
	@Test(expected = NoSuchResourceException.class)
	public void testCreateResourceByInvalidTypeName() {
		ResourceFactory.createResource("NonExistentType");
	}
}
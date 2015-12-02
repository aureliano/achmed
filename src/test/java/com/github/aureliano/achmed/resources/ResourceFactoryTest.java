package com.github.aureliano.achmed.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

	public void testCreateExecResource() {
		IResource resource = ResourceFactory.createResource(ResourceType.EXEC);
		assertEquals(ResourceType.EXEC, resource.type());
		assertTrue(resource instanceof ExecResource);
	}
	
	public void testCreateExecResourceByTypeName() {
		IResource resource = ResourceFactory.createResource("exec");
		assertEquals(ResourceType.EXEC, resource.type());
		assertTrue(resource instanceof ExecResource);
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
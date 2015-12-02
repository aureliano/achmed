package com.github.aureliano.achmed.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aureliano.achmed.exception.NoSuchResourceException;

public class ResourceFactoryTest {

	public void testCreateFileResource() {
		IResource resource = ResourceFactory.createResource(ResourceType.FILE);
		assertEquals(ResourceType.FILE, resource.type());
		assertTrue(resource instanceof FileResource);
	}
	
	public void testCreateFileResourceByTypeName() {
		IResource resource = ResourceFactory.createResource("file");
		assertEquals(ResourceType.FILE, resource.type());
		assertTrue(resource instanceof FileResource);
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

	public void testCreatePackageResource() {
		IResource resource = ResourceFactory.createResource(ResourceType.PACKAGE);
		assertEquals(ResourceType.PACKAGE, resource.type());
		assertTrue(resource instanceof PackageResource);
	}
	
	public void testCreatePackageResourceByTypeName() {
		IResource resource = ResourceFactory.createResource("package");
		assertEquals(ResourceType.PACKAGE, resource.type());
		assertTrue(resource instanceof PackageResource);
	}

	public void testCreateServiceResource() {
		IResource resource = ResourceFactory.createResource(ResourceType.SERVICE);
		assertEquals(ResourceType.SERVICE, resource.type());
		assertTrue(resource instanceof ServiceResource);
	}
	
	public void testCreateServiceResourceByTypeName() {
		IResource resource = ResourceFactory.createResource("service");
		assertEquals(ResourceType.SERVICE, resource.type());
		assertTrue(resource instanceof ServiceResource);
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
package com.github.aureliano.achmed.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.github.aureliano.achmed.resources.ExecResource;
import com.github.aureliano.achmed.resources.FileResource;
import com.github.aureliano.achmed.resources.IResource;
import com.github.aureliano.achmed.resources.PackageResource;
import com.github.aureliano.achmed.resources.ServiceResource;

public class ApplicationHelperTest {

	private static final String RESOURCES_DIR = "src/test/resources";
	
	@Test
	public void testBuildAllResources() {
		String path = this.buildPath(RESOURCES_DIR, "yaml", "main.yaml");
		List<IResource> resources = ApplicationHelper.buildAllResources(path);
		
		int expectedResources = 4;
		assertEquals(expectedResources, resources.size());
		
		assertTrue(resources.get(0) instanceof ExecResource);
		assertTrue(resources.get(1) instanceof FileResource);
		assertTrue(resources.get(2) instanceof PackageResource);
		assertTrue(resources.get(3) instanceof ServiceResource);
	}
	
	private String buildPath(String...tokens) {
		return StringHelper.join(tokens, File.separator);
	}
}
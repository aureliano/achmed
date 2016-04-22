package com.github.aureliano.achmed.client.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.github.aureliano.achmed.client.resources.ExecResource;
import com.github.aureliano.achmed.client.resources.FileResource;
import com.github.aureliano.achmed.client.resources.IResource;
import com.github.aureliano.achmed.client.resources.PackageResource;
import com.github.aureliano.achmed.client.resources.ServiceResource;
import com.github.aureliano.achmed.common.helper.StringHelper;

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
	
	@Test
	public void testHelp() {
		String help = ApplicationHelper.help();
		
		assertTrue(help.contains("Usage: [java -jar] achmed-client-x.x.x.jar burst [FILE]"));
		assertTrue(help.contains("-h        Print this message."));
		assertTrue(help.contains("-v        Print the version."));
		assertTrue(help.contains("--help    Print this message."));
		assertTrue(help.contains("--version Print the version."));
		assertTrue(help.contains("Required JVM 1.7 or higher."));
		assertTrue(help.contains("Example:"));
		assertTrue(help.contains("java -jar achmed-client-x.x.x.jar burst /path/to/schema.yaml"));
		assertTrue(help.contains("Get involved"));
		assertTrue(help.contains("- Source: https://github.com/aureliano/achmed"));
		assertTrue(help.contains("- Issues: https://github.com/aureliano/achmed/issues"));
		assertTrue(help.contains("- Wiki: https://github.com/aureliano/achmed/wiki for more detailed insights."));
	}
	
	@Test
	public void testVersion() {
		String version = ApplicationHelper.version();
		assertTrue(version.contains("0.1.0:BETA-01 (2015-12-15)"));
	}
	
	@Test
	public void testError() {
		String param = "malabibala";
		String expected = "Don't know how to handle such command: " + param;
		String actual = ApplicationHelper.error(new String[] { param });
		
		assertEquals(expected, actual);
		
		expected = "SILENCE...! I kill you!";
		actual = ApplicationHelper.error(new String[] {});
		assertEquals(expected, actual);
	}
	
	private String buildPath(String...tokens) {
		return StringHelper.join(tokens, File.separator);
	}
}
package com.github.aureliano.achmed.server.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.github.aureliano.achmed.server.conf.ServerConfiguration;

public class ApplicationHelperTest {

	@Test
	public void testBuildServerConfiguration() {
		ServerConfiguration configuration = ApplicationHelper.buildServerConfiguration();
		File repo = new File("/tmp");
		
		assertEquals(new Integer(9876), configuration.getPortNumber());
		assertEquals(repo.getAbsolutePath(), configuration.getFileRepository());
	}
	
	@Test
	public void testHelp() {
		String help = ApplicationHelper.help();
		
		assertTrue(help.contains("Usage: [java -jar] achmed-server-x.x.x.jar"));
		assertTrue(help.contains("-h        Print this message."));
		assertTrue(help.contains("-v        Print the version."));
		assertTrue(help.contains("--help    Print this message."));
		assertTrue(help.contains("--version Print the version."));
		assertTrue(help.contains("You have to create a file at $ACHMED_SERVER_HOME named achmed-server.properties in order to configure the server."));
		assertTrue(help.contains("File must looks like this:"));
		assertTrue(help.contains("port=9876"));
		assertTrue(help.contains("file.repository=/path/to/file/repository"));
		assertTrue(help.contains("Required JVM 1.7 or higher."));
		assertTrue(help.contains("Example:"));
		assertTrue(help.contains("java -jar achmed-server-x.x.x.jar"));
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
	}
}
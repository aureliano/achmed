package com.github.aureliano.achmed.server.conf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Properties;

import org.junit.Test;

import com.github.aureliano.achmed.common.exception.AchmedException;

public class ServerConfigurationTest {

	@Test
	public void testInstance() {
		ServerConfiguration c = ServerConfiguration.instance();
		assertNotNull(c);
		assertTrue(c == ServerConfiguration.instance());
	}
	
	@Test(expected = AchmedException.class)
	public void testBuildWithError1() {
		Properties properties = new Properties();
		properties.setProperty("file.repository", new File("").getAbsolutePath());
		
		ServerConfiguration.build(properties);
	}

	@Test(expected = AchmedException.class)
	public void testBuildWithError2() {
		Properties properties = new Properties();
		properties.setProperty("port", "9876");
		
		ServerConfiguration.build(properties);
	}
	
	@Test
	public void testBuild() {
		File repo = new File("");
		Properties properties = new Properties();
		properties.setProperty("port", "9876");
		properties.setProperty("file.repository", repo.getAbsolutePath() + File.separator);
		
		ServerConfiguration conf = ServerConfiguration.build(properties);
		assertEquals(new Integer(9876), conf.getPortNumber());
		assertEquals(repo.getAbsolutePath(), conf.getFileRepository());
	}
}
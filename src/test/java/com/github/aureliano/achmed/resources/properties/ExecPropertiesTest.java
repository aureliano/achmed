package com.github.aureliano.achmed.resources.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class ExecPropertiesTest {
	
	@Test
	public void testDefaultValues() {
		ExecProperties exec = new ExecProperties();
		
		assertEquals(new File("").getAbsolutePath(), exec.getCwd());
		assertEquals(true, exec.isVerbose());
		assertEquals(new Long(300000), exec.getTimeout());
		assertEquals(new Integer(1), exec.getTries());
	}

	@Test
	public void testConfigureAttributes() {
		ExecProperties exec = (ExecProperties) new ExecProperties()
			.put("command", "echo something")
			.put("cwd", "/path/to/some/directory")
			.put("verbose", true)
			.put("onlyIf", "another command")
			.put("unless", "any other command")
			.put("timeout", 10000)
			.put("tries", 3)
			.configureAttributes();
		
		assertEquals("echo something", exec.getCommand());
		assertEquals("/path/to/some/directory", exec.getCwd());
		assertTrue(exec.isVerbose());
		assertEquals("another command", exec.getOnlyIf());
		assertEquals("any other command", exec.getUnless());
		assertEquals(new Long(10000), exec.getTimeout());
		assertEquals(new Integer(3), exec.getTries());
	}
	
	@Test
	public void testHashCode() {
		ExecProperties e1 = new ExecProperties();
		ExecProperties e2 = new ExecProperties();
		
		assertEquals(e1.hashCode(), e2.hashCode());
		
		e1.setCommand("cmd");
		assertFalse(e1.hashCode() == e2.hashCode());
		
		e2.setCommand("cmd");
		assertTrue(e1.hashCode() == e2.hashCode());
		
		e1.setCwd("dir");
		assertFalse(e1.hashCode() == e2.hashCode());
		
		e2.setCwd("dir");
		assertTrue(e1.hashCode() == e2.hashCode());
	}
	
	@Test
	public void testEquals() {
		ExecProperties e1 = new ExecProperties();
		ExecProperties e2 = new ExecProperties();
		
		assertEquals(e1, e2);
		
		e1.setCommand("cmd");
		assertFalse(e1.equals(e2));
		
		e2.setCommand("cmd");
		assertTrue(e1.equals(e2));
		
		e1.setCwd("dir");
		assertFalse(e1.equals(e2));
		
		e2.setCwd("dir");
		assertTrue(e1.equals(e2));
	}
}
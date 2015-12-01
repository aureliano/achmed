package com.github.aureliano.achmed.resources.properties;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class ExecPropertiesTest {
	
	@Test
	public void testDefaultValues() {
		ExecProperties exec = new ExecProperties();
		
		Assert.assertEquals(new File("").getAbsolutePath(), exec.getCwd());
		Assert.assertEquals(true, exec.isVerbose());
		Assert.assertEquals(new Long(300000), exec.getTimeout());
		Assert.assertEquals(new Integer(1), exec.getTries());
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
		
		Assert.assertEquals("echo something", exec.getCommand());
		Assert.assertEquals("/path/to/some/directory", exec.getCwd());
		Assert.assertTrue(exec.isVerbose());
		Assert.assertEquals("another command", exec.getOnlyIf());
		Assert.assertEquals("any other command", exec.getUnless());
		Assert.assertEquals(new Long(10000), exec.getTimeout());
		Assert.assertEquals(new Integer(3), exec.getTries());
	}
	
	@Test
	public void testHashCode() {
		ExecProperties e1 = new ExecProperties();
		ExecProperties e2 = new ExecProperties();
		
		Assert.assertEquals(e1.hashCode(), e2.hashCode());
		
		e1.setCommand("cmd");
		Assert.assertFalse(e1.hashCode() == e2.hashCode());
		
		e2.setCommand("cmd");
		Assert.assertTrue(e1.hashCode() == e2.hashCode());
		
		e1.setCwd("dir");
		Assert.assertFalse(e1.hashCode() == e2.hashCode());
		
		e2.setCwd("dir");
		Assert.assertTrue(e1.hashCode() == e2.hashCode());
	}
	
	@Test
	public void testEquals() {
		ExecProperties e1 = new ExecProperties();
		ExecProperties e2 = new ExecProperties();
		
		Assert.assertEquals(e1, e2);
		
		e1.setCommand("cmd");
		Assert.assertFalse(e1.equals(e2));
		
		e2.setCommand("cmd");
		Assert.assertTrue(e1.equals(e2));
		
		e1.setCwd("dir");
		Assert.assertFalse(e1.equals(e2));
		
		e2.setCwd("dir");
		Assert.assertTrue(e1.equals(e2));
	}
}
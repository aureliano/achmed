package com.github.aureliano.achmed.client.resources.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

import com.github.aureliano.achmed.client.resources.properties.ExecProperties;
import com.github.aureliano.achmed.validation.ConstraintViolation;
import com.github.aureliano.achmed.validation.ObjectValidator;

public class ExecPropertiesTest {
	
	@Test
	public void testDefaultValues() {
		ExecProperties exec = new ExecProperties();
		
		assertEquals(new File("").getAbsolutePath(), exec.getCwd());
		assertEquals(true, exec.getVerbose());
		assertEquals(new Long(300000), exec.getTimeout());
		assertEquals(new Integer(1), exec.getTries());
		assertTrue(exec.getArguments().isEmpty());
		assertEquals(false, exec.getAmendargs());
	}

	@Test
	public void testConfigureAttributes() {
		ExecProperties exec = (ExecProperties) new ExecProperties()
			.put("command", "echo something")
			.put("arguments", Arrays.asList("malabibala"))
			.put("cwd", "/path/to/some/directory")
			.put("verbose", true)
			.put("onlyIf", "another command")
			.put("unless", "any other command")
			.put("timeout", 10000)
			.put("tries", 3)
			.put("amendargs", true)
			.configureAttributes();
		
		assertEquals("echo something", exec.getCommand());
		assertEquals(Arrays.asList("malabibala"), exec.getArguments());
		assertEquals("/path/to/some/directory", exec.getCwd());
		assertTrue(exec.getVerbose());
		assertEquals("another command", exec.getOnlyIf());
		assertEquals("any other command", exec.getUnless());
		assertEquals(new Long(10000), exec.getTimeout());
		assertEquals(new Integer(3), exec.getTries());
		assertTrue(exec.getAmendargs());
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
	
	@Test
	public void testValidation() {
		ExecProperties e = new ExecProperties();
		e.setCommand(null);
		e.setTries(1);
		
		Set<ConstraintViolation> violations = ObjectValidator.instance().validate(e);
		assertEquals(1, violations.size());
		assertEquals(
			"Expected to find a not empty text for field command.",
			violations.iterator().next().getMessage()
		);
		
		e.setCommand("");
		violations = ObjectValidator.instance().validate(e);
		assertEquals(1, violations.size());
		assertEquals(
			"Expected to find a not empty text for field command.",
			violations.iterator().next().getMessage()
		);
		
		e.setCommand("ls -la");
		e.setTries(0);
		violations = ObjectValidator.instance().validate(e);
		assertEquals(1, violations.size());
		assertEquals(
			"Expected a minimum value of 1 for field tries but got 0.",
			violations.iterator().next().getMessage()
		);
	}
}
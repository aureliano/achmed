package com.github.aureliano.achmed.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aureliano.achmed.resources.properties.ExecProperties;

public class ExecResourceTest {

	@Test
	public void testApply() {
		ExecProperties p = new ExecProperties();
		
		p.setCommand("echo \"Hello World!\"");
		p.setTries(2);
		
		ExecResource r = new ExecResource(p);
		r.apply();
	}
	
	@Test
	public void testType() {
		ExecResource r = new ExecResource();
		assertEquals(ResourceType.EXEC, r.type());
	}
	
	@Test
	public void testGetProperties() {
		ExecResource r = new ExecResource(this.createProperties());
		
		ExecProperties p = (ExecProperties) r.getProperties();
		this.checkProperties(p);
		
		r = new ExecResource();
		r.setProperties(this.createProperties());
		
		p = (ExecProperties) r.getProperties();
		this.checkProperties(p);
	}
	
	private ExecProperties createProperties() {
		ExecProperties p = new ExecProperties();
		
		p.setCommand("ls");
		p.setCwd("/path/to/directory");
		p.setOnlyIf("if command success");
		p.setTimeout(5000L);
		p.setTries(2);
		p.setUnless("unless command success");
		p.setVerbose(true);
		
		return p;
	}
	
	private void checkProperties(ExecProperties p) {
		assertEquals("ls", p.getCommand());
		assertEquals("/path/to/directory", p.getCwd());
		assertEquals("if command success", p.getOnlyIf());
		assertEquals(new Long(5000), p.getTimeout());
		assertEquals(new Integer(2), p.getTries());
		assertEquals("unless command success", p.getUnless());
		assertTrue(p.getVerbose());
	}
}
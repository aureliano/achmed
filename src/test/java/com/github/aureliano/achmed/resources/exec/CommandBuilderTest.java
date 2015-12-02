package com.github.aureliano.achmed.resources.exec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aureliano.achmed.resources.properties.ExecProperties;

public class CommandBuilderTest {

	@Test
	public void testBuild() {
		CommandBuilder cmd = new CommandBuilder()
			.withCommand("ls")
			.withTimeout(1000L)
			.withVerbose(true)
			.withWorkingDir("/path/to/working/dir");
		
		assertEquals("ls", cmd.getCommand());
		assertEquals(new Long(1000), cmd.getTimeout());
		assertTrue(cmd.getVerbose());
		assertEquals("/path/to/working/dir", cmd.getWorkingDir());
	}

	@Test
	public void testBuildWithProperties() {
		CommandBuilder cmd = new CommandBuilder(this.createProperties());
		
		assertEquals("ls", cmd.getCommand());
		assertEquals(new Long(1000), cmd.getTimeout());
		assertTrue(cmd.getVerbose());
		assertEquals("/path/to/working/dir", cmd.getWorkingDir());
	}
	
	private ExecProperties createProperties() {
		ExecProperties p = new ExecProperties();
		
		p.setCommand("ls");
		p.setCwd("/path/to/working/dir");
		p.setTimeout(1000L);
		p.setVerbose(true);
		
		return p;
	}
	
	// http://stackoverflow.com/questions/6811522/changing-the-working-directory-of-command-from-java
}
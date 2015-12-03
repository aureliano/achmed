package com.github.aureliano.achmed.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aureliano.achmed.command.CommandBuilder;
import com.github.aureliano.achmed.resources.properties.ExecProperties;

public class CommandBuilderTest {

	@Test
	public void testBuild() {
		CommandBuilder cmd = new CommandBuilder()
			.withCommand("ls")
			.withTimeout(1000L)
			.withTries(3)
			.withVerbose(true)
			.withWorkingDir("/path/to/working/dir");
		
		assertEquals("ls", cmd.getCommand());
		assertEquals(new Long(1000), cmd.getTimeout());
		assertEquals(new Integer(3), cmd.getTries());
		assertTrue(cmd.getVerbose());
		assertEquals("/path/to/working/dir", cmd.getWorkingDir());
	}

	@Test
	public void testBuildWithProperties() {
		CommandBuilder cmd = new CommandBuilder(this.createProperties());
		
		assertEquals("ls", cmd.getCommand());
		assertEquals(new Long(1000), cmd.getTimeout());
		assertEquals(new Integer(3), cmd.getTries());
		assertTrue(cmd.getVerbose());
		assertEquals("/path/to/working/dir", cmd.getWorkingDir());
	}
	
	private ExecProperties createProperties() {
		ExecProperties p = new ExecProperties();
		
		p.setCommand("ls");
		p.setCwd("/path/to/working/dir");
		p.setTimeout(1000L);
		p.setTries(3);
		p.setVerbose(true);
		
		return p;
	}
}
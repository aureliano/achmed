package com.github.aureliano.achmed.client.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aureliano.achmed.client.command.CommandResponse;

public class CommandResponseTest {

	@Test
	public void testBuild() {
		CommandResponse cmd = new CommandResponse("input command")
			.withExitStatusCode(0)
			.withOutput("command output")
			.withError("command error message");

		assertEquals("input command", cmd.getCommand());
		assertEquals(new Integer(0), cmd.getExitStatusCode());
		assertEquals("command output", cmd.getOutput());
		assertEquals("command error message", cmd.getError());
		assertEquals(new Integer(0), cmd.getExitStatusCode());
	}
	
	@Test
	public void testIsOK() {
		CommandResponse cmd = new CommandResponse("input command");
		
		assertTrue(cmd.isOK());
		
		cmd.withExitStatusCode(null);
		assertTrue(cmd.isOK());
		
		cmd.withExitStatusCode(0);
		assertTrue(cmd.isOK());
		
		cmd.withExitStatusCode(1);
		assertFalse(cmd.isOK());
	}
}
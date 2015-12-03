package com.github.aureliano.achmed.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommandResponseTest {

	@Test
	public void testBuild() {
		CommandResponse cmd = new CommandResponse()
			.withExitStatusCode(0)
			.withOutput("command output")
			.withError("command error message");

		assertEquals(new Integer(0), cmd.getExitStatusCode());
		assertEquals("command output", cmd.getOutput());
		assertEquals("command error message", cmd.getError());
		assertEquals(new Integer(0), cmd.getExitStatusCode());
	}
}
package com.github.aureliano.achmed.resources;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExecResourceTest {

	@Test(expected = UnsupportedOperationException.class)
	public void testApply() {
		ExecResource r = new ExecResource();
		r.apply(null);
	}
	
	@Test
	public void testType() {
		ExecResource r = new ExecResource();
		assertEquals(ResourceType.EXEC, r.type());
	}
}
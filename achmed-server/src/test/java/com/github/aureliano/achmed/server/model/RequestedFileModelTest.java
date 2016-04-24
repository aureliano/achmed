package com.github.aureliano.achmed.server.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class RequestedFileModelTest {

	@Test
	public void testEquals() {
		RequestedFileModel r1 = new RequestedFileModel();
		RequestedFileModel r2 = new RequestedFileModel();
		
		assertEquals(r1, r2);
		
		r1.withId("xpto");
		assertNotEquals(r1, r2);
		
		r2.withId("xpto");
		assertEquals(r1, r2);
		
		r1.withFilePath("/file/path");
		assertEquals(r1, r2);
	}
	
	private void assertNotEquals(Object o1, Object o2) {
		assertFalse(o1.equals(o2));
	}
}
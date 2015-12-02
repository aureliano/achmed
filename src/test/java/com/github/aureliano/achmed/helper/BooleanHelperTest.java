package com.github.aureliano.achmed.helper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BooleanHelperTest {

	@Test
	public void testParse() {
		assertNull(BooleanHelper.parse(null));
		assertFalse(BooleanHelper.parse(""));
		
		assertTrue(BooleanHelper.parse(true));
		assertFalse(BooleanHelper.parse(false));
		
		assertTrue(BooleanHelper.parse(Boolean.TRUE));
		assertFalse(BooleanHelper.parse(Boolean.FALSE));
		
		assertTrue(BooleanHelper.parse("true"));
		assertFalse(BooleanHelper.parse("false"));
		
		assertTrue(BooleanHelper.parse("TRUE"));
		assertFalse(BooleanHelper.parse("FALSE"));
	}
}
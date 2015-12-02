package com.github.aureliano.achmed.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class IntegerHelperTest {

	@Test
	public void testParse() {
		Integer expected = 1;
		assertNull(IntegerHelper.parse(null));
		
		assertEquals(expected, IntegerHelper.parse(1));
		assertEquals(expected, IntegerHelper.parse("1"));
		assertEquals(expected, IntegerHelper.parse(new Integer(1)));
	}
}
package com.github.aureliano.achmed.common.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.github.aureliano.achmed.common.helper.IntegerHelper;

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
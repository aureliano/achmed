package com.github.aureliano.achmed.common.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.github.aureliano.achmed.common.helper.LongHelper;

public class LongHelperTest {

	@Test
	public void testParse() {
		Long expected = 1L;
		assertNull(LongHelper.parse(null));
		
		assertEquals(expected, LongHelper.parse(1));
		assertEquals(expected, LongHelper.parse("1"));
		assertEquals(expected, LongHelper.parse(new Long(1)));
		assertEquals(expected, LongHelper.parse(new Integer(1)));
	}
}
package com.github.aureliano.achmed.helper;

import org.junit.Assert;
import org.junit.Test;

public class LongHelperTest {

	@Test
	public void testParse() {
		Long expected = 1L;
		Assert.assertNull(LongHelper.parse(null));
		
		Assert.assertEquals(expected, LongHelper.parse(1));
		Assert.assertEquals(expected, LongHelper.parse("1"));
		Assert.assertEquals(expected, LongHelper.parse(new Long(1)));
		Assert.assertEquals(expected, LongHelper.parse(new Integer(1)));
	}
}
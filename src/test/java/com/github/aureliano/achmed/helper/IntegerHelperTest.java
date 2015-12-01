package com.github.aureliano.achmed.helper;

import org.junit.Assert;
import org.junit.Test;

public class IntegerHelperTest {

	@Test
	public void testParse() {
		Integer expected = 1;
		Assert.assertNull(IntegerHelper.parse(null));
		
		Assert.assertEquals(expected, IntegerHelper.parse(1));
		Assert.assertEquals(expected, IntegerHelper.parse("1"));
		Assert.assertEquals(expected, IntegerHelper.parse(new Integer(1)));
	}
}
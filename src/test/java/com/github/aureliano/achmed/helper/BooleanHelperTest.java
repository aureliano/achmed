package com.github.aureliano.achmed.helper;

import org.junit.Assert;
import org.junit.Test;

public class BooleanHelperTest {

	@Test
	public void testParse() {
		Assert.assertNull(BooleanHelper.parse(null));
		Assert.assertFalse(BooleanHelper.parse(""));
		
		Assert.assertTrue(BooleanHelper.parse(true));
		Assert.assertFalse(BooleanHelper.parse(false));
		
		Assert.assertTrue(BooleanHelper.parse(Boolean.TRUE));
		Assert.assertFalse(BooleanHelper.parse(Boolean.FALSE));
		
		Assert.assertTrue(BooleanHelper.parse("true"));
		Assert.assertFalse(BooleanHelper.parse("false"));
		
		Assert.assertTrue(BooleanHelper.parse("TRUE"));
		Assert.assertFalse(BooleanHelper.parse("FALSE"));
	}
}
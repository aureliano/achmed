package com.github.aureliano.achmed.idiom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LanguageCodeTest {

	@Test
	public void testGetIdiom() {
		assertEquals(Idiom.ENGLISH, LanguageCode.EN_US.getIdiom());
		assertEquals(Idiom.PORTUGUESE, LanguageCode.PT_BR.getIdiom());
	}
}
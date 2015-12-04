package com.github.aureliano.achmed.idiom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LanguageSingletonTest {

	@Test
	public void testInstance() {
		LanguageSingleton o1 = LanguageSingleton.instance();
		LanguageSingleton o2 = LanguageSingleton.instance();
		
		assertEquals(o1, o2);
		assertTrue(o1 == o2);
	}
	
	@Test
	public void testGetValue() {
		LanguageSingleton language = LanguageSingleton.instance();
		
		assertEquals("Install", language.getValue(LanguageCode.EN_US, "resource.package.provider.install"));
		assertEquals("Instalar", language.getValue(LanguageCode.PT_BR, "resource.package.provider.install"));
	}
}
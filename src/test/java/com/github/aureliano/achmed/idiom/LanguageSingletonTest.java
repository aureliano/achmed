package com.github.aureliano.achmed.idiom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aureliano.achmed.client.idiom.LanguageCode;
import com.github.aureliano.achmed.client.idiom.LanguageSingleton;

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
		
		assertEquals("Install", language.getValue("resource.package.provider.install"));
		
		language.setDefaultLanguageCode(LanguageCode.EN_US);
		assertEquals("Install", language.getValue("resource.package.provider.install"));
		
		language.setDefaultLanguageCode(LanguageCode.PT_BR);
		assertEquals("Instalar", language.getValue("resource.package.provider.install"));
	}
	
	@Test
	public void testGetValueWithLanguageCode() {
		LanguageSingleton language = LanguageSingleton.instance();
		
		assertEquals("Install", language.getValue(LanguageCode.EN_US, "resource.package.provider.install"));
		assertEquals("Instalar", language.getValue(LanguageCode.PT_BR, "resource.package.provider.install"));
	}
}
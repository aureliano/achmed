package com.github.aureliano.achmed.idiom;

import java.util.Properties;

import com.github.aureliano.achmed.helper.PropertyHelper;

public class LanguageSingleton {

	private static LanguageSingleton instance;
	
	private Properties language;
	
	private LanguageSingleton() {
		this.language = PropertyHelper.loadProperties("internationalization.properties");
	}
	
	public static LanguageSingleton instance() {
		if (instance == null) {
			instance = new LanguageSingleton();
		}
		
		return instance;
	}
	
	public String getValue(LanguageCode language, String key) {
		key = new StringBuilder(language.name().toLowerCase())
			.append(".")
			.append(key)
			.toString();
		
		return this.language.getProperty(key);
	}
}
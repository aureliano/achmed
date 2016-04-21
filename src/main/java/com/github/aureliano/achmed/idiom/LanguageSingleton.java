package com.github.aureliano.achmed.idiom;

import java.util.Properties;

import com.github.aureliano.achmed.common.helper.PropertyHelper;

public class LanguageSingleton {

	private static LanguageSingleton instance;
	
	private Properties language;
	private LanguageCode defaultLanguageCode;
	
	private LanguageSingleton() {
		this.language = PropertyHelper.loadProperties("internationalization.properties");
		this.defaultLanguageCode = LanguageCode.EN_US;
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
	
	public String getValue(String key) {
		if (this.defaultLanguageCode == null) {
			this.defaultLanguageCode = LanguageCode.EN_US;
		}
		
		return this.getValue(this.defaultLanguageCode, key);
	}
	
	public LanguageCode getDefaultLanguageCode() {
		return defaultLanguageCode;
	}
	
	public void setDefaultLanguageCode(LanguageCode defaultLanguageCode) {
		this.defaultLanguageCode = defaultLanguageCode;
	}
}
package com.github.aureliano.achmed;

import java.util.Locale;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.idiom.LanguageCode;
import com.github.aureliano.achmed.idiom.LanguageSingleton;

public final class AppConfiguration {

	private static final Logger logger = Logger.getLogger(AppConfiguration.class);
	
	private LanguageCode language;
	
	public AppConfiguration() {
		this.applyConfiguration();
	}
	
	public void applyConfiguration() {
		logger.info("Apply core configuration.");
		this.configureLocale();
		LanguageSingleton.instance().setDefaultLanguageCode(this.language);
	}
	
	public LanguageCode getLanguage() {
		return language;
	}
	
	private void configureLocale() {
		logger.info("Configuring default locale.");
		
		Locale locale = Locale.getDefault();
		String code = locale.getLanguage() + "_" + locale.getCountry();
		
		logger.info(String.format(
				"Using default locale to %s with language %s (%s).",
				locale.getDisplayCountry(),
				locale.getDisplayLanguage(),
				code
			)
		);
		
		this.language = LanguageCode.valueOf(code.toUpperCase());
	}
}
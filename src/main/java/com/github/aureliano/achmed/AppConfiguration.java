package com.github.aureliano.achmed;

import java.util.Locale;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.idiom.LanguageCode;
import com.github.aureliano.achmed.idiom.LanguageSingleton;
import com.github.aureliano.achmed.os.IOperatingSystem;
import com.github.aureliano.achmed.os.OperatingSystemFactory;

public final class AppConfiguration {

	private static final Logger logger = Logger.getLogger(AppConfiguration.class);
	
	private LanguageCode language;
	private IOperatingSystem operatingSystem;
	
	public AppConfiguration() {
		this.applyConfiguration();
	}
	
	public void applyConfiguration() {
		logger.info("Apply core configuration.");
		
		this.configureLocale();
		LanguageSingleton.instance().setDefaultLanguageCode(this.language);
		
		this.resolveOperatingSystem();
	}
	
	public LanguageCode getLanguage() {
		return language;
	}
	
	public IOperatingSystem getOperatingSystem() {
		return operatingSystem;
	}
	
	private void resolveOperatingSystem() {
		logger.info("Resolve operating system.");
		
		this.operatingSystem = OperatingSystemFactory.currentOperatingSystem();
		logger.info("Operating System: " + this.operatingSystem.getOperatingSystem());
		logger.info("Operating System family: " + this.operatingSystem.getOperatingSystemFamily());
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
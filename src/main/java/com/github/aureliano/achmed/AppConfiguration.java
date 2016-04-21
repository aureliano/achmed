package com.github.aureliano.achmed;

import java.util.Locale;
import java.util.logging.Logger;

import com.github.aureliano.achmed.client.idiom.LanguageCode;
import com.github.aureliano.achmed.client.idiom.LanguageSingleton;
import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.common.logging.LoggingFactory;
import com.github.aureliano.achmed.os.IOperatingSystem;
import com.github.aureliano.achmed.os.OperatingSystemFactory;

public final class AppConfiguration {

	private static final Logger logger = LoggingFactory.createLogger(AppConfiguration.class);
	
	private static AppConfiguration instance;
	
	private LanguageCode language;
	private IOperatingSystem operatingSystem;
	
	private AppConfiguration() {
		this.applyConfiguration();
	}
	
	public static AppConfiguration instance() {
		if (instance == null) {
			instance = new AppConfiguration();
		}
		
		return instance;
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
		logger.fine("Creating an operating system prototype from " + this.operatingSystem.getClass().getName());
		return operatingSystem.prototype();
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
				StringHelper.capitalize(locale.getDisplayLanguage()),
				code
			)
		);
		
		this.language = LanguageCode.valueOf(code.toUpperCase());
	}
}
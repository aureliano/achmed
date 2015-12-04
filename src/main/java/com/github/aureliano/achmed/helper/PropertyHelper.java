package com.github.aureliano.achmed.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyHelper {

	private PropertyHelper() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
	
	public static Properties loadProperties(String resourceName) {
		Properties properties = new Properties();
		
		try (InputStream stream = getResourceAsStream(resourceName)) {
			properties.load(stream);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		
		return properties;
	}
	
	private static InputStream getResourceAsStream(String resource) {
		String stripped = resource.startsWith("/") ?
				resource.substring(1) : resource;

		InputStream stream = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader != null) {
			stream = classLoader.getResourceAsStream(stripped);
		}
		if (stream == null) {
			stream = PropertyHelper.class.getResourceAsStream(resource);
		}
		if (stream == null) {
			stream = PropertyHelper.class.getClassLoader().getResourceAsStream(stripped);
		}
		if (stream == null) {
			throw new RuntimeException(resource + " not found");
		}
		
		return stream;
	}
}
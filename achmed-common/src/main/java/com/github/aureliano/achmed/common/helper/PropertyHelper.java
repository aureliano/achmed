package com.github.aureliano.achmed.common.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.github.aureliano.achmed.common.exception.AchmedException;

public final class PropertyHelper {

	private PropertyHelper() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
	
	public static Properties loadProperties(String resourceName) {
		InputStream stream = getResourceAsStream(resourceName);
		return loadProperties(stream);
	}
	
	public static Properties loadProperties(File file) {
		try {
			InputStream stream = new FileInputStream(file);
			return loadProperties(stream);
		} catch (FileNotFoundException ex) {
			throw new AchmedException(ex);
		}
	}
	
	private static Properties loadProperties(InputStream stream) {
		Properties properties = new Properties();
		
		try {
			properties.load(stream);
			stream.close();
		} catch (IOException ex) {
			throw new AchmedException(ex);
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
			throw new AchmedException(resource + " not found");
		}
		
		return stream;
	}
}
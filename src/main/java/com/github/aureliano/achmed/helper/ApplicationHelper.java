package com.github.aureliano.achmed.helper;

import java.util.Properties;

public final class ApplicationHelper {

	private ApplicationHelper() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
	
	public static String help() {
		String help = FileHelper.readResource("meta/help");
		return help;
	}
	
	public static String version() {
		Properties properties = PropertyHelper.loadProperties("meta/version.properties");
		return new StringBuilder("achmed")
			.append(" ")
			.append(properties.get("version"))
			.append(":")
			.append(properties.get("release"))
			.append(" (")
			.append(properties.get("date"))
			.append(")")
			.toString();
	}
}
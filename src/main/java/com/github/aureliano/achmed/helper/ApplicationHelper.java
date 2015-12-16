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
	
	public static String error(String[] args) {
		String param = StringHelper.join(args, " ");
		if ("".equals(param)) {
			return EasterEggHelper.silence();
		} else if ("greeting".equalsIgnoreCase(param)) {
			return EasterEggHelper.greeting();
		} else if ("hello".equalsIgnoreCase(param)) {
			return EasterEggHelper.hello();
		} else if ("you are dead".equalsIgnoreCase(param)) {
			return EasterEggHelper.youAreDead();
		} else if ("do you like jews?".equalsIgnoreCase(param)) {
			return EasterEggHelper.doYouLikeJews();
		} else if ("bin laden is dead".equalsIgnoreCase(param)) {
			return EasterEggHelper.binLadenIsDead();
		} else {
			return "Don't know how to handle such command: " + param;
		}
	}
}
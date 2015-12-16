package com.github.aureliano.achmed.helper;

import java.util.Calendar;

public final class EasterEggHelper {

	private EasterEggHelper() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
	
	public static String greeting() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		String greetingMessage = null;
		
		if ((hour >= 5) && (hour < 12)) {
			greetingMessage = "morning";
		} else if ((hour >= 12) && (hour < 18)) {
			greetingMessage = "afternoon";
		} else if ((hour >= 18) && (hour < 21) || ((hour == 20) && (calendar.get(Calendar.MINUTE) <= 30))) {
			greetingMessage = "evening";
		} else {
			greetingMessage = "night";
		}
		
		return "Good " + greetingMessage + "... Infidel!";
	}
}
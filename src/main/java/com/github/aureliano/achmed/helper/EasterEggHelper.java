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
	
	public static String hello() {
		return "Hey, I just met you and this is crazy. But I will kill you, so silence maybe.";
	}
	
	public static String silence() {
		return "SILENCE...! I kill you!";
	}
	
	public static String youAreDead() {
		return "I'm dead?! But I just got my flu shot!";
	}
}
package com.github.aureliano.achmed.helper;

public final class ApplicationHelper {

	private ApplicationHelper() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
	
	public static void printHelp() {
		String help = FileHelper.readResource("meta/help");
		System.out.println(help);
	}
}
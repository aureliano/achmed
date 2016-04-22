package com.github.aureliano.achmed.server.helper;

import java.util.Properties;

import com.github.aureliano.achmed.common.helper.FileHelper;
import com.github.aureliano.achmed.common.helper.PropertyHelper;
import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.server.AchmedServerHandler;

public final class ApplicationHelper {

	private ApplicationHelper() {}
	
	public static void execute(String port) {
		Integer portNumber = Integer.parseInt(port);
		AchmedServerHandler.instance().startUp(portNumber);
	}
	
	public static void addShutDownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				AchmedServerHandler.instance().shutDown();
			}
		});
	}
	
	public static String help() {
		String help = FileHelper.readResource("meta/help");
		return help;
	}
	
	public static String version() {
		Properties properties = PropertyHelper.loadProperties("meta/version.properties");
		
		return new StringBuilder()
			.append(properties.get("version"))
			.append(":")
			.append(properties.get("release"))
			.append(" (")
			.append(properties.get("date"))
			.append(")\n")
			.toString();
	}
	
	public static String error(String[] args) {
		if (args.length == 0) {
			return "Port number parameter is mandatory.";
		} else if ((args.length == 2) && ("-p".equals(args[0])) && (!args[1].matches("\\d+"))) {
			return "Port number must be a valid integer.";
		} else {
			return "Don't know how to handle such command: " + StringHelper.join(args, " ");
		}
	}
}
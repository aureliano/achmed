package com.github.aureliano.achmed.server.helper;

import java.io.File;
import java.util.Properties;

import com.github.aureliano.achmed.common.helper.FileHelper;
import com.github.aureliano.achmed.common.helper.PropertyHelper;
import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.server.AchmedServerHandler;
import com.github.aureliano.achmed.server.conf.ServerConfiguration;

public final class ApplicationHelper {

	private ApplicationHelper() {}
	
	public static void execute() {
		ServerConfiguration configuration = buildServerConfiguration("");
		AchmedServerHandler.instance().startUp(configuration);
	}
	
	protected static ServerConfiguration buildServerConfiguration(String dir) {
		Properties properties = PropertyHelper.loadProperties(new File(dir + "achmed-server.properties"));
		return ServerConfiguration.build(properties);
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
		return "Don't know how to handle such command: " + StringHelper.join(args, " ");
	}
}
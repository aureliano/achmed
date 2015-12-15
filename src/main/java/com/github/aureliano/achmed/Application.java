package com.github.aureliano.achmed;

import java.util.Properties;

import com.github.aureliano.achmed.helper.FileHelper;
import com.github.aureliano.achmed.helper.PropertyHelper;

public class Application {

	public Application() {
		super();
	}
	
	public static void main(String[] args) {
		new Application().startUp(args);
	}
	
	public void startUp(String[] args) {
		if (args.length == 0) {
			this.printHelp();
			return;
		}
		
		if ((args[0].equals("-h")) || (args[0].equals("--help")) || (args[0].equals("help"))) {
			this.printHelp();
		} else if ((args[0].equals("-v")) || (args[0].equals("--version")) || (args[0].equals("version"))) {
			this.printVersion();
		} else if (args[0].equals("burst")) {
			this.prepareExecution(args[1]);
		} else {
			this.printError(args);
		}
	}

	protected void prepareExecution(String string) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	protected void printHelp() {
		String help = FileHelper.readResource("meta/help");
		System.out.println(help);
	}
	
	protected void printVersion() {
		Properties properties = PropertyHelper.loadProperties("meta/version.properties");
		StringBuilder builder = new StringBuilder("achmed")
			.append(" ")
			.append(properties.get("version"))
			.append(":")
			.append(properties.get("release"))
			.append(" (")
			.append(properties.get("date"))
			.append(")");
		
		System.out.println(builder);
	}
	
	private void printError(String[] args) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
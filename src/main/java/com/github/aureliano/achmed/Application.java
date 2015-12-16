package com.github.aureliano.achmed;

import com.github.aureliano.achmed.exception.AchmedException;
import com.github.aureliano.achmed.helper.ApplicationHelper;
import com.github.aureliano.achmed.helper.StringHelper;

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
			this.handleExecution(args);
		} else {
			this.printError(args);
			System.exit(1);
		}
		
		System.exit(0);
	}
	
	protected void handleExecution(String[] args) {
		if (args.length == 1) {
			this.prepareExecution(null);
		} else if (args.length != 2) {
			throw new AchmedException("Invalid arguments. You can pass just one file path after burst command.");
		} else {
			this.prepareExecution(args[1]);
		}
	}

	protected void prepareExecution(String path) {
		if (StringHelper.isEmpty(path)) {
			System.out.println(ApplicationHelper.error(new String[0]));
			System.exit(1);
		}
		throw new UnsupportedOperationException("Not implemented yet");
	}

	protected void printHelp() {
		System.out.println(ApplicationHelper.help());
	}
	
	protected void printVersion() {
		System.out.println(ApplicationHelper.version());
	}
	
	private void printError(String[] args) {
		System.out.println(ApplicationHelper.error(args));
	}
}
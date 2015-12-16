package com.github.aureliano.achmed;

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
		
		int status = 0;
		if ((args[0].equals("-h")) || (args[0].equals("--help")) || (args[0].equals("help"))) {
			status = this.printHelp();
		} else if ((args[0].equals("-v")) || (args[0].equals("--version")) || (args[0].equals("version"))) {
			status = this.printVersion();
		} else if (args[0].equals("burst")) {
			status = this.handleExecution(args);
		} else {
			status = this.printError(args);
		}
		
		System.exit(status);
	}
	
	protected int handleExecution(String[] args) {
		if (args.length == 1) {
			return this.prepareExecution(null);
		} else if (args.length != 2) {
			System.err.println("Invalid arguments. You can pass just one file path after burst command.");
			return 1;
		} else {
			return this.prepareExecution(args[1]);
		}
	}

	protected int prepareExecution(String path) {
		if (StringHelper.isEmpty(path)) {
			System.out.println(ApplicationHelper.error(new String[0]));
			return 1;
		}
		
		try {
			ApplicationHelper.execute(path);
			return 0;
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			
			return 2;
		}
	}

	protected int printHelp() {
		System.out.println(ApplicationHelper.help());
		return 0;
	}
	
	protected int printVersion() {
		System.out.println(ApplicationHelper.version());
		return 0;
	}
	
	private int printError(String[] args) {
		System.out.println(ApplicationHelper.error(args));
		return 1;
	}
}
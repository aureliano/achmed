package com.github.aureliano.achmed;

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
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	protected void printVersion() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	private void printError(String[] args) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
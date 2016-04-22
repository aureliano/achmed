package com.github.aureliano.achmed.server;

import com.github.aureliano.achmed.common.StatusCode;
import com.github.aureliano.achmed.common.exception.AchmedException;
import com.github.aureliano.achmed.server.helper.ApplicationHelper;

public class Application {

	public static void main(String[] args) {
		ApplicationHelper.addShutDownHook();
		new Application().startUp(args);
	}

	public Application() {
		super();
	}

	public void startUp(String[] args) {
		StatusCode status = null;
		if (args.length == 0) {
			status = this.handleExecution();
		} else if ((args[0].equals("-h")) || (args[0].equals("--help")) || (args[0].equals("help"))) {
			status = this.printHelp();
		} else if ((args[0].equals("-v")) || (args[0].equals("--version")) || (args[0].equals("version"))) {
			status = this.printVersion();
		} else {
			status = this.printError(args);
		}

		System.exit(status.getCode());
	}
	
	protected StatusCode handleExecution() {
		return this.prepareExecution();
	}

	protected StatusCode prepareExecution() {
		try {
			ApplicationHelper.execute();
			return StatusCode.SUCCESS;
		} catch (AchmedException ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			
			return ex.getCode();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			
			return StatusCode.COMMON_EXECUTION_ERROR;
		}
	}

	protected StatusCode printHelp() {
		System.out.println(ApplicationHelper.help());
		return StatusCode.SUCCESS;
	}
	
	protected StatusCode printVersion() {
		System.out.println(ApplicationHelper.version());
		return StatusCode.SUCCESS;
	}
	
	private StatusCode printError(String[] args) {
		System.out.println(ApplicationHelper.error(args));
		return StatusCode.CLI_PARAMETERS_ERROR;
	}
}
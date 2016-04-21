package com.github.aureliano.achmed;

import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.exception.AchmedException;
import com.github.aureliano.achmed.helper.ApplicationHelper;
import com.github.aureliano.achmed.types.StatusCode;

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
		
		StatusCode status = null;
		if ((args[0].equals("-h")) || (args[0].equals("--help")) || (args[0].equals("help"))) {
			status = this.printHelp();
		} else if ((args[0].equals("-v")) || (args[0].equals("--version")) || (args[0].equals("version"))) {
			status = this.printVersion();
		} else if (args[0].equals("burst")) {
			status = this.handleExecution(args);
		} else {
			status = this.printError(args);
		}
		
		System.exit(status.getCode());
	}
	
	protected StatusCode handleExecution(String[] args) {
		if (args.length == 1) {
			return this.prepareExecution(null);
		} else if (args.length != 2) {
			System.err.println("Invalid arguments. You can pass just one file path after burst command.");
			return StatusCode.CLI_PARAMETERS_ERROR;
		} else {
			return this.prepareExecution(args[1]);
		}
	}

	protected StatusCode prepareExecution(String path) {
		if (StringHelper.isEmpty(path)) {
			System.out.println(ApplicationHelper.error(new String[0]));
			return StatusCode.CLI_PARAMETERS_ERROR;
		}
		
		try {
			ApplicationHelper.execute(path);
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
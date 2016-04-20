package com.github.aureliano.achmed.command;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import com.github.aureliano.achmed.helper.StringHelper;
import com.github.aureliano.achmed.logging.LoggingFactory;
import com.github.aureliano.achmed.resources.properties.ExecProperties;

public final class CommandFacade {

	private static final Logger logger = LoggingFactory.createLogger(CommandFacade.class);
	private static final long ONE_SECOND = 1000;
	public static final long DEFAULT_TIMEOUT_EXECUTION = 300 * ONE_SECOND;
	
	private CommandFacade() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
	
	public static CommandResponse executeCommand(String...cmd) {
		CommandBuilder command = new CommandBuilder()
			.withCommand(StringHelper.join(cmd, " "))
			.withTimeout(DEFAULT_TIMEOUT_EXECUTION)
			.withTries(1)
			.withVerbose(false)
			.withWorkingDir(new File("").getAbsolutePath());
		
		return executeCommand(command);
	}
	
	public static CommandResponse executeCommand(CommandBuilder command) {
		CommandResponse response = null;
		int exitStatusCode = 0;
		int tries = 0;
		
		do {
			response = _executeCommand(command);
			exitStatusCode = response.getExitStatusCode();
			tries ++;
			
			logger.fine("Exit status code: " + exitStatusCode);
			logger.fine("Tries: " + tries);
		} while ((tries < command.getTries()) && (exitStatusCode != 0));
		
		return response;
	}
	
	public static CommandResponse executeCommand(ExecProperties properties) {
		return executeCommand(new CommandBuilder(properties));
	}
	
	private static CommandResponse _executeCommand(CommandBuilder command) {
		boolean verbose = command.getVerbose() != null ? command.getVerbose() : true;
		CommandRunner runner = new CommandRunner(
			command.getCommand(), command.getWorkingDir(), verbose, command.getArguments()
		);
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<CommandResponse> future = executor.submit(runner);
		
		try {
			return future.get(command.getTimeout(), TimeUnit.MILLISECONDS);
		} catch (TimeoutException ex) {
			logger.warning("Execution timeout to command: " + command.getCommand());
			throw new RuntimeException(ex);
		} catch (ExecutionException|InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}
}
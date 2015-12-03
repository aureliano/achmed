package com.github.aureliano.achmed.command;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.exception.ExecResourceException;
import com.github.aureliano.achmed.resources.properties.ExecProperties;

public final class CommandFacade {

	private static final Logger logger = Logger.getLogger(CommandFacade.class);
	
	private CommandFacade() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
	
	public static int executeCommand(CommandBuilder command) {
		int exitStatusCode = 0;
		int tries = 0;
		
		do {
			exitStatusCode = _executeCommand(command);
			tries ++;
			
			logger.debug("Exit status code: " + exitStatusCode);
			logger.debug("Tries: " + tries);
		} while ((tries < command.getTries()) && (exitStatusCode != 0));
		
		return exitStatusCode;
	}
	
	public static int executeCommand(ExecProperties properties) {
		return executeCommand(new CommandBuilder(properties));
	}
	
	private static int _executeCommand(CommandBuilder command) {
		boolean verbose = command.getVerbose() != null ? command.getVerbose() : true;
		CommandRunner runner = new CommandRunner(command.getCommand(), command.getWorkingDir(), verbose);
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Integer> future = executor.submit(runner);
		
		try {
			return future.get(command.getTimeout(), TimeUnit.MILLISECONDS);
		} catch (TimeoutException ex) {
			logger.warn("Execution timeout to command: " + command.getCommand());
			throw new ExecResourceException(ex);
		} catch (ExecutionException|InterruptedException ex) {
			throw new ExecResourceException(ex);
		}
	}
}
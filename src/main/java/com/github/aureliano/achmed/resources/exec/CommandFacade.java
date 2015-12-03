package com.github.aureliano.achmed.resources.exec;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.github.aureliano.achmed.exception.ExecResourceException;

public final class CommandFacade {

	private CommandFacade() {
		super();
	}
	
	public static int executeCommand(CommandBuilder command) {
		boolean verbose = command.getVerbose() != null ? command.getVerbose() : true;
		CommandRunner runner = new CommandRunner(command.getCommand(), command.getWorkingDir(), verbose);
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Integer> future = executor.submit(runner);
		
		try {
			return future.get(command.getTimeout(), TimeUnit.MILLISECONDS);
		} catch (ExecutionException|TimeoutException|InterruptedException ex) {
			throw new ExecResourceException(ex);
		}
	}
}
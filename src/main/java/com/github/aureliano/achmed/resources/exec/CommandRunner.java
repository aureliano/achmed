package com.github.aureliano.achmed.resources.exec;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

import com.github.aureliano.achmed.exception.ExecResourceException;

public class CommandRunner implements Callable<Integer> {

	private List<String> command;
	private File workingDir;
	private boolean verbose;
	private int exitStatus;
	
	public CommandRunner(String command, String dir, boolean verbose) {
		this.command = Arrays.asList(command.split("\\s+"));
		this.workingDir = new File(dir);
		this.verbose = verbose;
	}
	
	public Integer call() throws Exception {
		return this.execute();
	}
	
	public int execute() {
		ProcessBuilder builder = new ProcessBuilder(this.command);
		builder.directory(this.workingDir);
		
		this.exitStatus = Integer.parseInt("1" + this.command.size());
		Process process = null;
		
		try {
			process = builder.start();
			this.scanCommand(process);
			this.exitStatus = process.waitFor();
		} catch (Exception ex) {
			try {
				this.readError(process);
			} catch (RuntimeException re) {
				throw new ExecResourceException(re);
			}
		}
		
		return this.exitStatus;
	}
	
	public int getExitStatusCode() {
		return this.exitStatus;
	}
	
	private void scanCommand(Process process) {
		this.consumeStream(process.getInputStream(), this.verbose);
	}
	
	private void readError(Process process) {
		this.consumeStream(process.getErrorStream(), true);
	}
	
	private void consumeStream(InputStream stream, boolean verbose) {
		Scanner scanner = new Scanner(stream);
		
		while (scanner.hasNextLine()) {
			if (verbose) {
				System.out.println(scanner.nextLine());
			}
		}
		
		scanner.close();
	}
}
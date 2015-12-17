package com.github.aureliano.achmed.command;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

import com.github.aureliano.achmed.helper.StringHelper;

public class CommandRunner implements Callable<CommandResponse> {

	private List<String> command;
	private File workingDir;
	private boolean verbose;
	
	public CommandRunner(String command, String dir, boolean verbose) {
		this.command = Arrays.asList(command.split("\\s+"));
		this.workingDir = new File(dir);
		this.verbose = verbose;
	}
	
	public CommandResponse call() throws Exception {
		return this.execute();
	}
	
	public CommandResponse execute() {
		ProcessBuilder builder = new ProcessBuilder(this.command);
		builder.directory(this.workingDir);
		
		int exitStatus = Integer.parseInt("1" + this.command.size());
		Process process = null;
		CommandResponse response = new CommandResponse(StringHelper.join(this.command, " "));
		
		try {
			process = builder.start();
			response.withOutput(this.scanCommand(process));
			exitStatus = process.waitFor();
		} catch (Exception ex) {
			response.withError(this.readError(process));
		}
		
		return response.withExitStatusCode(exitStatus);
	}
	
	private String scanCommand(Process process) {
		return this.consumeStream(process.getInputStream(), this.verbose);
	}
	
	private String readError(Process process) {
		return this.consumeStream(process.getErrorStream(), true);
	}
	
	private String consumeStream(InputStream stream, boolean verbose) {
		Scanner scanner = new Scanner(stream);
		StringBuilder builder = new StringBuilder();
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			builder.append(line).append("\n");
			
			if (verbose) {
				System.out.println(line);
			}
		}
		
		if (builder.length() > 0) {
			builder.deleteCharAt(builder.length() - 1);
		}
		scanner.close();
		
		return builder.toString();
	}
}
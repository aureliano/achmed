package com.github.aureliano.achmed.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import com.github.aureliano.achmed.exception.AchmedException;
import com.github.aureliano.achmed.helper.StringHelper;

public class CommandRunner implements Callable<CommandResponse> {

	private List<String> command;
	private File workingDir;
	private boolean verbose;
	
	public CommandRunner(String command, String dir, boolean verbose, boolean splitCommand) {
		String[] tokens = (splitCommand) ? command.split("\\s+") : new String[] { command };
		this.command = Arrays.asList(tokens);
		
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
			if (process != null) {
				response.withError(this.readError(process));
			} else {
				response.withError("Program '" + this.command.get(0) + "' may not exist in your environment.");
			}
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
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
			StringBuilder builder = new StringBuilder();
			
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				builder.append(line).append("\n");
				
				if (verbose) {
					System.out.println(line);
				}
			}
			
			if (builder.length() > 0) {
				builder.deleteCharAt(builder.length() - 1);
			}
			
			return builder.toString();
		} catch (Exception ex) {
			throw new AchmedException(ex);
		}
	}
}
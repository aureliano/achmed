package com.github.aureliano.achmed.command;

import com.github.aureliano.achmed.resources.properties.ExecProperties;

public class CommandBuilder {

	private String command;
	private String workingDir;
	private Boolean verbose;
	private Long timeout;
	private Integer tries;
	private Boolean splitCommand;
	
	public CommandBuilder() {
		this.splitCommand = true;
	}
	
	public CommandBuilder(ExecProperties properties) {
		this
			.withCommand(properties.getCommand())
			.withWorkingDir(properties.getCwd())
			.withVerbose(properties.getVerbose())
			.withTimeout(properties.getTimeout())
			.withTries(properties.getTries())
			.withSplitCommand(properties.getSplitCommand());
	}

	public String getCommand() {
		return command;
	}

	public CommandBuilder withCommand(String command) {
		this.command = command;
		return this;
	}

	public String getWorkingDir() {
		return workingDir;
	}

	public CommandBuilder withWorkingDir(String workingDir) {
		this.workingDir = workingDir;
		return this;
	}

	public Boolean getVerbose() {
		return verbose;
	}

	public CommandBuilder withVerbose(Boolean verbose) {
		this.verbose = verbose;
		return this;
	}

	public Long getTimeout() {
		return timeout;
	}

	public CommandBuilder withTimeout(Long timeout) {
		this.timeout = timeout;
		return this;
	}
	
	public Integer getTries() {
		return tries;
	}
	
	public CommandBuilder withTries(Integer tries) {
		this.tries = tries;
		return this;
	}
	
	public Boolean getSplitCommand() {
		return splitCommand;
	}
	
	public void setSplitCommand(Boolean splitCommand) {
		this.splitCommand = splitCommand;
	}
	
	public CommandBuilder withSplitCommand(Boolean splitCommand) {
		this.splitCommand = splitCommand;
		return this;
	}
}
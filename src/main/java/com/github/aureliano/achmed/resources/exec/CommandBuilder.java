package com.github.aureliano.achmed.resources.exec;

import com.github.aureliano.achmed.resources.properties.ExecProperties;

public class CommandBuilder {

	private String command;
	private String workingDir;
	private Boolean verbose;
	private Long timeout;
	
	public CommandBuilder() {
		super();
	}
	
	public CommandBuilder(ExecProperties properties) {
		this
			.withCommand(properties.getCommand())
			.withWorkingDir(properties.getCwd())
			.withVerbose(properties.isVerbose())
			.withTimeout(properties.getTimeout());
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
}
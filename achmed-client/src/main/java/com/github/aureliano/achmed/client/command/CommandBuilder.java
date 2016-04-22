package com.github.aureliano.achmed.client.command;

import java.util.List;

import com.github.aureliano.achmed.client.resources.properties.ExecProperties;

public class CommandBuilder {

	private String command;
	private List<String> arguments;
	private String workingDir;
	private Boolean verbose;
	private Long timeout;
	private Integer tries;
	
	public CommandBuilder() {
		super();
	}
	
	public CommandBuilder(ExecProperties properties) {
		this
			.withCommand(properties.getCommand())
			.withArguments(properties.getArguments())
			.withWorkingDir(properties.getCwd())
			.withVerbose(properties.getVerbose())
			.withTimeout(properties.getTimeout())
			.withTries(properties.getTries());
	}

	public String getCommand() {
		return command;
	}

	public CommandBuilder withCommand(String command) {
		this.command = command;
		return this;
	}
	
	public List<String> getArguments() {
		return arguments;
	}
	
	public CommandBuilder withArguments(List<String> arguments) {
		this.arguments = arguments;
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
}
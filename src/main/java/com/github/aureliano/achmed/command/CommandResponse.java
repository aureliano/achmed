package com.github.aureliano.achmed.command;

public class CommandResponse {

	private Integer exitStatusCode;
	private String output;
	private String error;
	private String command;
	
	public CommandResponse(String command) {
		this.command = command;
	}

	public Integer getExitStatusCode() {
		return exitStatusCode;
	}

	public CommandResponse withExitStatusCode(Integer exitStatusCode) {
		this.exitStatusCode = exitStatusCode;
		return this;
	}

	public String getOutput() {
		return output;
	}

	public CommandResponse withOutput(String output) {
		this.output = output;
		return this;
	}

	public String getError() {
		return error;
	}

	public CommandResponse withError(String error) {
		this.error = error;
		return this;
	}
	
	public String getCommand() {
		return command;
	}
	
	public boolean isOK() {
		return ((this.exitStatusCode != null) ? (this.exitStatusCode == 0) : true);
	}
}
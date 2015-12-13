package com.github.aureliano.achmed.exception;

import com.github.aureliano.achmed.command.CommandResponse;

public class ResourceException extends AchmedException {

	private static final long serialVersionUID = 6266823647140894014L;

	public ResourceException() {
		super();
	}

	public ResourceException(String message) {
		super(message);
	}

	public ResourceException(Throwable throwable) {
		super(throwable);
	}

	public ResourceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ResourceException(CommandResponse response) {
		super(
			String.format(
				"Command [ %s ] has exited with status [ %d ]. Detail: [ %s ]",
				response.getCommand(),
				response.getExitStatusCode(),
				response.getError()
			)
		);
	}
}
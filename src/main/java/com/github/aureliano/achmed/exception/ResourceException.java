package com.github.aureliano.achmed.exception;

import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.types.StatusCode;

public class ResourceException extends AchmedException {

	private static final long serialVersionUID = 6266823647140894014L;

	public ResourceException() {
		this(null, null);
	}

	public ResourceException(String message) {
		this(message, null);
	}

	public ResourceException(Throwable throwable) {
		this(null, throwable);
	}

	public ResourceException(String message, Throwable throwable) {
		super(message, throwable);
		super.code = StatusCode.RESOURCE_EXECUTION_ERROR.getCode();
	}

	public ResourceException(CommandResponse response) {
		this(
			String.format(
				"Command [ %s ] has exited with status [ %d ]. Detail: [ %s ]",
				response.getCommand(),
				response.getExitStatusCode(),
				response.getError()
			)
		);
	}
}
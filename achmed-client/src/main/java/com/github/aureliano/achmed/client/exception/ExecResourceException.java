package com.github.aureliano.achmed.client.exception;

import com.github.aureliano.achmed.client.command.CommandResponse;

public class ExecResourceException extends ResourceException {

	private static final long serialVersionUID = -1257837733015431140L;

	public ExecResourceException() {
		this(null, null);
	}

	public ExecResourceException(String message) {
		this(message, null);
	}

	public ExecResourceException(Throwable throwable) {
		this(null, throwable);
	}

	public ExecResourceException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public ExecResourceException(CommandResponse response) {
		super(response);
	}
}
package com.github.aureliano.achmed.exception;

import com.github.aureliano.achmed.command.CommandResponse;

public class ServiceResourceException extends ResourceException {

	private static final long serialVersionUID = 892406933885019778L;

	public ServiceResourceException() {
		this(null, null);
	}

	public ServiceResourceException(String message) {
		this(message, null);
	}

	public ServiceResourceException(Throwable throwable) {
		this(null, throwable);
	}

	public ServiceResourceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ServiceResourceException(CommandResponse response) {
		super(response);
	}
}
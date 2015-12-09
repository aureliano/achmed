package com.github.aureliano.achmed.exception;

import com.github.aureliano.achmed.command.CommandResponse;

public class ServiceResourceException extends ResourceException {

	private static final long serialVersionUID = 892406933885019778L;

	public ServiceResourceException() {
		super();
	}

	public ServiceResourceException(String message) {
		super(message);
	}

	public ServiceResourceException(Throwable throwable) {
		super(throwable);
	}

	public ServiceResourceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ServiceResourceException(CommandResponse response) {
		super(response);
	}
}
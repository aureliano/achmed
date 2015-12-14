package com.github.aureliano.achmed.exception;

import com.github.aureliano.achmed.command.CommandResponse;

public class FileResourceException extends ResourceException {

	private static final long serialVersionUID = 6809095978112185723L;

	public FileResourceException() {
		super();
	}

	public FileResourceException(String message) {
		super(message);
	}

	public FileResourceException(Throwable throwable) {
		super(throwable);
	}

	public FileResourceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public FileResourceException(CommandResponse response) {
		super(response);
	}
}
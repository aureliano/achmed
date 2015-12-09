package com.github.aureliano.achmed.exception;

import com.github.aureliano.achmed.command.CommandResponse;

public class PackageResourceException extends ResourceException {

	private static final long serialVersionUID = -1257837733015431140L;

	public PackageResourceException() {
		super();
	}

	public PackageResourceException(String message) {
		super(message);
	}

	public PackageResourceException(Throwable throwable) {
		super(throwable);
	}

	public PackageResourceException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public PackageResourceException(CommandResponse response) {
		super(response);
	}
}
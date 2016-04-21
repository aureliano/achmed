package com.github.aureliano.achmed.exception;

import com.github.aureliano.achmed.client.command.CommandResponse;

public class PackageResourceException extends ResourceException {

	private static final long serialVersionUID = -1257837733015431140L;

	public PackageResourceException() {
		this(null, null);
	}

	public PackageResourceException(String message) {
		this(message, null);
	}

	public PackageResourceException(Throwable throwable) {
		this(null, throwable);
	}

	public PackageResourceException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public PackageResourceException(CommandResponse response) {
		super(response);
	}
}
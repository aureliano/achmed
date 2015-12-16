package com.github.aureliano.achmed.exception;

import com.github.aureliano.achmed.types.StatusCode;

public class NoSuchResourceException extends AchmedException {

	private static final long serialVersionUID = 140440539843566483L;

	public NoSuchResourceException() {
		this(null, null);
	}

	public NoSuchResourceException(String message) {
		this(message, null);
	}

	public NoSuchResourceException(Throwable throwable) {
		this(null, throwable);
	}

	public NoSuchResourceException(String message, Throwable throwable) {
		super(message, throwable);
		super.code = StatusCode.RESOURCE_RESOLVING_ERROR;
	}
}
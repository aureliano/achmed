package com.github.aureliano.achmed.exception;

public class NoSuchResourceException extends AchmedException {

	private static final long serialVersionUID = 140440539843566483L;

	public NoSuchResourceException() {
		super();
	}

	public NoSuchResourceException(String message) {
		super(message);
	}

	public NoSuchResourceException(Throwable throwable) {
		super(throwable);
	}

	public NoSuchResourceException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
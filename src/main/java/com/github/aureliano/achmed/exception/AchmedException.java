package com.github.aureliano.achmed.exception;

public class AchmedException extends RuntimeException {

	private static final long serialVersionUID = 3113079107744700883L;

	public AchmedException() {
		super();
	}

	public AchmedException(String message) {
		super(message);
	}

	public AchmedException(Throwable throwable) {
		super(throwable);
	}

	public AchmedException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
package com.github.aureliano.achmed.exception;

public class ExecResourceException extends RuntimeException {

	private static final long serialVersionUID = -1257837733015431140L;

	public ExecResourceException() {
		super();
	}

	public ExecResourceException(String message) {
		super(message);
	}

	public ExecResourceException(Throwable throwable) {
		super(throwable);
	}

	public ExecResourceException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
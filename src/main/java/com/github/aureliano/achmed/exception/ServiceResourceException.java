package com.github.aureliano.achmed.exception;

public class ServiceResourceException extends RuntimeException {

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
}
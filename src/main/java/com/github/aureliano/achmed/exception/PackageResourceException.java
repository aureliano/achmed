package com.github.aureliano.achmed.exception;

public class PackageResourceException extends RuntimeException {

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
}
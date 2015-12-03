package com.github.aureliano.achmed.exception;

public class UnsupportedOperatingSystemException extends RuntimeException {

	private static final long serialVersionUID = 4654066489822139408L;

	public UnsupportedOperatingSystemException() {
		super();
	}

	public UnsupportedOperatingSystemException(String message) {
		super(message);
	}

	public UnsupportedOperatingSystemException(Throwable throwable) {
		super(throwable);
	}

	public UnsupportedOperatingSystemException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
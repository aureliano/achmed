package com.github.aureliano.achmed.exception;

public class UnsupportedPackageManagerException extends AchmedException {

	private static final long serialVersionUID = -4089891118124103597L;

	public UnsupportedPackageManagerException() {
		super();
	}

	public UnsupportedPackageManagerException(String message) {
		super(message);
	}

	public UnsupportedPackageManagerException(Throwable throwable) {
		super(throwable);
	}

	public UnsupportedPackageManagerException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
package com.github.aureliano.achmed.exception;

public class AchmedValidationException extends AchmedException {

	private static final long serialVersionUID = -1185214958887450988L;

	public AchmedValidationException() {
		super();
	}

	public AchmedValidationException(String message) {
		super(message);
	}

	public AchmedValidationException(Throwable throwable) {
		super(throwable);
	}

	public AchmedValidationException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
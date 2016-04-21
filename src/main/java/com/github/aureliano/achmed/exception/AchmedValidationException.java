package com.github.aureliano.achmed.exception;

import com.github.aureliano.achmed.common.StatusCode;
import com.github.aureliano.achmed.common.exception.AchmedException;

public class AchmedValidationException extends AchmedException {

	private static final long serialVersionUID = -1185214958887450988L;

	public AchmedValidationException() {
		this(null, null);
	}

	public AchmedValidationException(String message) {
		this(message, null);
	}

	public AchmedValidationException(Throwable throwable) {
		this(null, throwable);
	}

	public AchmedValidationException(String message, Throwable throwable) {
		super(message, throwable);
		super.code = StatusCode.VALIDATION_ERROR;
	}
}
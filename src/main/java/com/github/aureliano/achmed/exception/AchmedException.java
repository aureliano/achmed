package com.github.aureliano.achmed.exception;

import com.github.aureliano.achmed.types.StatusCode;

public class AchmedException extends RuntimeException {

	private static final long serialVersionUID = 3113079107744700883L;
	protected StatusCode code;

	public AchmedException() {
		this(null, null);
	}

	public AchmedException(String message) {
		this(message, null);
	}

	public AchmedException(Throwable throwable) {
		this(null, throwable);
	}

	public AchmedException(String message, Throwable throwable) {
		super(message, throwable);
		this.code = StatusCode.COMMON_EXECUTION_ERROR;
	}
	
	public StatusCode getCode() {
		return code;
	}
}
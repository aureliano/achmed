package com.github.aureliano.achmed.exception;

import com.github.aureliano.achmed.common.StatusCode;
import com.github.aureliano.achmed.common.exception.AchmedException;

public class UnsupportedOperatingSystemException extends AchmedException {

	private static final long serialVersionUID = 4654066489822139408L;

	public UnsupportedOperatingSystemException() {
		this(null, null);
	}

	public UnsupportedOperatingSystemException(String message) {
		this(message, null);
	}

	public UnsupportedOperatingSystemException(Throwable throwable) {
		this(null, throwable);
	}

	public UnsupportedOperatingSystemException(String message, Throwable throwable) {
		super(message, throwable);
		super.code = StatusCode.UNSUPPORTED_OPERATING_SYSTEM_ERROR;
	}
}
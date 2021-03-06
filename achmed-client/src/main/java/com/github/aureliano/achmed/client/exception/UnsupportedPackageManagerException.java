package com.github.aureliano.achmed.client.exception;

import com.github.aureliano.achmed.common.StatusCode;
import com.github.aureliano.achmed.common.exception.AchmedException;

public class UnsupportedPackageManagerException extends AchmedException {

	private static final long serialVersionUID = -4089891118124103597L;

	public UnsupportedPackageManagerException() {
		this(null, null);
	}

	public UnsupportedPackageManagerException(String message) {
		this(message, null);
	}

	public UnsupportedPackageManagerException(Throwable throwable) {
		this(null, throwable);
	}

	public UnsupportedPackageManagerException(String message, Throwable throwable) {
		super(message, throwable);
		super.code = StatusCode.UNSUPPORTED_PACKAGE_MANAGER_ERROR;
	}
}
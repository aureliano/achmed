package com.github.aureliano.achmed.types;

public enum StatusCode {

	SUCCESS(0),
	CLI_PARAMETERS_ERROR(1),
	COMMON_EXECUTION_ERROR(2),
	VALIDATION_ERROR(3),
	RESOURCE_EXECUTION_ERROR(4),
	RESOURCE_RESOLVING_ERROR(5),
	UNSUPPORTED_OPERATING_SYSTEM_ERROR(6),
	UNSUPPORTED_PACKAGE_MANAGER_ERROR(7);
	
	private int code;
	
	private StatusCode(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
}
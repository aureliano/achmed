package com.github.aureliano.achmed.server.service;

import com.github.aureliano.achmed.common.exception.AchmedException;

public final class ServiceFactory {

	private ServiceFactory() {}
	
	public static IService createService(ServiceType type) {
		switch (type) {
		case READ_FILE:
			return null;
		default:
			throw new AchmedException("Unsupported service type: " + type);
		}
	}
}
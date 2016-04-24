package com.github.aureliano.achmed.server.service;

import com.github.aureliano.achmed.common.exception.AchmedException;

public final class ServiceFactory {

	private static final String SERVICE_PATTERN = "^service:\\s*";
	private static final String SERVICE_NAME_PATTERN = SERVICE_PATTERN + "(read_file)$";
	
	private ServiceFactory() {}
	
	public static IService createService(ServiceType type) {
		switch (type) {
		case READ_FILE:
			return new CheckFileStatusService();
		default:
			throw new AchmedException("Unsupported service type: " + type);
		}
	}
	
	public static IService createService(String data) {
		ServiceType type = getServiceType(data);
		return ServiceFactory.createService(type);
	}
	
	private static ServiceType getServiceType(String data) {
		String serviceName = getRequestedService(data);
		try {
			return ServiceType.valueOf(serviceName);
		} catch (Exception ex) {
			throw new AchmedException("There is not a service named " + serviceName);
		}
	}
	
	private static String getRequestedService(String data) {
		if (!data.matches(SERVICE_NAME_PATTERN)) {
			throw new AchmedException("Invalid service: " + data);
		}
		
		String serviceName = data.split(":")[1].trim();
		return serviceName.toUpperCase();
	}
}
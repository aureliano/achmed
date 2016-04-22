package com.github.aureliano.achmed.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.github.aureliano.achmed.common.exception.AchmedException;
import com.github.aureliano.achmed.server.service.IService;
import com.github.aureliano.achmed.server.service.ServiceFactory;
import com.github.aureliano.achmed.server.service.ServiceType;

public class ThreadHandler implements Runnable {
	
	private static final String SERVICE_PATTERN = "^service:\\s*";
	private static final String SERVICE_NAME_PATTERN = SERVICE_PATTERN + "(read_file)$";
	
	private Socket socket;
	
	public ThreadHandler(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		IService service = this.createService();
		service.consume(this.socket);
	}
	
	private IService createService() {
		ServiceType type = this.getServiceType();
		return ServiceFactory.createService(type);
	}
	
	private ServiceType getServiceType() {
		String serviceName = this.getRequestedService();
		try {
			return ServiceType.valueOf(serviceName);
		} catch (Exception ex) {
			throw new AchmedException("There is not a service named " + serviceName);
		}
	}
	
	private String getRequestedService() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			String requestedService = reader.readLine();
		
			if (!requestedService.matches(SERVICE_NAME_PATTERN)) {
				throw new AchmedException("Broken service name description.");
			}
			
			String serviceName = requestedService.replaceFirst(SERVICE_PATTERN, "").trim();
			return serviceName.toUpperCase();
		} catch (IOException ex) {
			throw new AchmedException(ex);
		}
	}
}
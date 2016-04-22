package com.github.aureliano.achmed.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.aureliano.achmed.common.exception.AchmedException;
import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.common.logging.LoggingFactory;
import com.github.aureliano.achmed.server.service.IService;
import com.github.aureliano.achmed.server.service.ServiceFactory;
import com.github.aureliano.achmed.server.service.ServiceType;

public class ThreadHandler implements Runnable {
	
	private static final String SERVICE_PATTERN = "^service:\\s*";
	private static final String SERVICE_NAME_PATTERN = SERVICE_PATTERN + "(read_file)$";
	private static final Logger logger = LoggingFactory.createLogger(ThreadHandler.class);
	
	private Socket socket;
	
	private ThreadHandler(Socket socket) {
		this.socket = socket;
	}
	
	public static Runnable handle(Socket socket) {
		logger.info("Accepted connection from: " + socket.getInetAddress().getHostAddress());
		return new ThreadHandler(socket);
	}
	
	@Override
	public void run() {
		try {
			IService service = this.createService();
			service.consume(this.socket);
			
			this.closeSocket();
		} catch (AchmedException ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	
	private IService createService() {
		ServiceType type = this.getServiceType();
		logger.info("Accessing service: " + type);
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
		
			if ((StringHelper.isEmpty(requestedService)) || (!requestedService.matches(SERVICE_NAME_PATTERN))) {
				throw new AchmedException("Broken service name description.");
			}
			
			String serviceName = requestedService.replaceFirst(SERVICE_PATTERN, "").trim();
			return serviceName.toUpperCase();
		} catch (IOException ex) {
			throw new AchmedException(ex);
		}
	}
	
	private void closeSocket() {
		if (!this.socket.isClosed()) {
			try {
				this.socket.close();
			} catch (IOException ex) {
				throw new AchmedException(ex);
			}
			logger.info("Closed connection.");
		}
	}
}
package com.github.aureliano.achmed.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
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
			this.conversation();
			this.closeSocket();
		} catch (AchmedException ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
			ex.printStackTrace();
		}
	}
	
	private IService createService(String data) {
		ServiceType type = this.getServiceType(data);
		logger.info("Accessing service: " + type);
		return ServiceFactory.createService(type);
	}
	
	private ServiceType getServiceType(String data) {
		String serviceName = this.getRequestedService(data);
		try {
			return ServiceType.valueOf(serviceName);
		} catch (Exception ex) {
			throw new AchmedException("There is not a service named " + serviceName);
		}
	}
	
	private String getRequestedService(String data) {
		if (!data.matches(SERVICE_NAME_PATTERN)) {
			throw new AchmedException("Invalid service: " + data);
		}
		
		String serviceName = data.split(":")[1].trim();
		return serviceName.toUpperCase();
	}
	
	private void conversation() {
		try (
			BufferedReader reader = new BufferedReader(
				new InputStreamReader(this.socket.getInputStream()));
		) {
			IService service = this.createService(reader.readLine());
			Map<String, String> parameters = new HashMap<>();
			
			String[] tokens = reader.readLine().split(":");
			parameters.put(tokens[0].trim(), tokens[1].trim());
			byte[] response = service.consume(parameters);
			
			this.socket.getOutputStream().write(response);
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
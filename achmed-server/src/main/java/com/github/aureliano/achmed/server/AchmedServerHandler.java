package com.github.aureliano.achmed.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.aureliano.achmed.common.StatusCode;
import com.github.aureliano.achmed.common.logging.LoggingFactory;

public class AchmedServerHandler {

	private static final Logger logger = LoggingFactory.createLogger(AchmedServerHandler.class);
	private static AchmedServerHandler instance;
	
	private Integer portNumber;
	
	private AchmedServerHandler() {}
	
	public static AchmedServerHandler instance() {
		if (instance == null) {
			instance = new AchmedServerHandler();
		}
		
		return instance;
	}
	
	public void startUp(Integer portNumber) {
		this.configurePortNumber(portNumber);
		
		try (
			ServerSocket serverSocket = new ServerSocket(portNumber);
		) {
			logger.info("Achmed server started and listening on port " + portNumber);
			this.listen(serverSocket);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
			System.exit(StatusCode.SERVER_LISTEN_CONNECTION.getCode());
		}
	}
	
	public void shutDown() {
		logger.info("Achmed server has just shut down.");
	}
	
	private void listen(ServerSocket serverSocket) throws IOException {
		while (true) {
			Runnable thread = ThreadHandler.handle(serverSocket.accept());
			thread.run();
		}
	}
	
	private void configurePortNumber(Integer portNumber) {
		this.portNumber = portNumber;
		if ((this.portNumber == null) || (this.portNumber <= 0)) {
			logger.log(Level.SEVERE, "Invalid port number: " + this.portNumber);
			System.exit(StatusCode.CLI_PARAMETERS_ERROR.getCode());
		}
	}
}
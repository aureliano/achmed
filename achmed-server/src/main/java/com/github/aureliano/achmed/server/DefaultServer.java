package com.github.aureliano.achmed.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.aureliano.achmed.common.StatusCode;
import com.github.aureliano.achmed.common.logging.LoggingFactory;
import com.github.aureliano.achmed.server.conf.ServerConfiguration;
import com.github.aureliano.achmed.server.control.RequestedFilesControl;
import com.github.aureliano.achmed.server.handler.ThreadHandler;

public class DefaultServer {

	private static final Logger logger = LoggingFactory.createLogger(DefaultServer.class);
	private static DefaultServer instance;
	
	private ServerConfiguration configuration;
	private boolean serverRunning;
	
	private DefaultServer() {
		this.serverRunning = false;
	}
	
	public static DefaultServer instance() {
		if (instance == null) {
			instance = new DefaultServer();
		}
		
		return instance;
	}
	
	public void startUp(ServerConfiguration configuration) {
		this.configuration = configuration;
		
		try (
			ServerSocket serverSocket = new ServerSocket(this.configuration.getPortNumber());
		) {
			this.configureThreadExecutors();
			this.serverRunning = true;
			logger.info("Achmed server started and listening on port " + this.configuration.getPortNumber());
			
			this.listen(serverSocket);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
			System.exit(StatusCode.SERVER_LISTEN_CONNECTION.getCode());
		}
	}
	
	public void shutDown() {
		if (this.serverRunning) {
			this.serverRunning = false;
			logger.info("Achmed server has just shut down.");
		}
	}
	
	public String getFileRepository() {
		return this.configuration.getFileRepository();
	}
	
	private void listen(ServerSocket serverSocket) throws IOException {
		while (true) {
			Runnable runnable = ThreadHandler.handle(serverSocket.accept());
			new Thread(runnable).start();
		}
	}
	
	private void configureThreadExecutors() {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		final int minutes = 5;
		scheduler.scheduleAtFixedRate(
				this.createRequestedFilesCleaner(minutes), minutes, minutes, TimeUnit.MINUTES);
	}
	
	private Runnable createRequestedFilesCleaner(final int minutes) {
		return new Runnable() {
			
			@Override
			public void run() {
				RequestedFilesControl.instance().removeOldRequests(minutes * 1000 * 60);
			}
		};
	}
}
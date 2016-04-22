package com.github.aureliano.achmed.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.aureliano.achmed.common.StatusCode;
import com.github.aureliano.achmed.common.logging.LoggingFactory;
import com.github.aureliano.achmed.server.conf.ServerConfiguration;

public class AchmedServerHandler {

	private static final Logger logger = LoggingFactory.createLogger(AchmedServerHandler.class);
	private static AchmedServerHandler instance;
	
	private ServerConfiguration configuration;
	private boolean serverRunning;
	
	private AchmedServerHandler() {
		this.serverRunning = false;
	}
	
	public static AchmedServerHandler instance() {
		if (instance == null) {
			instance = new AchmedServerHandler();
		}
		
		return instance;
	}
	
	public void startUp(ServerConfiguration configuration) {
		this.configuration = configuration;
		
		InetSocketAddress inetSocketAddress = new InetSocketAddress(this.configuration.getPortNumber());
		try (
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()
		) {
			ServerSocket serverSocket = serverSocketChannel.socket();
			serverSocket.setReuseAddress(true);
			serverSocket.bind(inetSocketAddress);
			
			this.serverRunning = true;
			logger.info("Achmed server started and listening on port " + this.configuration.getPortNumber());
			
			this.listen(serverSocketChannel);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
			System.exit(StatusCode.SERVER_LISTEN_CONNECTION.getCode());
		}
		
		/*
		try (
			ServerSocket serverSocket = new ServerSocket(this.configuration.getPortNumber());
		) {
			this.serverRunning = true;
			logger.info("Achmed server started and listening on port " + this.configuration.getPortNumber());
			
			this.listen(serverSocket);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
			System.exit(StatusCode.SERVER_LISTEN_CONNECTION.getCode());
		}
		*/
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
	
	private void listen(ServerSocketChannel serverSocketChannel) throws IOException {
		while (true) {
			Runnable runnable = ThreadHandler.handle(serverSocketChannel.accept());
			new Thread(runnable).start();
		}
	}
}
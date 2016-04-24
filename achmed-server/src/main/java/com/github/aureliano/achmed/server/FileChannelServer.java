package com.github.aureliano.achmed.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.aureliano.achmed.common.StatusCode;
import com.github.aureliano.achmed.common.logging.LoggingFactory;
import com.github.aureliano.achmed.server.conf.ServerConfiguration;
import com.github.aureliano.achmed.server.handler.FileChannelThreadHandler;

public class FileChannelServer {

	private static final Logger logger = LoggingFactory.createLogger(FileChannelServer.class);
	private static FileChannelServer instance;
	
	private ServerConfiguration configuration;
	private boolean serverRunning;
	
	private FileChannelServer() {}
	
	public static FileChannelServer instance() {
		if (instance == null) {
			instance = new FileChannelServer();
		}
		
		return instance;
	}
	
	public void startUp(ServerConfiguration configuration) {
		this.configuration = configuration;
		int port = this.configuration.getPortNumber();// + 1;
		
		try (
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()
		) {
			serverSocketChannel.socket().bind(new InetSocketAddress(port));
			this.serverRunning = true;
			logger.info("File channel server started and listening on port " + port);
	        
	        this.listen(serverSocketChannel);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
			System.exit(StatusCode.SERVER_LISTEN_CONNECTION.getCode());
		}
	}
	
	private void listen(ServerSocketChannel serverSocketChannel) throws IOException {
		while (true) {
			Runnable runnable = FileChannelThreadHandler.handle(serverSocketChannel.accept());
			new Thread(runnable).start();
		}
	}

	public void shutDown() {
		if (this.serverRunning) {
			this.serverRunning = false;
			logger.info("Achmed server has just shut down.");
		}
	}
}
package com.github.aureliano.achmed.server.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.aureliano.achmed.common.exception.AchmedException;
import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.common.logging.LoggingFactory;
import com.github.aureliano.achmed.server.service.IService;
import com.github.aureliano.achmed.server.service.ServiceFactory;

public class FileChannelThreadHandler implements Runnable {

private static final Logger logger = LoggingFactory.createLogger(FileChannelThreadHandler.class);
	
	private SocketChannel socketChannel;
	private Socket socket;
	
	private FileChannelThreadHandler(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
		this.socket = socketChannel.socket();
	}
	
	public static Runnable handle(SocketChannel socketChannel) {
		FileChannelThreadHandler handler = new FileChannelThreadHandler(socketChannel);
		logger.info("Accepted connection from: " + handler.getRemoteAddress());
		return handler;
	}
	
	@Override
	public void run() {
		try {
			this.conversation();
			this.closeConnection();
			logger.info("Request processed. Server is done.");
		} catch (AchmedException ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
			ex.printStackTrace();
		}
	}
	
	private void conversation() {
		try (
			BufferedReader reader = new BufferedReader(
				new InputStreamReader(this.socket.getInputStream()));
			OutputStream writer = this.socket.getOutputStream();
		) {
			String path = this.getRequestedFilePath(reader);
			if (StringHelper.isEmpty(path)) {
				return;
			}
			
			this.sendData(path);
		} catch (IOException ex) {
			throw new AchmedException(ex);
		}
	}
	
	private void sendData(String path) throws IOException {
		File file = new File(path);
		logger.info("Preparing to send file " + path + " with size " + file.length());
		long init = System.currentTimeMillis();
		long totalBytesTransferred = 0;
		
		try (
			FileInputStream stream = new FileInputStream(file);
			FileChannel fileChannel = stream.getChannel();
		) {
			while (totalBytesTransferred < file.length()) {
				long bytesLeft = file.length() - totalBytesTransferred;
				long bytesTransferred = fileChannel.transferTo(
						totalBytesTransferred, bytesLeft, this.socketChannel);
				
				totalBytesTransferred += bytesTransferred;
			}
		}
		
		long diff = System.currentTimeMillis() - init;
		logger.info("Total bytes transferred: " + totalBytesTransferred);
		logger.info("Time " + (diff / 1000) + " seconds");
	}
	
	private String getRequestedFilePath(BufferedReader reader) throws IOException {
		IService service = ServiceFactory.createService(reader.readLine());
		Map<String, String> parameters = new HashMap<>();
		
		String[] tokens = reader.readLine().split(":");
		parameters.put(tokens[0].trim(), tokens[1].trim());
		String response = service.consume(this.socket, parameters);
		
		return response;
	}
	
	private void closeConnection() {
		if (this.socketChannel.isOpen()) {
			try {
				this.socketChannel.close();
			} catch (IOException ex) {
				throw new AchmedException(ex);
			}
		}
	}
	
	private String getRemoteAddress() {
		return this.socket.getInetAddress().getHostAddress();
	}
}
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
import java.nio.ByteBuffer;
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
import com.github.aureliano.achmed.server.service.ServiceType;

public class ThreadHandler implements Runnable {
	
	private static final String SERVICE_PATTERN = "^service:\\s*";
	private static final String SERVICE_NAME_PATTERN = SERVICE_PATTERN + "(read_file)$";
	private static final Logger logger = LoggingFactory.createLogger(ThreadHandler.class);
	
	private SocketChannel socket;
	
	private ThreadHandler(SocketChannel socket) {
		this.socket = socket;
	}
	
	public static Runnable handle(SocketChannel socket) {
		logger.info("Accepted connection from: " + socket);
		return new ThreadHandler(socket);
	}
	
	@Override
	public void run() {
		try {
			this.conversation();
			logger.info("Request processed.");
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
		ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
		try {
			while (true) {
				this.socket.configureBlocking(true);
				int nread = 0;
				
				while (nread != -1) {
					try {
						nread = this.socket.read(byteBuffer);
					} catch (IOException ex) {
						logger.warning(ex.getMessage());
						nread = -1;
					}
					byteBuffer.rewind();
				}
			}
		} catch (IOException ex) {
			throw new AchmedException(ex);
		}
		
		
		
		/*
		try (
			BufferedReader reader = new BufferedReader(
				new InputStreamReader(this.socket.getInputStream()));
			OutputStream writer = this.socket.getOutputStream();
		) {
			IService service = this.createService(reader.readLine());
			Map<String, String> parameters = new HashMap<>();
			
			String[] tokens = reader.readLine().split(":");
			parameters.put(tokens[0].trim(), tokens[1].trim());
			byte[] response = service.consume(writer, parameters);
			
			if ((response != null) && (response.length > 0)) {
				writer.write(response);
				writer.flush();
			}
		} catch (IOException ex) {
			throw new AchmedException(ex);
		}*/
	}
}

/*
 		Socket socket = new Socket("127.0.0.1", 9876);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println("service: read_file");
		out.println("resource: eclipse.tar.gz");
		
		int count;
		byte[] buffer = new byte[1024];
		
		long init = System.currentTimeMillis();
		
		String host = "127.0.0.1";
		SocketAddress sad = new InetSocketAddress(host, 9876);
		SocketChannel sc = SocketChannel.open();
		sc.connect(sad);
		sc.configureBlocking(true);

		String fname = "/tmp/eclipse.tar.gz";
		long fileSize = new File(fname).length();

		FileChannel fc = new FileInputStream(fname).getChannel();
		long start = System.currentTimeMillis();
		long curnset = 0;
		curnset = fc.transferTo(0, fileSize, sc);
		System.out.println("total bytes transferred: " + curnset + " and time taken:" + (System.currentTimeMillis() - start));
		
		/*
		FileOutputStream writer = new FileOutputStream(new File("/tmp/another-eclipse.tar.gz"));
		InputStream stream = socket.getInputStream();
		
		while ((count = stream.read(buffer)) > 0) {
			writer.write(buffer, 0, count);
		}
		
		writer.flush();
		
		stream.close();
		writer.close();
		
		System.out.println("Time millis: " + (System.currentTimeMillis() - init));
*/

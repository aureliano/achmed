package com.github.aureliano.achmed.server.service;

import java.net.Socket;
import java.util.Map;

public class ReadFileService implements IService {

	public ReadFileService() {}
	
	@Override
	public void consume(Socket socket, Map<String, String> parameters) {
		String resourcePath = parameters.get("resource");
		System.out.println(" >>> Resource path: " + resourcePath);
	}
}
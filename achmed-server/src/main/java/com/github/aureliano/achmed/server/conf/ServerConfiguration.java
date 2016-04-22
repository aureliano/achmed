package com.github.aureliano.achmed.server.conf;

import java.io.File;
import java.util.Properties;

import com.github.aureliano.achmed.common.exception.AchmedException;
import com.github.aureliano.achmed.common.helper.StringHelper;

public class ServerConfiguration {

	private Integer portNumber;
	private String fileRepository;
	
	public ServerConfiguration() {}

	public Integer getPortNumber() {
		return portNumber;
	}

	public ServerConfiguration withPortNumber(Integer portNumber) {
		this.portNumber = portNumber;
		return this;
	}

	public String getFileRepository() {
		return fileRepository;
	}

	public ServerConfiguration withFileRepository(String fileRepository) {
		this.fileRepository = fileRepository;
		return this;
	}
	
	public static ServerConfiguration build(Properties properties) {
		validate(properties);
		
		return new ServerConfiguration()
			.withPortNumber(Integer.parseInt(properties.getProperty("port")))
			.withFileRepository(properties.getProperty("file.repository"));
	}
	
	private static void validate(Properties properties) {
		String property = properties.getProperty("port");
		if (StringHelper.isEmpty(property)) {
			throw new AchmedException("Empty port number.");
		} else if (!property.matches("\\d+")) {
			throw new AchmedException("Port number must be a valid integer.");
		}
		
		property = properties.getProperty("file.repository");
		if (StringHelper.isEmpty(property)) {
			throw new AchmedException("Empty file repository.");
		}
		
		File repo = new File(property);
		if (!repo.isDirectory()) {
			throw new AchmedException(repo.getAbsolutePath() + " is not a directory.");
		}
	}
}
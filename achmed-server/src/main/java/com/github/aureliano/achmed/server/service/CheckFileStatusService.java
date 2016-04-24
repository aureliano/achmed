package com.github.aureliano.achmed.server.service;

import java.io.File;
import java.io.OutputStream;
import java.util.Map;
import java.util.logging.Logger;

import com.github.aureliano.achmed.common.exception.AchmedException;
import com.github.aureliano.achmed.common.helper.FileHelper;
import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.common.logging.LoggingFactory;
import com.github.aureliano.achmed.server.DefaultServer;

public class CheckFileStatusService implements IService {

	private static final Logger logger = LoggingFactory.createLogger(CheckFileStatusService.class);
	
	public CheckFileStatusService() {}
	
	@Override
	public String consume(OutputStream outputStream, Map<String, String> parameters) {
		String resourcePath = parameters.get("resource");
		if (StringHelper.isEmpty(resourcePath)) {
			throw new AchmedException("Empty resource path.");
		}
		
		File requestedFile = FileHelper.buildFile(DefaultServer.instance().getFileRepository(), resourcePath);
		if (!(requestedFile.exists() && requestedFile.isFile())) {
			logger.warning("Requested resource path [ " + requestedFile + " ] does not exist.");
			return Boolean.FALSE.toString();
		}
		
		return Boolean.TRUE.toString();
	}
}
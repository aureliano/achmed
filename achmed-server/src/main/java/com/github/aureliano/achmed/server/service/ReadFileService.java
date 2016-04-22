package com.github.aureliano.achmed.server.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;
import java.util.logging.Logger;

import com.github.aureliano.achmed.common.exception.AchmedException;
import com.github.aureliano.achmed.common.helper.FileHelper;
import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.common.logging.LoggingFactory;
import com.github.aureliano.achmed.server.AchmedServerHandler;

public class ReadFileService implements IService {

	private static final Logger logger = LoggingFactory.createLogger(ReadFileService.class);
	
	public ReadFileService() {}
	
	@Override
	public byte[] consume(OutputStream outputStream, Map<String, String> parameters) {
		String resourcePath = parameters.get("resource");
		if (StringHelper.isEmpty(resourcePath)) {
			throw new AchmedException("Empty resource path.");
		}
		
		File requestedFile = FileHelper.buildFile(AchmedServerHandler.instance().getFileRepository(), resourcePath);
		if (!(requestedFile.exists() && requestedFile.isFile())) {
			logger.warning("Requested resource path [ " + requestedFile + " ] does not exist.");
			return new byte[0];
		}
		
		int count;
		byte[] buffer = new byte[1024];
		
		try (
			InputStream stream = new FileInputStream(requestedFile)
		) {
			logger.info("Sending bytes of file " + requestedFile);
			while ((count = stream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, count);
			}
			
			outputStream.flush();
		} catch (IOException ex) {
			throw new AchmedException(ex);
		}
		
		return new byte[0];
	}
}
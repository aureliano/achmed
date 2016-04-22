package com.github.aureliano.achmed.server.service;

import java.io.File;
import java.net.Socket;
import java.util.Map;

import com.github.aureliano.achmed.common.exception.AchmedException;
import com.github.aureliano.achmed.common.helper.FileHelper;
import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.server.AchmedServerHandler;

public class ReadFileService implements IService {

	public ReadFileService() {}
	
	@Override
	public void consume(Socket socket, Map<String, String> parameters) {
		String resourcePath = parameters.get("resource");
		if (StringHelper.isEmpty(resourcePath)) {
			throw new AchmedException("Empty resource path.");
		}
		
		File requestedFile = FileHelper.buildFile(AchmedServerHandler.instance().getFileRepository(), resourcePath);
		if (!(requestedFile.exists() && requestedFile.isFile())) {
			System.out.println(" >>> Resource path: " + resourcePath);
		}
	}
}
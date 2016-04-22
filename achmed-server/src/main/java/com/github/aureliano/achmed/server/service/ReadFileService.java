package com.github.aureliano.achmed.server.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.github.aureliano.achmed.common.exception.AchmedException;
import com.github.aureliano.achmed.common.helper.FileHelper;
import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.server.AchmedServerHandler;

public class ReadFileService implements IService {

	public ReadFileService() {}
	
	@Override
	public byte[] consume(Map<String, String> parameters) {
		String resourcePath = parameters.get("resource");
		if (StringHelper.isEmpty(resourcePath)) {
			throw new AchmedException("Empty resource path.");
		}
		
		File requestedFile = FileHelper.buildFile(AchmedServerHandler.instance().getFileRepository(), resourcePath);
		if (!(requestedFile.exists() && requestedFile.isFile())) {
			try {
				return (" >>> Resource path: " + resourcePath).getBytes("UTF-8");
			} catch (UnsupportedEncodingException ex) {
				throw new AchmedException(ex);
			}
		}
		
		return new byte[0];
	}
}
package com.github.aureliano.achmed.os.fs;

import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.exception.FileResourceException;
import com.github.aureliano.achmed.resources.properties.FileProperties;

public class PosixFileProvider implements IFileProvider {

	private FileProperties properties;
	
	public PosixFileProvider() {
		super();
	}

	@Override
	public void setFileMode() {
		CommandResponse res = CommandFacade.executeCommand(
			"chmod", this.properties.getMode(), this.properties.getPath());
		
		if (!res.isOK()) {
			throw new FileResourceException(res);
		}
	}

	@Override
	public void setFileOwner() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public void copyFile() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public void copyFolder() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public void setFileProperties(FileProperties properties) {
		this.properties = properties;
	}

	@Override
	public FileProperties getFileProperties() {
		return this.properties;
	}
}
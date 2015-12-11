package com.github.aureliano.achmed.os.fs;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.exception.FileResourceException;
import com.github.aureliano.achmed.helper.StringHelper;
import com.github.aureliano.achmed.resources.properties.FileProperties;

public class PosixFileProvider implements IFileProvider {

	private static final Logger logger = Logger.getLogger(PosixFileProvider.class);
	
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
		List<String> ownerAndGroup = new ArrayList<String>();
		if (!StringHelper.isEmpty(this.properties.getOwner())) {
			ownerAndGroup.add(this.properties.getOwner());
		}
		
		if ((!StringHelper.isEmpty(this.properties.getOwner())) && (!StringHelper.isEmpty(this.properties.getGroup()))) {
			ownerAndGroup.add(":");
		}
		
		if (!StringHelper.isEmpty(this.properties.getGroup())) {
			ownerAndGroup.add(this.properties.getOwner());
		}
		
		if (ownerAndGroup.isEmpty()) {
			logger.warn("Ignore setting file owner because no owner neither group was provided.");
			return;
		}
		
		ownerAndGroup.add(0, "chown");
		CommandResponse res = CommandFacade.executeCommand(ownerAndGroup.toArray(new String[0]));
		
		if (!res.isOK()) {
			throw new FileResourceException(res);
		}
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
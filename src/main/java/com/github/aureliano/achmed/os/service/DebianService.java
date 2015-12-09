package com.github.aureliano.achmed.os.service;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.os.Debian;
import com.github.aureliano.achmed.os.Linux;

public class DebianService extends LinuxService {

	private static final Logger logger = Logger.getLogger(DebianService.class);
	
	private Linux linux;
	
	public DebianService() {
		this.linux = new Debian();
	}

	public CommandResponse enableBootstrap() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public CommandResponse disableBootstrap() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
}
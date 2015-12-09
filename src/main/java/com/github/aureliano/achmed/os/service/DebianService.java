package com.github.aureliano.achmed.os.service;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.helper.StringHelper;

public class DebianService extends LinuxService {

	private static final Logger logger = Logger.getLogger(DebianService.class);
	private static final String SERVICE_APP = "service";
	
	public DebianService() {
		super();
	}
	
	@Override
	public CommandResponse start() {
		if (this.isRunning()) {
			logger.debug("Service " + super.properties.getName() + " is already running.");
			return null;
		}
		
		if (StringHelper.isEmpty(super.properties.getBinary())) {
			return CommandFacade.executeCommand(SERVICE_APP, super.properties.getName(), "start");
		}
		
		return super.start();
	}
	
	@Override
	public boolean isRunning() {
		if ((super.properties.getHasStatus() != null) && (super.properties.getHasStatus())) {
			CommandResponse res = CommandFacade.executeCommand(SERVICE_APP, super.properties.getName(), "status");
			return (res.getExitStatusCode() == 0);
		}
		
		return super.isRunning();
	}

	public CommandResponse enableBootstrap() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public CommandResponse disableBootstrap() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
}
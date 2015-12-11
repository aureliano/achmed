package com.github.aureliano.achmed.os.service;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;

public class RedHatService extends LinuxService {

	private static final Logger logger = Logger.getLogger(RedHatService.class);
	private static final String SERVICE = "/sbin/service";
	
	public RedHatService() {
		super();
	}
	
	@Override
	public boolean isRunning() {
		if ((super.properties.getHasStatus() != null) && (super.properties.getHasStatus())) {
			CommandResponse res = CommandFacade.executeCommand(SERVICE, super.properties.getName(), "status");
			return (res.getExitStatusCode() == 0);
		}
		
		return super.isRunning();
	}

	@Override
	public CommandResponse enableBootstrap() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public CommandResponse disableBootstrap() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public boolean isEnabledInBootstrap() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
}
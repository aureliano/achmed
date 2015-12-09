package com.github.aureliano.achmed.os.service;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.helper.StringHelper;

public class DebianService extends LinuxService {

	private static final Logger logger = Logger.getLogger(DebianService.class);
	private static final String SERVICE = "/usr/sbin/service";
	private static final String UPDATE_RC = "/usr/sbin/update-rc.d";
	private static final String INVOKE_RC = "/usr/sbin/invoke-rc.d";
	
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
			return CommandFacade.executeCommand(SERVICE, super.properties.getName(), "start");
		}
		
		return super.start();
	}
	
	@Override
	public CommandResponse stop() {
		if (!this.isRunning()) {
			logger.debug("Service " + super.properties.getName() + " is not running.");
			return null;
		}
		
		if (StringHelper.isEmpty(super.properties.getBinary())) {
			return CommandFacade.executeCommand(SERVICE, super.properties.getName(), "stop");
		}
		
		return super.stop();
	}
	
	@Override
	public CommandResponse restart() {
		if ((super.properties.getHasRestart() != null) && (super.properties.getHasRestart())) {
			return CommandFacade.executeCommand(SERVICE, super.properties.getName(), "restart");
		}
		
		return super.restart();
	}
	
	@Override
	public boolean isRunning() {
		if ((super.properties.getHasStatus() != null) && (super.properties.getHasStatus())) {
			CommandResponse res = CommandFacade.executeCommand(SERVICE, super.properties.getName(), "status");
			return (res.getExitStatusCode() == 0);
		}
		
		return super.isRunning();
	}
	
	public boolean isEnabledInBootstrap() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public CommandResponse enableBootstrap() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public CommandResponse disableBootstrap() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
}
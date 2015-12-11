package com.github.aureliano.achmed.os.service;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.exception.ServiceResourceException;
import com.github.aureliano.achmed.helper.StringHelper;

public class RedHatService extends LinuxService {

	private static final Logger logger = Logger.getLogger(RedHatService.class);
	private static final String SERVICE = "/sbin/service";
	private static final String CHKCONFIG = "/sbin/chkconfig";
	
	public RedHatService() {
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

	@Override
	public CommandResponse enableBootstrap() {
		CommandResponse res = CommandFacade.executeCommand(CHKCONFIG, "--add", super.properties.getName());
		if (!res.isOK()) {
			throw new ServiceResourceException(res);
		}
		
		return CommandFacade.executeCommand(CHKCONFIG, super.properties.getName(), "on");
	}

	@Override
	public CommandResponse disableBootstrap() {
		return CommandFacade.executeCommand(CHKCONFIG, "--level", "0123456", super.properties.getName(), "off");
	}

	@Override
	public boolean isEnabledInBootstrap() {
		CommandResponse res = CommandFacade.executeCommand(CHKCONFIG, super.properties.getName());
		return res.isOK();
	}
}
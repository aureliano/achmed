package com.github.aureliano.achmed.os.service;

import java.util.logging.Logger;

import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.exception.ServiceResourceException;
import com.github.aureliano.achmed.logging.LoggingFactory;

public class RedHatService extends LinuxService {

	private static final Logger logger = LoggingFactory.createLogger(RedHatService.class);
	private static final String SERVICE = "/sbin/service";
	private static final String CHKCONFIG = "/sbin/chkconfig";
	
	public RedHatService() {
		super();
	}
	
	@Override
	public CommandResponse start() {
		if (this.isRunning()) {
			logger.fine("Service " + super.properties.getName() + " is already running.");
			return null;
		}
		
		if (StringHelper.isEmpty(super.properties.getBinary())) {
			CommandResponse res = CommandFacade.executeCommand(SERVICE, super.properties.getName(), "start");
			super.assertServiceIsRunning();
			
			return res;
		}
		
		CommandResponse res = super.start();
		super.assertServiceIsRunning();
		
		return res;
	}
	
	@Override
	public CommandResponse stop() {
		if (!this.isRunning()) {
			logger.fine("Service " + super.properties.getName() + " is not running.");
			return null;
		}
		
		if (StringHelper.isEmpty(super.properties.getBinary())) {
			CommandResponse res = CommandFacade.executeCommand(SERVICE, super.properties.getName(), "stop");
			super.assertServiceIsStopped();
			
			return res;
		}
		
		CommandResponse res = super.stop();
		super.assertServiceIsStopped();
		
		return res;
	}
	
	@Override
	public CommandResponse restart() {
		if ((super.properties.getHasRestart() != null) && (super.properties.getHasRestart())) {
			CommandResponse res = CommandFacade.executeCommand(SERVICE, super.properties.getName(), "restart");
			super.assertServiceIsRunning();
			
			return res;
		}
		
		CommandResponse res = super.restart();
		super.assertServiceIsRunning();
		
		return res;
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
		
		res = CommandFacade.executeCommand(CHKCONFIG, super.properties.getName(), "on");
		super.assertServiceIsEnabledInBootstrap();
		
		return res;
	}

	@Override
	public CommandResponse disableBootstrap() {
		CommandResponse res = CommandFacade.executeCommand(
			CHKCONFIG, "--level", "0123456", super.properties.getName(), "off"
		);
		super.assertServiceIsDisabledInBootstrap();
		
		return res;
	}

	@Override
	public boolean isEnabledInBootstrap() {
		CommandResponse res = CommandFacade.executeCommand(CHKCONFIG, super.properties.getName());
		return res.isOK();
	}
}
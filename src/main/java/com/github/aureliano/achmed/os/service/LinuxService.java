package com.github.aureliano.achmed.os.service;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.exception.ServiceResourceException;
import com.github.aureliano.achmed.helper.StringHelper;
import com.github.aureliano.achmed.os.Linux;
import com.github.aureliano.achmed.os.pkg.IPackageManager;
import com.github.aureliano.achmed.types.OperatingSystemFamily;

public abstract class LinuxService extends BaseService {
	
	private static final Logger logger = Logger.getLogger(LinuxService.class);

	private Linux linux;
	
	public LinuxService() {
		this.linux = this.createLinux();
	}

	public CommandResponse start() {
		if (this.isRunning()) {
			logger.debug("Service " + super.properties.getName() + " is already running.");
			return null;
		}
		
		if (StringHelper.isEmpty(super.properties.getBinary())) {
			throw new ServiceResourceException(
				"Binary file of service " + super.properties.getName() + " not provided.");
		} else if (StringHelper.isEmpty(super.properties.getStart())) {
			throw new ServiceResourceException(
					"Start command of service " + super.properties.getName() +
					" was not provided to be used with binary command " + super.properties.getBinary());
		}
		
		return CommandFacade.executeCommand(
			super.properties.getBinary(),
			super.properties.getStart()
		);
	}

	public CommandResponse stop() {
		if (!this.isRunning()) {
			logger.debug("Service " + super.properties.getName() + " is not running.");
			return null;
		}
		
		if (StringHelper.isEmpty(super.properties.getBinary())) {
			throw new ServiceResourceException(
				"Binary file of service " + super.properties.getName() + " not provided.");
		} else if (StringHelper.isEmpty(super.properties.getStop())) {
			throw new ServiceResourceException(
					"Stop command of service " + super.properties.getName() +
					" was not provided to be used with binary command " + super.properties.getBinary());
		}
		
		return CommandFacade.executeCommand(
			super.properties.getBinary(),
			super.properties.getStop()
		);
	}

	public CommandResponse restart() {
		CommandResponse res = this.stop();
		if ((res != null) && (!res.isOK())) {
			throw new ServiceResourceException(
				"Could not stop service " + super.properties.getName() + ". Detail: " + res.getError());
		}
		
		return this.start();
	}

	public boolean isRunning() {
		Integer pid = this.linux.getPid(super.properties.getPattern());
		return (pid != null);
	}

	private Linux createLinux() {
		return new Linux() {
			
			public OperatingSystemFamily getOperatingSystemFamily() {
				return null;
			}
			
			public IPackageManager getDefaultPackageManager() {
				return null;
			}
		};
	}
}
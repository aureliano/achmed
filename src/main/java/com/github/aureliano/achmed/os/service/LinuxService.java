package com.github.aureliano.achmed.os.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.exception.ServiceResourceException;
import com.github.aureliano.achmed.logging.LoggingFactory;
import com.github.aureliano.achmed.os.Linux;
import com.github.aureliano.achmed.os.pkg.IPackageManager;
import com.github.aureliano.achmed.types.OperatingSystemFamily;

public abstract class LinuxService extends BaseService {
	
	private static final Logger logger = LoggingFactory.createLogger(LinuxService.class);

	private Linux linux;
	
	public LinuxService() {
		this.linux = this.createLinux();
	}

	public CommandResponse start() {
		if (this.isRunning()) {
			logger.fine("Service " + super.properties.getName() + " is already running.");
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
		
		return this.executeCommand(super.properties.getStart());
	}

	public CommandResponse stop() {
		if (!this.isRunning()) {
			logger.fine("Service " + super.properties.getName() + " is not running.");
			return null;
		}
		
		if (StringHelper.isEmpty(super.properties.getBinary())) {
			throw new ServiceResourceException(
				"Binary file of service " + super.properties.getName() + " not provided.");
		}
		
		if (StringHelper.isEmpty(super.properties.getStop())) {
			Integer pid = this.linux.getPid(super.properties.getPattern());
			CommandResponse res = this.linux.kill(pid);
			if (res.isOK()) {
				return res;
			}
			
			throw new ServiceResourceException(res);
		}
		
		return this.executeCommand(super.properties.getStop());
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

			public IService getDefaultServiceManager() {
				return null;
			}
			
			public Linux prototype() {
				return null;
			}
		};
	}
	
	private String[] buildCommand(String binary, String flags, String action) {
		List<String> command = new ArrayList<>();
		
		command.add(binary);
		if (!StringHelper.isEmpty(flags)) {
			command.addAll(Arrays.asList(flags.split("\\s+")));
		}
		command.add(action);
		
		return command.toArray(new String[0]);
	}
	
	private CommandResponse executeCommand(String action) {
		return CommandFacade.executeCommand(
			this.buildCommand(
				super.properties.getBinary(),
				super.properties.getFlags(),
				action
			)
		);
	}
}
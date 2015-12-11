package com.github.aureliano.achmed.resources;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.command.CommandBuilder;
import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.exception.ExecResourceException;
import com.github.aureliano.achmed.helper.StringHelper;
import com.github.aureliano.achmed.resources.properties.ExecProperties;
import com.github.aureliano.achmed.resources.properties.IResourceProperties;

public class ExecResource implements IResource {

	private static final Logger logger = Logger.getLogger(ExecResource.class);
	
	private ExecProperties properties;
	
	public ExecResource() {
		this.properties = new ExecProperties();
	}
	
	public ExecResource(ExecProperties properties) {
		this.properties = properties;
	}

	public void apply() {
		logger.info(" >>> Apply exec resource with command: " + this.properties.getCommand());
		
		this.properties.configureAttributes();
		
		if (this.canExecute()) {
			this.execute();
		}
	}
	
	public void setProperties(IResourceProperties properties) {
		this.properties = (ExecProperties) properties;
	}
	
	public IResourceProperties getProperties() {
		return this.properties;
	}

	public ResourceType type() {
		return ResourceType.EXEC;
	}
	
	private boolean canExecute() {
		boolean canExecute = true;
		
		if (!StringHelper.isEmpty(this.properties.getOnlyIf())) {
			canExecute = canExecute && this.shouldExecute(true, this.properties.getOnlyIf());
		}
		
		if (!StringHelper.isEmpty(this.properties.getUnless())) {
			canExecute = canExecute && this.shouldExecute(false, this.properties.getUnless());
		}
		
		return canExecute;
	}
	
	private boolean shouldExecute(boolean queryCase, String cmd) {
		CommandBuilder commandBuilder = this.createCommandBuilder(cmd);
		CommandResponse res = CommandFacade.executeCommand(commandBuilder);
		
		return (queryCase == res.isOK());
	}
	
	private void execute() {
		CommandResponse res = CommandFacade.executeCommand(this.properties);
		
		if (!res.isOK()) {
			throw new ExecResourceException(res);
		}
	}
	
	private CommandBuilder createCommandBuilder(String command) {
		return new CommandBuilder()
			.withCommand(command)
			.withWorkingDir(this.properties.getCwd())
			.withVerbose(this.properties.isVerbose());
	}
}
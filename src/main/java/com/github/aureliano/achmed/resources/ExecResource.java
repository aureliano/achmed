package com.github.aureliano.achmed.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.github.aureliano.achmed.client.command.CommandBuilder;
import com.github.aureliano.achmed.client.command.CommandFacade;
import com.github.aureliano.achmed.client.command.CommandResponse;
import com.github.aureliano.achmed.common.helper.FileHelper;
import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.common.logging.LoggingFactory;
import com.github.aureliano.achmed.exception.ExecResourceException;
import com.github.aureliano.achmed.resources.properties.ExecProperties;
import com.github.aureliano.achmed.resources.properties.IResourceProperties;

public class ExecResource implements IResource {

	private static final Logger logger = LoggingFactory.createLogger(ExecResource.class);
	
	private ExecProperties properties;
	
	public ExecResource() {
		this.properties = new ExecProperties();
	}
	
	public ExecResource(ExecProperties properties) {
		this.properties = properties;
	}

	public void apply() {
		this.properties.configureAttributes();
		String command = this.commandPresentation();
		
		logger.fine("Resource description: " + this.properties.get("description"));
		logger.info(" >>> Apply exec resource with command: " + command);
		
		if (this.canExecute()) {
			this.execute();
		}
	}
	
	private String commandPresentation() {
		StringBuilder cmd = new StringBuilder(this.properties.getCommand());
		if (!this.properties.getArguments().isEmpty()) {
			cmd
				.append(" ")
				.append(StringHelper.join(this.properties.getArguments(), " "));
		}
		
		return cmd.toString();
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
		cmd = StringHelper.amendEnvVars(cmd);
		CommandBuilder commandBuilder = this.createCommandBuilder(cmd);
		CommandResponse res = CommandFacade.executeCommand(commandBuilder);
		
		return (queryCase == res.isOK());
	}
	
	private void execute() {
		this.properties.setCommand(StringHelper.amendEnvVars(this.properties.getCommand()));
		if ((this.properties.getAmendargs() != null) && (this.properties.getAmendargs() == true)) {
			List<String> args = new ArrayList<>(this.properties.getArguments().size());
			for (String arg : this.properties.getArguments()) {
				args.add(StringHelper.amendEnvVars(arg));
			}
			
			this.properties.setArguments(args);
		}
		
		CommandResponse res = CommandFacade.executeCommand(this.properties);
		
		if (!res.isOK()) {
			throw new ExecResourceException(res);
		}
	}
	
	private CommandBuilder createCommandBuilder(String command) {
		return new CommandBuilder()
			.withCommand(command)
			.withWorkingDir(FileHelper.amendFilePath(this.properties.getCwd()))
			.withVerbose(this.properties.getVerbose())
			.withTimeout(CommandFacade.DEFAULT_TIMEOUT_EXECUTION)
			.withTries(1);
	}
}
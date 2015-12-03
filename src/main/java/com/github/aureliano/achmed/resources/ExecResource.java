package com.github.aureliano.achmed.resources;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.exception.ExecResourceException;
import com.github.aureliano.achmed.resources.exec.CommandFacade;
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
		int exitStatusCode = 0;
		int tries = 0;
		
		do {
			exitStatusCode = this.execute();
			tries ++;
			
			logger.debug("Exit status code: " + exitStatusCode);
			logger.debug("Tries: " + tries);
		} while ((tries < this.properties.getTries()) && (exitStatusCode != 0));
		
		if (exitStatusCode != 0) {
			throw new ExecResourceException(this.executionErrorMessage(exitStatusCode));
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
	
	private int execute() {
		try {
			return CommandFacade.executeCommand(this.properties);
		} catch (ExecResourceException ex) {
			return 100;
		}
	}
	
	private String executionErrorMessage(int exitStatusCode) {
		return String.format(
			"Command [%s] exited with status code [%d]",
			this.properties.getCommand(),
			exitStatusCode
		);
	}
}
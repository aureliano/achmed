package com.github.aureliano.achmed.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.github.aureliano.achmed.client.exception.AchmedValidationException;
import com.github.aureliano.achmed.client.resources.IResource;
import com.github.aureliano.achmed.client.validation.ConstraintViolation;
import com.github.aureliano.achmed.client.validation.ObjectValidator;
import com.github.aureliano.achmed.common.logging.LoggingFactory;

public class Agent {
	
	private static final Logger logger = LoggingFactory.createLogger(Agent.class);

	private List<IResource> resources;
	
	public Agent() {
		this.resources = new ArrayList<IResource>();
	}
	
	public Agent addResource(IResource resource) {
		this.resources.add(resource);
		return this;
	}
	
	public Agent withResources(List<IResource> resources) {
		this.resources = resources;
		return this;
	}
	
	public List<IResource> getResources() {
		return this.resources;
	}
	
	public void apply() {
		if (this.resources.isEmpty()) {
			logger.info("There isn't any resource to apply.");
			return;
		}
		
		logger.info("Preparing to apply " + this.resources.size() + " resources.");
		logger.info("Applying resource validations.");
		
		this.validate();
		
		logger.info("Resources validated.");
		logger.info("Applying resources.");
		
		for (IResource resource : this.resources) {
			resource.apply();
		}
	}
	
	private void validate() {
		boolean failed = false;
		ObjectValidator validator = ObjectValidator.instance();
		
		for (IResource resource : this.resources) {
			Set<ConstraintViolation> violations = validator.validate(resource);
			if (violations.isEmpty()) {
				continue;
			}
			
			for (ConstraintViolation violation : violations) {
				failed = true;
				logger.severe(violation.getMessage());
			}
		}
		
		if (failed) {
			throw new AchmedValidationException("Resource validation has failed.");
		}
	}
}
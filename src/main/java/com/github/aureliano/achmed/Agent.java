package com.github.aureliano.achmed;

import java.util.ArrayList;
import java.util.List;

import com.github.aureliano.achmed.resources.IResource;

public class Agent {

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
		throw new UnsupportedOperationException("Not implemented exception.");
	}
}
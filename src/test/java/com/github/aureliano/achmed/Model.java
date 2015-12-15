package com.github.aureliano.achmed;

import com.github.aureliano.achmed.annotation.NotNull;

public class Model {

	private String id;
	
	public Model() {
		super();
	}

	@NotNull
	public String getId() {
		return this.id;
	}

	public Model withId(String id) {
		this.id = id;
		return this;
	}
}
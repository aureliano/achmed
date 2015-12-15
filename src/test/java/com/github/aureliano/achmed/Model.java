package com.github.aureliano.achmed;

import com.github.aureliano.achmed.annotation.NotEmpty;
import com.github.aureliano.achmed.annotation.NotNull;
import com.github.aureliano.achmed.annotation.Pattern;

public class Model {

	private String id;
	
	public Model() {
		super();
	}

	@NotNull
	@NotEmpty
	@Pattern(value = "[\\d\\w]{3,5}")
	public String getId() {
		return this.id;
	}

	public Model withId(String id) {
		this.id = id;
		return this;
	}
}
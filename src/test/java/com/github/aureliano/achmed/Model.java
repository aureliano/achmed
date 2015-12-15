package com.github.aureliano.achmed;

import java.util.ArrayList;
import java.util.List;

import com.github.aureliano.achmed.annotation.Min;
import com.github.aureliano.achmed.annotation.NotEmpty;
import com.github.aureliano.achmed.annotation.NotNull;
import com.github.aureliano.achmed.annotation.Pattern;

public class Model {

	private String id;
	private List<String> exceptions;
	
	public Model() {
		this.exceptions = new ArrayList<>();
	}

	@NotNull
	@NotEmpty
	@Pattern(value = "[\\d\\w]{3,5}")
	@Min(value = 3)
	public String getId() {
		return this.id;
	}

	public Model withId(String id) {
		this.id = id;
		return this;
	}

	public Model addException(String ex) {
		this.exceptions.add(ex);
		return this;
	}

	@Min(value = 1)
	public List<String> getExceptions() {
		return this.exceptions;
	}
}
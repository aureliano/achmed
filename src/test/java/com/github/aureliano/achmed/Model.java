package com.github.aureliano.achmed;

import java.util.ArrayList;
import java.util.List;

import com.github.aureliano.achmed.annotation.Max;
import com.github.aureliano.achmed.annotation.Min;
import com.github.aureliano.achmed.annotation.NotEmpty;
import com.github.aureliano.achmed.annotation.NotNull;
import com.github.aureliano.achmed.annotation.Pattern;
import com.github.aureliano.achmed.annotation.Size;

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
	@Max(value = 5)
	@Size(min = 3, max = 5)
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
	@Max(value = 1)
	@Size(min = 1, max = 1)
	public List<String> getExceptions() {
		return this.exceptions;
	}
}
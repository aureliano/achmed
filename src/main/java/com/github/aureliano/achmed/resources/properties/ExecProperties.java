package com.github.aureliano.achmed.resources.properties;

import com.github.aureliano.achmed.helper.BooleanHelper;
import com.github.aureliano.achmed.helper.IntegerHelper;
import com.github.aureliano.achmed.helper.LongHelper;
import com.github.aureliano.achmed.helper.StringHelper;

public class ExecProperties extends ResourceProperties {

	private String command;
	private String cwd;
	private Boolean verbose;
	private String onlyIf;
	private String unless;
	private Long timeout;
	private Integer tries;
	
	public ExecProperties() {
		super();
	}
	
	public ExecProperties configureAttributes() {
		for (String key : super.properties.keySet()) {
			this.setAttribute(key, super.properties.get(key));
		}
		
		return this;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getCwd() {
		return cwd;
	}

	public void setCwd(String cwd) {
		this.cwd = cwd;
	}

	public Boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(Boolean verbose) {
		this.verbose = verbose;
	}

	public String getOnlyIf() {
		return onlyIf;
	}

	public void setOnlyIf(String onlyIf) {
		this.onlyIf = onlyIf;
	}

	public String getUnless() {
		return unless;
	}

	public void setUnless(String unless) {
		this.unless = unless;
	}

	public Long getTimeout() {
		return timeout;
	}

	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}

	public Integer getTries() {
		return tries;
	}

	public void setTries(Integer tries) {
		this.tries = tries;
	}
	
	private void setAttribute(String name, Object value) {
		if ("command".equalsIgnoreCase(name)) {
			this.command = StringHelper.parse(value);
		} else if ("cwd".equalsIgnoreCase(name)) {
			this.cwd = StringHelper.parse(value);
		} else if ("verbose".equalsIgnoreCase(name)) {
			this.verbose = BooleanHelper.parse(value);
		} else if ("onlyIf".equalsIgnoreCase(name)) {
			this.onlyIf = StringHelper.parse(value);
		} else if ("unless".equalsIgnoreCase(name)) {
			this.unless = StringHelper.parse(value);
		} else if ("timeout".equalsIgnoreCase(name)) {
			this.timeout = LongHelper.parse(value);
		} else if ("tries".equalsIgnoreCase(name)) {
			this.tries = IntegerHelper.parse(value);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		result = prime * result + ((cwd == null) ? 0 : cwd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExecProperties other = (ExecProperties) obj;
		if (command == null) {
			if (other.command != null)
				return false;
		} else if (!command.equals(other.command))
			return false;
		if (cwd == null) {
			if (other.cwd != null)
				return false;
		} else if (!cwd.equals(other.cwd))
			return false;
		return true;
	}
}
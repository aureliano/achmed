package com.github.aureliano.achmed.client.resources.properties;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.aureliano.achmed.client.annotation.Min;
import com.github.aureliano.achmed.client.annotation.NotEmpty;
import com.github.aureliano.achmed.client.command.CommandFacade;
import com.github.aureliano.achmed.common.helper.BooleanHelper;
import com.github.aureliano.achmed.common.helper.IntegerHelper;
import com.github.aureliano.achmed.common.helper.LongHelper;
import com.github.aureliano.achmed.common.helper.StringHelper;

public class ExecProperties extends ResourceProperties {

	private String command;
	private List<String> arguments;
	private String cwd;
	private Boolean verbose;
	private String onlyIf;
	private String unless;
	private Long timeout;
	private Integer tries;
	private Boolean amendargs;
	
	public ExecProperties() {
		this.cwd = new File("").getAbsolutePath();
		this.arguments = new ArrayList<>();
		this.verbose = true;
		this.timeout = CommandFacade.DEFAULT_TIMEOUT_EXECUTION;
		this.tries = 1;
		this.amendargs = false;
	}
	
	public ExecProperties configureAttributes() {
		for (String key : super.properties.keySet()) {
			this.setAttribute(key, super.properties.get(key));
		}
		
		return this;
	}

	@NotEmpty
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
	public List<String> getArguments() {
		return arguments;
	}
	
	public void setArguments(List<String> arguments) {
		this.arguments = arguments;
	}

	public String getCwd() {
		return cwd;
	}

	public void setCwd(String cwd) {
		this.cwd = cwd;
	}

	public Boolean getVerbose() {
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

	@Min(1)
	public Integer getTries() {
		return tries;
	}

	public void setTries(Integer tries) {
		this.tries = tries;
	}
	
	public Boolean getAmendargs() {
		return amendargs;
	}
	
	public void setAmendargs(Boolean amendargs) {
		this.amendargs = amendargs;
	}
	
	private void setAttribute(String name, Object value) {
		if ("command".equalsIgnoreCase(name)) {
			this.command = StringHelper.parse(value);
		} else if ("arguments".equalsIgnoreCase(name)) {
			if (value != null) {
				try {
					this.arguments = new ArrayList<>((Collection) value);
				} catch (Exception ex) {
					this.arguments.add(StringHelper.parse(value));
				}
			}
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
		} else if ("amendargs".equalsIgnoreCase(name)) {
			this.amendargs = BooleanHelper.parse(value);
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
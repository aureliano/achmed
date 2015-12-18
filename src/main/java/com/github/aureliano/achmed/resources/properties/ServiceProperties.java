package com.github.aureliano.achmed.resources.properties;

import com.github.aureliano.achmed.annotation.NotEmpty;
import com.github.aureliano.achmed.annotation.NotNull;
import com.github.aureliano.achmed.helper.BooleanHelper;
import com.github.aureliano.achmed.helper.StringHelper;

public class ServiceProperties extends ResourceProperties {

	private String name;
	private Boolean ensure;
	private String binary;
	private Boolean enable;
	private String flags;
	private Boolean hasRestart;
	private Boolean hasStatus;
	private String pattern;
	private String restart;
	private String start;
	private String status;
	private String stop;
	
	public ServiceProperties() {
		this.hasRestart = false;
		this.hasStatus = true;
	}
	
	public ServiceProperties configureAttributes() {
		for (String key : super.properties.keySet()) {
			this.setAttribute(key, super.properties.get(key));
		}
		
		return this;
	}

	@NotEmpty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	public Boolean getEnsure() {
		return ensure;
	}

	public void setEnsure(Boolean ensure) {
		this.ensure = ensure;
	}

	public String getBinary() {
		return binary;
	}

	public void setBinary(String binary) {
		this.binary = binary;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public Boolean getHasRestart() {
		return hasRestart;
	}

	public void setHasRestart(Boolean hasRestart) {
		this.hasRestart = hasRestart;
	}

	public Boolean getHasStatus() {
		return hasStatus;
	}

	public void setHasStatus(Boolean hasStatus) {
		this.hasStatus = hasStatus;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getRestart() {
		return restart;
	}

	public void setRestart(String restart) {
		this.restart = restart;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}
	
	private void setAttribute(String name, Object value) {
		if ("name".equalsIgnoreCase(name)) {
			this.name = StringHelper.parse(value);
		} else if ("ensure".equalsIgnoreCase(name)) {
			this.ensure = BooleanHelper.parse(value);
		} else if ("binary".equalsIgnoreCase(name)) {
			this.binary = StringHelper.parse(value);
		} else if ("enable".equalsIgnoreCase(name)) {
			this.enable = BooleanHelper.parse(value);
		} else if ("flags".equalsIgnoreCase(name)) {
			this.flags = StringHelper.parse(value);
		} else if ("hasRestart".equalsIgnoreCase(name)) {
			this.hasRestart = BooleanHelper.parse(value);
		} else if ("hasStatus".equalsIgnoreCase(name)) {
			this.hasStatus = BooleanHelper.parse(value);
		} else if ("pattern".equalsIgnoreCase(name)) {
			this.pattern = StringHelper.parse(value);
		} else if ("restart".equalsIgnoreCase(name)) {
			this.restart = StringHelper.parse(value);
		} else if ("start".equalsIgnoreCase(name)) {
			this.start = StringHelper.parse(value);
		} else if ("status".equalsIgnoreCase(name)) {
			this.status = StringHelper.parse(value);
		} else if ("stop".equalsIgnoreCase(name)) {
			this.stop = StringHelper.parse(value);
		}
		
		if (StringHelper.isEmpty(this.pattern)) {
			this.pattern = this.name;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((binary == null) ? 0 : binary.hashCode());
		result = prime * result + ((ensure == null) ? 0 : ensure.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ServiceProperties other = (ServiceProperties) obj;
		if (binary == null) {
			if (other.binary != null)
				return false;
		} else if (!binary.equals(other.binary))
			return false;
		if (ensure == null) {
			if (other.ensure != null)
				return false;
		} else if (!ensure.equals(other.ensure))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
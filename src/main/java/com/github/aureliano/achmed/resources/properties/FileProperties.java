package com.github.aureliano.achmed.resources.properties;

import com.github.aureliano.achmed.annotation.NotEmpty;
import com.github.aureliano.achmed.annotation.NotNull;
import com.github.aureliano.achmed.helper.BooleanHelper;
import com.github.aureliano.achmed.helper.StringHelper;
import com.github.aureliano.achmed.types.EnsureFileStatus;

public class FileProperties extends ResourceProperties {

	private String path;
	private EnsureFileStatus ensure;
	private String backup;
	private String content;
	private Boolean force;
	private String group;
	private String mode;
	private String owner;
	private Boolean recurse;
	private Boolean replace;
	private String source;
	private String target;
	
	public FileProperties() {
		this.force = false;
		this.replace = false;
		this.recurse = false;
	}
	
	public FileProperties configureAttributes() {
		for (String key : super.properties.keySet()) {
			this.setAttribute(key, super.properties.get(key));
		}
		
		return this;
	}

	@NotEmpty
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@NotNull(message = "Expected to find a not null value for field ensure. Accept only [ PRESENT, ABSENT, FILE, DIRECTORY, LINK ]")
	public EnsureFileStatus getEnsure() {
		return ensure;
	}

	public void setEnsure(EnsureFileStatus ensure) {
		this.ensure = ensure;
	}

	public String getBackup() {
		return backup;
	}

	public void setBackup(String backup) {
		this.backup = backup;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getForce() {
		return force;
	}

	public void setForce(Boolean force) {
		this.force = force;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public Boolean getRecurse() {
		return recurse;
	}
	
	public void setRecurse(Boolean recurse) {
		this.recurse = recurse;
	}

	public Boolean getReplace() {
		return replace;
	}

	public void setReplace(Boolean replace) {
		this.replace = replace;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	private void setAttribute(String name, Object value) {
		if ("path".equalsIgnoreCase(name)) {
			this.path = StringHelper.parse(value);
		} else if ("ensure".equalsIgnoreCase(name)) {
			if (value == null) {
				this.ensure = null;
			} else if ((value != null) && (value instanceof EnsureFileStatus)) {
				this.ensure = (EnsureFileStatus) value;
			} else {
				String status = StringHelper.parse(value).toUpperCase();
				this.ensure = EnsureFileStatus.valueOf(status);
			};
		} else if ("backup".equalsIgnoreCase(name)) {
			this.backup = StringHelper.parse(value);
		} else if ("content".equalsIgnoreCase(name)) {
			this.content = StringHelper.parse(value);
		} else if ("force".equalsIgnoreCase(name)) {
			this.force = BooleanHelper.parse(value);
		} else if ("group".equalsIgnoreCase(name)) {
			this.group = StringHelper.parse(value);
		} else if ("mode".equalsIgnoreCase(name)) {
			this.mode = StringHelper.parse(value);
		} else if ("owner".equalsIgnoreCase(name)) {
			this.owner = StringHelper.parse(value);
		} else if ("recurse".equalsIgnoreCase(name)) {
			this.recurse = BooleanHelper.parse(value);
		} else if ("replace".equalsIgnoreCase(name)) {
			this.replace = BooleanHelper.parse(value);
		} else if ("source".equalsIgnoreCase(name)) {
			this.source = StringHelper.parse(value);
		} else if ("target".equalsIgnoreCase(name)) {
			this.target = StringHelper.parse(value);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ensure == null) ? 0 : ensure.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
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
		FileProperties other = (FileProperties) obj;
		if (ensure != other.ensure)
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}
}
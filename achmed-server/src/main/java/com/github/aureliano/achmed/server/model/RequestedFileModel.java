package com.github.aureliano.achmed.server.model;

public class RequestedFileModel {

	private String id;
	private Long time;
	private String filePath;
	
	public RequestedFileModel() {}

	public String getId() {
		return id;
	}

	public RequestedFileModel withId(String id) {
		this.id = id;
		return this;
	}

	public Long getTime() {
		return time;
	}

	public RequestedFileModel withTime(Long time) {
		this.time = time;
		return this;
	}

	public String getFilePath() {
		return filePath;
	}

	public RequestedFileModel withFilePath(String filePath) {
		this.filePath = filePath;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		RequestedFileModel other = (RequestedFileModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
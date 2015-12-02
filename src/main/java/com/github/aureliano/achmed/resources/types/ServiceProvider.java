package com.github.aureliano.achmed.resources.types;

public enum ServiceProvider {

	SERVICE(OperatingSystemFamily.UBUNTU, "service");

	private OperatingSystemFamily operatingSystemFamily;
	private String serviceName;
	
	private ServiceProvider(OperatingSystemFamily family, String app) {
		this.operatingSystemFamily = family;
		this.serviceName = app;
	}
	
	public OperatingSystemFamily getOperatingSystemFamily() {
		return this.operatingSystemFamily;
	}
	
	public String getServiceName() {
		return this.serviceName;
	}
}
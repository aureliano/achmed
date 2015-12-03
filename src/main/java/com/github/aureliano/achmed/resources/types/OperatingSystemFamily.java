package com.github.aureliano.achmed.resources.types;

public enum OperatingSystemFamily {

	DEBIAN(OS.LINUX, null),
	UBUNTU(OS.LINUX, DEBIAN),
	RPM(OS.LINUX, null),
	APP(null, null);
	
	private OS operatingSystem;
	private OperatingSystemFamily ancestor;
	
	private OperatingSystemFamily(OS operatingSystem, OperatingSystemFamily ancestor) {
		this.operatingSystem = operatingSystem;
		this.ancestor = ancestor;
	}
	
	public OS getOperatingSystem() {
		return operatingSystem;
	}
	
	public OperatingSystemFamily getAncestor() {
		return ancestor;
	}
}
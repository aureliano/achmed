package com.github.aureliano.achmed.resources.types;

public enum OperatingSystemFamily {

	DEBIAN(null),
	UBUNTU(DEBIAN),
	RPM(null),
	APP(null);
	
	private OperatingSystemFamily ancestor;
	
	private OperatingSystemFamily(OperatingSystemFamily ancestor) {
		this.ancestor = ancestor;
	}
	
	public OperatingSystemFamily getAncestor() {
		return ancestor;
	}
}
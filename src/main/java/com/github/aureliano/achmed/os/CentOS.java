package com.github.aureliano.achmed.os;

import com.github.aureliano.achmed.resources.types.OS;
import com.github.aureliano.achmed.resources.types.OperatingSystemFamily;

public class CentOS implements IOperatingSystem {

	public CentOS() {
		super();
	}

	public OS getOperatingSystem() {
		return OS.LINUX;
	}

	public OperatingSystemFamily getOperatingSystemFamily() {
		return OperatingSystemFamily.RPM;
	}
}
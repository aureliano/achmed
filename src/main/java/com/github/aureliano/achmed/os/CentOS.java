package com.github.aureliano.achmed.os;

import com.github.aureliano.achmed.types.OperatingSystemFamily;

public class CentOS extends RedHat {
	
	public CentOS() {
		super();
	}

	public OperatingSystemFamily getOperatingSystemFamily() {
		return OperatingSystemFamily.RED_HAT;
	}
}
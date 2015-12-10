package com.github.aureliano.achmed.os;

import com.github.aureliano.achmed.types.OperatingSystemFamily;

public class Ubuntu extends Debian {
	
	public Ubuntu() {
		super();
	}

	public OperatingSystemFamily getOperatingSystemFamily() {
		return OperatingSystemFamily.UBUNTU;
	}
}
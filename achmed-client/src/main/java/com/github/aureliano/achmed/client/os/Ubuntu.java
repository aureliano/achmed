package com.github.aureliano.achmed.client.os;

import com.github.aureliano.achmed.client.types.OperatingSystemFamily;

public class Ubuntu extends Debian {
	
	public Ubuntu() {
		super();
	}

	public OperatingSystemFamily getOperatingSystemFamily() {
		return OperatingSystemFamily.UBUNTU;
	}
}
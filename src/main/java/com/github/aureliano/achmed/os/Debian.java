package com.github.aureliano.achmed.os;

import com.github.aureliano.achmed.resources.types.OS;
import com.github.aureliano.achmed.resources.types.OperatingSystemFamily;

public class Debian implements IOperatingSystem {

	public Debian() {
		super();
	}

	public OS getOperatingSystem() {
		return OS.LINUX;
	}

	public OperatingSystemFamily getOperatingSystemFamily() {
		return OperatingSystemFamily.DEBIAN;
	}
}
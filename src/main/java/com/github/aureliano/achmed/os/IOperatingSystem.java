package com.github.aureliano.achmed.os;

import com.github.aureliano.achmed.resources.types.OS;
import com.github.aureliano.achmed.resources.types.OperatingSystemFamily;

public interface IOperatingSystem {

	public abstract OS getOperatingSystem();
	
	public abstract OperatingSystemFamily getOperatingSystemFamily();
}
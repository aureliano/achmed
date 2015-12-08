package com.github.aureliano.achmed.os;

import com.github.aureliano.achmed.types.OS;

public abstract class Linux implements IOperatingSystem {

	public OS getOperatingSystem() {
		return OS.LINUX;
	}

	public String getPsCommand() {
		return "ps -ef";
	}
}
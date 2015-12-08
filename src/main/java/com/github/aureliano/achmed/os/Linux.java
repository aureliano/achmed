package com.github.aureliano.achmed.os;

import com.github.aureliano.achmed.exception.ServiceResourceException;
import com.github.aureliano.achmed.helper.StringHelper;
import com.github.aureliano.achmed.types.OS;

public abstract class Linux implements IOperatingSystem {

	public OS getOperatingSystem() {
		return OS.LINUX;
	}

	public String getPsCommand() {
		return "ps -ef";
	}
	
	public Integer getPid(String pattern) {
		if (StringHelper.isEmpty(pattern)) {
			throw new ServiceResourceException("Cannot get process id. No pattern provided.");
		}
		
		throw new UnsupportedOperationException("Not implemented yet.");
	}
}
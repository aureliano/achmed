package com.github.aureliano.achmed.client.os;

import com.github.aureliano.achmed.client.command.CommandFacade;
import com.github.aureliano.achmed.client.exception.UnsupportedOperatingSystemException;
import com.github.aureliano.achmed.common.helper.StringHelper;

public final class OperatingSystemFactory {

	private OperatingSystemFactory() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
	
	public static IOperatingSystem currentOperatingSystem() {
		String os = System.getProperty("os.name");
		
		if ("Linux".equalsIgnoreCase(os)) {
			return resolveLinux();
		} else {
			throw new UnsupportedOperatingSystemException("There is no support to the Operating System '" + os + "'.");
		}
	}
	
	protected static IOperatingSystem resolveLinux() {
		String linux = CommandFacade.executeCommand("cat /etc/issue").getOutput();
		linux = StringHelper.match("^\\w+", linux).get(0);
		
		return LinuxResolver.resolveLinux(linux);
	}
}
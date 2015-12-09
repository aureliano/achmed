package com.github.aureliano.achmed.os;

import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;
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
		
		CommandResponse res = CommandFacade.executeCommand(this.getPsCommand());
		if (!res.isOK()) {
			throw new ServiceResourceException(res.getError());
		}
		
		return this.matchPid(res.getOutput(), pattern);
	}
	
	public CommandResponse kill(Integer pid) {
		if (pid == null) {
			throw new ServiceResourceException("Invalid pid: " + pid);
		}
		
		return CommandFacade.executeCommand("kill " + pid);
	}
	
	Integer matchPid(String processTable, String pattern) {
		String[] lines = processTable.split("\n");
		for (String line : lines) {
			if (!line.matches(pattern)) {
				continue;
			}
			
			String pid = line.trim().split("\\s+")[1];
			return Integer.parseInt(pid);
		}
		
		return null;
	}
}
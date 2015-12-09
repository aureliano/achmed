package com.github.aureliano.achmed.os;

import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.os.pkg.IPackageManager;
import com.github.aureliano.achmed.types.OS;
import com.github.aureliano.achmed.types.OperatingSystemFamily;

public interface IOperatingSystem {

	public abstract IPackageManager getDefaultPackageManager();
	
	public abstract OS getOperatingSystem();
	
	public abstract OperatingSystemFamily getOperatingSystemFamily();
	
	public abstract String getPsCommand();
	
	public abstract Integer getPid(String pattern);
	
	public abstract CommandResponse kill(Integer pid);
}
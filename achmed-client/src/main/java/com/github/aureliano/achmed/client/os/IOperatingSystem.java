package com.github.aureliano.achmed.client.os;

import com.github.aureliano.achmed.client.command.CommandResponse;
import com.github.aureliano.achmed.client.types.OS;
import com.github.aureliano.achmed.client.types.OperatingSystemFamily;
import com.github.aureliano.achmed.os.fs.IFileProvider;
import com.github.aureliano.achmed.os.pkg.IPackageManager;
import com.github.aureliano.achmed.os.service.IService;

public interface IOperatingSystem {
	
	public abstract Linux prototype();

	public abstract IPackageManager getDefaultPackageManager();
	
	public abstract IService getDefaultServiceManager();
	
	public abstract IFileProvider getDefaultFileProvider();
	
	public abstract OS getOperatingSystem();
	
	public abstract OperatingSystemFamily getOperatingSystemFamily();
	
	public abstract String getPsCommand();
	
	public abstract Integer getPid(String pattern);
	
	public abstract CommandResponse kill(Integer pid);
}
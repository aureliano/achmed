package com.github.aureliano.achmed.os.pkg;

import java.io.File;

import com.github.aureliano.achmed.command.CommandBuilder;
import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.exception.PackageResourceException;
import com.github.aureliano.achmed.helper.StringHelper;
import com.github.aureliano.achmed.idiom.LanguageSingleton;
import com.github.aureliano.achmed.resources.properties.PackageProperties;

public class AptPackageManager implements IPackageManager {

	private static final String APT_GET = "/usr/bin/apt-get";
	private static final String APT_CACHE = "/usr/bin/apt-cache";
	
	private PackageProperties properties;
	
	public AptPackageManager() {
		super();
	}

	public CommandResponse install() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public CommandResponse uninstall() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public String latest() {
		String cmd = String.format("%s policy %s", APT_CACHE, this.properties.getName());
		CommandResponse res = CommandFacade.executeCommand(this.buildCommand(cmd));
		
		if (!res.isOK()) {
			throw new PackageResourceException(res.getError());
		}
		
		String candidate = LanguageSingleton.instance().getValue("resource.package.apt.cache.latest_candidate_version");
		String version = StringHelper.match(candidate + ":\\s+(\\S+)\\s", res.getOutput())[1];
		
		return version;
	}

	public void setPackageProperties(PackageProperties properties) {
		this.properties = properties;
	}

	public PackageProperties getPackageProperties() {
		return this.properties;
	}
	
	private CommandBuilder buildCommand(String cmd) {
		return new CommandBuilder()
			.withCommand(cmd)
			.withVerbose(false)
			.withWorkingDir(new File("").getAbsolutePath())
			.withTries(1)
			.withTimeout(10000L);
	}
}
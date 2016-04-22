package com.github.aureliano.achmed.client.os.pkg;

import java.util.List;

import com.github.aureliano.achmed.client.command.CommandFacade;
import com.github.aureliano.achmed.client.command.CommandResponse;
import com.github.aureliano.achmed.client.exception.PackageResourceException;
import com.github.aureliano.achmed.client.resources.properties.PackageProperties;
import com.github.aureliano.achmed.common.helper.StringHelper;

public class RpmPackageManager implements IPackageManager {

	private static final String RPM = "/bin/rpm";
	private static final String QUERY_FORMAT = "'%{NAME} %|EPOCH?{%{EPOCH}}:{0}| %{VERSION} %{RELEASE} %{ARCH}\\n'";
	
	protected PackageProperties properties;
	
	public RpmPackageManager() {
		super();
	}

	public CommandResponse install() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public CommandResponse uninstall() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public String latest() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public boolean isInstalled() {
		CommandResponse res = CommandFacade.executeCommand(
			RPM, "-qa", "--qf", QUERY_FORMAT, this.properties.getName()
		);
		
		if (!res.isOK()) {
			throw new PackageResourceException(res);
		}
		
		if (StringHelper.isEmpty(res.getOutput())) {
			return false;
		}
		
		if (res.getOutput().split("\n").length > 1) {
			throw new PackageResourceException("Found more than one package installed to " + res.getCommand());
		}
		
		List<String> match = StringHelper.match("^(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)$", res.getOutput());
		if (match.isEmpty()) {
			throw new PackageResourceException("Failed to match rpm query " + res.getCommand());
		}
		
		return true;
	}

	public void setPackageProperties(PackageProperties properties) {
		this.properties = properties;
	}

	public PackageProperties getPackageProperties() {
		return this.properties;
	}
}
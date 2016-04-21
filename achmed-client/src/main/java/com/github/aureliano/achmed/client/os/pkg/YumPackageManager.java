package com.github.aureliano.achmed.client.os.pkg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.github.aureliano.achmed.client.command.CommandFacade;
import com.github.aureliano.achmed.client.command.CommandResponse;
import com.github.aureliano.achmed.client.exception.PackageResourceException;
import com.github.aureliano.achmed.client.helper.PkgManagerHelper;
import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.common.logging.LoggingFactory;

public class YumPackageManager extends RpmPackageManager {

	private static final Logger logger = LoggingFactory.createLogger(YumPackageManager.class);
	private static final String YUM = "yum";
	private static final String CHECK_UPDATE = YUM + " check-update";
	
	public YumPackageManager() {
		super();
	}

	public CommandResponse install() {
		String cmd = this.buildInstallCommand();
		CommandResponse res = CommandFacade.executeCommand(PkgManagerHelper.buildCommand(cmd));
		
		if (!res.isOK()) {
			throw new PackageResourceException(res);
		}
		
		if (!super.isInstalled()) {
			throw new PackageResourceException("Expected package " + super.properties.getName() + " was installed, but it doesn't.");
		}
		
		return res;
	}

	public CommandResponse uninstall() {
		String cmd = this.buildUninstallCommand();
		CommandResponse res = CommandFacade.executeCommand(PkgManagerHelper.buildCommand(cmd));
		
		if (!res.isOK()) {
			throw new PackageResourceException(res);
		}
		
		if (super.isInstalled()) {
			throw new PackageResourceException("Expected package " + super.properties.getName() + " was not installed, but it is.");
		}
		
		return res;
	}

	public String latest() {
		List<String> options = PkgManagerHelper.scanRepositoryOptions(super.properties.getInstallOptions());
		List<Map<String, String>> updates = PkgManagerHelper.yumCheckUpdates(super.properties.getName(), options);
		
		if (updates.isEmpty()) {
			logger.info(CHECK_UPDATE + " exited with 0; no package updates available.");
		}
		
		if (updates.size() > 1) {
			logger.warning(CHECK_UPDATE + " got more than one update to package " +
					super.properties.getName() + ". Picking the first one.");
		}
		
		Map<String, String> pkg = updates.get(0);
		return String.format("%s:%s-%s", pkg.get("epoch"), pkg.get("version"), pkg.get("release"));
	}
	
	private String buildInstallCommand() {
		List<String> cmd = new ArrayList<>();
		cmd.add(YUM);
		cmd.add("-q -y");
		
		String version = null;
		if (!(("present".equalsIgnoreCase(super.properties.getEnsure())) ||
				("installed".equalsIgnoreCase(super.properties.getEnsure()))) ||
				("absent".equalsIgnoreCase(super.properties.getEnsure()))) {
			version = super.properties.getEnsure();
		}
		
		String pkg = super.properties.getName();
		if (!StringHelper.isEmpty(version)) {
			version = (version.equalsIgnoreCase("latest")) ? this.latest() : version;
			pkg = String.format("%s=%s", pkg, version);
		}
		
		if (!super.properties.getInstallOptions().isEmpty()) {
			cmd.add(StringHelper.join(super.properties.getInstallOptions(), " "));
		}
		
		cmd.add("install");
		cmd.add(pkg);
		
		return StringHelper.join(cmd, " ");
	}
	
	private String buildUninstallCommand() {
		List<String> cmd = new ArrayList<String>();
		
		cmd.add(YUM);
		cmd.add("-y");
		cmd.add("-q");
		
		if (!super.properties.getUninstallOptions().isEmpty()) {
			cmd.add(StringHelper.join(super.properties.getUninstallOptions(), " "));
		}
		
		cmd.add("erase");
		cmd.add(super.properties.getName());
		
		return StringHelper.join(cmd, " ");
	}
}
package com.github.aureliano.achmed.os.pkg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.exception.PackageResourceException;
import com.github.aureliano.achmed.helper.PkgManagerHelper;
import com.github.aureliano.achmed.helper.StringHelper;
import com.github.aureliano.achmed.resources.properties.PackageProperties;

public class YumPackageManager extends RpmPackageManager {

	private static final Logger logger = Logger.getLogger(YumPackageManager.class);
	private static final String YUM = "yum";
	private static final String CHECK_UPDATE = YUM + " check-update";
	
	private PackageProperties properties;
	
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
			throw new PackageResourceException("Expected package " + this.properties.getName() + " was installed, but it doesn't.");
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
			throw new PackageResourceException("Expected package " + this.properties.getName() + " was not installed, but it is.");
		}
		
		return res;
	}

	public String latest() {
		List<String> options = PkgManagerHelper.scanRepositoryOptions(this.properties.getInstallOptions());
		List<Map<String, String>> updates = PkgManagerHelper.yumCheckUpdates(this.properties.getName(), options);
		
		if (updates.isEmpty()) {
			logger.info(CHECK_UPDATE + " exited with 0; no package updates available.");
		}
		
		if (updates.size() > 1) {
			logger.warn(CHECK_UPDATE + " got more than one update to package " +
					this.properties.getName() + ". Picking the first one.");
		}
		
		Map<String, String> pkg = updates.get(0);
		return String.format("%s:%s-%s", pkg.get("epoch"), pkg.get("version"), pkg.get("release"));
	}

	public void setPackageProperties(PackageProperties properties) {
		this.properties = properties;
	}

	public PackageProperties getPackageProperties() {
		return this.properties;
	}
	
	private String buildInstallCommand() {
		List<String> cmd = new ArrayList<>();
		cmd.add(YUM);
		cmd.add("-q -y");
		
		String version = null;
		if (!(("present".equalsIgnoreCase(this.properties.getEnsure())) ||
				("installed".equalsIgnoreCase(this.properties.getEnsure()))) ||
				("absent".equalsIgnoreCase(this.properties.getEnsure()))) {
			version = this.properties.getEnsure();
		}
		
		String pkg = this.properties.getName();
		if (!StringHelper.isEmpty(version)) {
			version = (version.equalsIgnoreCase("latest")) ? this.latest() : version;
			pkg = String.format("%s=%s", pkg, version);
		}
		
		if (!this.properties.getInstallOptions().isEmpty()) {
			cmd.add(StringHelper.join(this.properties.getInstallOptions(), " "));
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
		
		if (!this.properties.getUninstallOptions().isEmpty()) {
			cmd.add(StringHelper.join(this.properties.getUninstallOptions(), " "));
		}
		
		cmd.add("erase");
		cmd.add(this.properties.getName());
		
		return StringHelper.join(cmd, " ");
	}
}
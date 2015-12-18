package com.github.aureliano.achmed.os.pkg;

import java.util.ArrayList;
import java.util.List;

import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.exception.PackageResourceException;
import com.github.aureliano.achmed.helper.PkgManagerHelper;
import com.github.aureliano.achmed.helper.StringHelper;
import com.github.aureliano.achmed.idiom.LanguageSingleton;
import com.github.aureliano.achmed.types.DebianConfigFilesStatus;

public class AptPackageManager extends DpkgPackageManager {

	private static final String APT_GET = "/usr/bin/apt-get";
	private static final String APT_CACHE = "/usr/bin/apt-cache";
	
	public AptPackageManager() {
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
		String cmd = String.format("%s policy %s", APT_CACHE, super.properties.getName());
		CommandResponse res = CommandFacade.executeCommand(PkgManagerHelper.buildCommand(cmd));
		
		if (!res.isOK()) {
			throw new PackageResourceException(res);
		}
		
		String candidate = LanguageSingleton.instance().getValue("resource.package.apt.cache.latest_candidate_version");
		String version = StringHelper.match(candidate + ":\\s+(\\S+)\\s", res.getOutput()).get(1);
		
		return version;
	}
	
	private String buildInstallCommand() {
		List<String> cmd = new ArrayList<String>();
		cmd.add(APT_GET);
		cmd.add("-q -y");
		
		if (super.properties.getConfigFiles() != null) {
			if (DebianConfigFilesStatus.KEEP.equals(super.properties.getConfigFiles())) {
				cmd.add("-o");
				cmd.add("DPkg::Options::=--force-confold");
			} else if (DebianConfigFilesStatus.REPLACE.equals(super.properties.getConfigFiles())) {
				cmd.add("-o");
				cmd.add("DPkg::Options::=--force-confnew");
			}
		}
		
		String version = null;
		if (!(("present".equalsIgnoreCase(super.properties.getEnsure())) ||
				("installed".equalsIgnoreCase(super.properties.getEnsure()))) ||
				("absent".equalsIgnoreCase(super.properties.getEnsure()))) {
			version = super.properties.getEnsure();
		}
		
		String pkg = super.properties.getName();
		if (!StringHelper.isEmpty(version)) {
			version = (version.equalsIgnoreCase("latest")) ? this.latest() : version;
			pkg = String.format("%s=%s --force-yes", pkg, version);
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
		
		cmd.add(APT_GET);
		cmd.add("-y");
		cmd.add("-q");
		
		if (!super.properties.getUninstallOptions().isEmpty()) {
			cmd.add(StringHelper.join(super.properties.getUninstallOptions(), " "));
		}
		
		cmd.add("remove");
		cmd.add(super.properties.getName());
		
		return StringHelper.join(cmd, " ");
	}
}
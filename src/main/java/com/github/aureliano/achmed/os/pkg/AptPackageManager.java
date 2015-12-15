package com.github.aureliano.achmed.os.pkg;

import java.util.ArrayList;
import java.util.List;

import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.exception.PackageResourceException;
import com.github.aureliano.achmed.helper.PkgManagerHelper;
import com.github.aureliano.achmed.helper.StringHelper;
import com.github.aureliano.achmed.idiom.LanguageSingleton;
import com.github.aureliano.achmed.resources.properties.PackageProperties;
import com.github.aureliano.achmed.types.DebianConfigFilesStatus;

public class AptPackageManager extends DpkgPackageManager {

	private static final String APT_GET = "/usr/bin/apt-get";
	private static final String APT_CACHE = "/usr/bin/apt-cache";
	
	private PackageProperties properties;
	
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
		String cmd = String.format("%s policy %s", APT_CACHE, this.properties.getName());
		CommandResponse res = CommandFacade.executeCommand(PkgManagerHelper.buildCommand(cmd));
		
		if (!res.isOK()) {
			throw new PackageResourceException(res);
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
	
	private String buildInstallCommand() {
		List<String> cmd = new ArrayList<String>();
		cmd.add(APT_GET);
		cmd.add("-q -y");
		
		if (this.properties.getConfigFiles() != null) {
			if (DebianConfigFilesStatus.KEEP.equals(this.properties.getConfigFiles())) {
				cmd.add("-o");
				cmd.add("DPkg::Options::=--force-confold");
			} else if (DebianConfigFilesStatus.REPLACE.equals(this.properties.getConfigFiles())) {
				cmd.add("-o");
				cmd.add("DPkg::Options::=--force-confnew");
			}
		}
		
		String version = null;
		if (!(("present".equalsIgnoreCase(this.properties.getEnsure())) ||
				("installed".equalsIgnoreCase(this.properties.getEnsure()))) ||
				("absent".equalsIgnoreCase(this.properties.getEnsure()))) {
			version = this.properties.getEnsure();
		}
		
		String pkg = this.properties.getName();
		if (!StringHelper.isEmpty(version)) {
			version = (version.equalsIgnoreCase("latest")) ? this.latest() : version;
			pkg = String.format("%s=%s --force-yes", pkg, version);
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
		
		cmd.add(APT_GET);
		cmd.add("-y");
		cmd.add("-q");
		
		if (!this.properties.getUninstallOptions().isEmpty()) {
			cmd.add(StringHelper.join(this.properties.getUninstallOptions(), " "));
		}
		
		cmd.add("remove");
		cmd.add(this.properties.getName());
		
		return StringHelper.join(cmd, " ");
	}
}
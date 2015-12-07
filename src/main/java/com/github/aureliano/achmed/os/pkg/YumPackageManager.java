package com.github.aureliano.achmed.os.pkg;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.helper.PkgManagerHelper;
import com.github.aureliano.achmed.resources.properties.PackageProperties;

public class YumPackageManager implements IPackageManager {

	private static final Logger logger = Logger.getLogger(YumPackageManager.class);
	private static final String CHECK_UPDATE = "yum check-update";
	
	private PackageProperties properties;
	
	public YumPackageManager() {
		super();
	}

	public CommandResponse install() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public CommandResponse uninstall() {
		throw new UnsupportedOperationException("Method not implemented yet.");
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
}
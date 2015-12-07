package com.github.aureliano.achmed.helper;

import java.util.ArrayList;
import java.util.List;

public final class PkgManagerHelper {

	private PkgManagerHelper() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
	
	public static List<String> scanRepositoryOptions(List<String> options) {
		List<String> scannedOptions = new ArrayList<>();
		
		if (options.isEmpty()) {
			return scannedOptions;
		}
		
		String[] repoParams = { "enablerepo", "disablerepo", "disableexcludes" };
		String regex = "^\\s*--${repoParam}\\s*=?\\s*";
		
		for (String opt : options) {
			for (String repoParam : repoParams) {
				String _regex = regex.replaceFirst("\\$\\{repoParam\\}", repoParam);
				if (StringHelper.match(_regex, opt) != null) {
					scannedOptions.add(opt);
					break;
				}
			}
		}
		
		return scannedOptions;
	}
}
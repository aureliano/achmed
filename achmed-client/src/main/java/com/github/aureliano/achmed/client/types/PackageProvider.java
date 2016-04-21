package com.github.aureliano.achmed.client.types;

public enum PackageProvider {

	APT(OperatingSystemFamily.DEBIAN, "apt-get"),
	YUM(OperatingSystemFamily.RED_HAT, "yum"),
	DPKG(OperatingSystemFamily.DEBIAN, "dpkg"),
	GEM(OperatingSystemFamily.APP, "gem");
	
	private OperatingSystemFamily operatingSystemFamily;
	private String packageManagement;
	
	private PackageProvider(OperatingSystemFamily family, String app) {
		this.operatingSystemFamily = family;
		this.packageManagement = app;
	}
	
	public OperatingSystemFamily getOperatingSystemFamily() {
		return this.operatingSystemFamily;
	}
	
	public String getPackageManagement() {
		return this.packageManagement;
	}
}
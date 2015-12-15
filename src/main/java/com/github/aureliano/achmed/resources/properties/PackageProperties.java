package com.github.aureliano.achmed.resources.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.aureliano.achmed.helper.StringHelper;
import com.github.aureliano.achmed.types.DebianConfigFilesStatus;
import com.github.aureliano.achmed.types.PackageProvider;

public class PackageProperties extends ResourceProperties {

	private String name;
	private PackageProvider provider;
	private DebianConfigFilesStatus configFiles;
	private String ensure;
	private List<String> installOptions;
	private String source;
	private List<String> uninstallOptions;
	
	public PackageProperties() {
		this.ensure = "installed";
		this.configFiles = DebianConfigFilesStatus.KEEP;
		this.installOptions = new ArrayList<>();
		this.uninstallOptions = new ArrayList<>();
	}
	
	public PackageProperties configureAttributes() {
		for (String key : super.properties.keySet()) {
			this.setAttribute(key, super.properties.get(key));
		}
		
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PackageProvider getProvider() {
		return provider;
	}

	public void setProvider(PackageProvider provider) {
		this.provider = provider;
	}
	
	public DebianConfigFilesStatus getConfigFiles() {
		return configFiles;
	}
	
	public void setConfigFiles(DebianConfigFilesStatus configFiles) {
		this.configFiles = configFiles;
	}

	public String getEnsure() {
		return ensure;
	}

	public void setEnsure(String ensure) {
		this.ensure = ensure;
	}

	public List<String> getInstallOptions() {
		return installOptions;
	}

	public void setInstallOptions(List<String> installOptions) {
		this.installOptions = installOptions;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<String> getUninstallOptions() {
		return uninstallOptions;
	}

	public void setUninstallOptions(List<String> uninstallOptions) {
		this.uninstallOptions = uninstallOptions;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setAttribute(String name, Object value) {
		if ("name".equalsIgnoreCase(name)) {
			this.name = StringHelper.parse(value);
		} else if ("provider".equalsIgnoreCase(name)) {
			if ((value != null) && (value instanceof PackageProvider)) {
				this.provider = (PackageProvider) value;
			} else {
				String pkg = StringHelper.parse(value).toUpperCase();
				this.provider = PackageProvider.valueOf(pkg);
			};
		} else if ("configfiles".equalsIgnoreCase(name)) {
			if ((value != null) && (value instanceof DebianConfigFilesStatus)) {
				this.configFiles = (DebianConfigFilesStatus) value;
			} else {
				String pkg = StringHelper.parse(value).toUpperCase();
				this.configFiles = DebianConfigFilesStatus.valueOf(pkg);
			};
		} else if ("ensure".equalsIgnoreCase(name)) {
			this.ensure = StringHelper.parse(value);
		} else if ("installOptions".equalsIgnoreCase(name)) {
			try {
				this.installOptions = new ArrayList<>((Collection) value);
			} catch (Exception ex) {
				this.installOptions.add(StringHelper.parse(value));
			}
		} else if ("source".equalsIgnoreCase(name)) {
			this.source = StringHelper.parse(value);
		} else if ("uninstallOptions".equalsIgnoreCase(name)) {
			try {
				this.uninstallOptions = new ArrayList<>((Collection) value);
			} catch (Exception ex) {
				this.uninstallOptions.add(StringHelper.parse(value));
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((provider == null) ? 0 : provider.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PackageProperties other = (PackageProperties) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (provider != other.provider)
			return false;
		return true;
	}
}
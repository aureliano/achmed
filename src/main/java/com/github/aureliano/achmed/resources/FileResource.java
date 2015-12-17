package com.github.aureliano.achmed.resources;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.AppConfiguration;
import com.github.aureliano.achmed.helper.FileHelper;
import com.github.aureliano.achmed.helper.StringHelper;
import com.github.aureliano.achmed.os.fs.IFileProvider;
import com.github.aureliano.achmed.resources.properties.FileProperties;
import com.github.aureliano.achmed.resources.properties.IResourceProperties;
import com.github.aureliano.achmed.types.EnsureFileStatus;

public class FileResource implements IResource {
	
	private static final Logger logger = Logger.getLogger(FileResource.class);
	
	private FileProperties properties;
	
	public FileResource() {
		this.properties = new FileProperties();
	}

	public FileResource(FileProperties properties) {
		this.properties = properties;
	}
	
	public void apply() {
		logger.info(" >>> Apply file resource to " + this.properties.getPath());
		
		this.properties.configureAttributes();
		this.amendPaths();
		this.amendTexts();
		
		IFileProvider provider = AppConfiguration.instance().getOperatingSystem().getDefaultFileProvider();
		provider.setFileProperties(this.properties);
		
		this.applyFilePermissions(provider);
		this.ensure(provider);
	}

	public void setProperties(IResourceProperties properties) {
		this.properties = (FileProperties) properties;
	}
	
	public IResourceProperties getProperties() {
		return this.properties;
	}

	public ResourceType type() {
		return ResourceType.FILE;
	}
	
	private void amendPaths() {
		this.properties.setPath(FileHelper.amendFilePath(this.properties.getPath()));
		if (!StringHelper.isEmpty(this.properties.getSource())) {
			this.properties.setSource(FileHelper.amendFilePath(this.properties.getSource()));
		}
		if (!StringHelper.isEmpty(this.properties.getTarget())) {
			this.properties.setTarget(FileHelper.amendFilePath(this.properties.getTarget()));
		}
	}
	
	private void amendTexts() {
		if (!StringHelper.isEmpty(this.properties.getGroup())) {
			this.properties.setGroup(StringHelper.amendEnvVars(this.properties.getGroup()));
		}
		
		if (!StringHelper.isEmpty(this.properties.getOwner())) {
			this.properties.setOwner(StringHelper.amendEnvVars(this.properties.getOwner()));
		}
	}
	
	private void applyFilePermissions(IFileProvider provider) {
		if (!StringHelper.isEmpty(this.properties.getMode())) {
			logger.info("Setting file mode " + this.properties.getMode() + " to " + this.properties.getPath());
			provider.setFileMode();
		}
		
		if (!((StringHelper.isEmpty(this.properties.getGroup())) && (StringHelper.isEmpty(this.properties.getOwner())))) {
			provider.setFileOwner();
		}
	}
	
	private void ensure(IFileProvider provider) {
		if (EnsureFileStatus.ABSENT.equals(this.properties.getEnsure())) {
			logger.info("Apply file absence to " + this.properties.getPath());
			provider.ensureFileAbsence();
		} else {
			logger.info("Apply file presence to " + this.properties.getPath());
			provider.ensureFilePresence();
		}
	}
}
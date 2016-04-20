package com.github.aureliano.achmed.resources;

import java.util.logging.Logger;

import com.github.aureliano.achmed.AppConfiguration;
import com.github.aureliano.achmed.helper.FileHelper;
import com.github.aureliano.achmed.helper.StringHelper;
import com.github.aureliano.achmed.os.fs.IFileProvider;
import com.github.aureliano.achmed.resources.properties.FileProperties;
import com.github.aureliano.achmed.resources.properties.IResourceProperties;
import com.github.aureliano.achmed.types.EnsureFileStatus;

public class FileResource implements IResource {
	
	private static final Logger logger = Logger.getLogger(FileResource.class.getName());
	
	private FileProperties properties;
	
	public FileResource() {
		this.properties = new FileProperties();
	}

	public FileResource(FileProperties properties) {
		this.properties = properties;
	}
	
	public void apply() {
		this.properties.configureAttributes();
		logger.fine("Resource description: " + this.properties.get("description"));
		logger.info(" >>> Apply file resource to " + this.properties.getPath());
		this.amendPaths();
		
		this.amendTexts();
		
		IFileProvider provider = AppConfiguration.instance().getOperatingSystem().getDefaultFileProvider();
		provider.setFileProperties(this.properties);
		
		this.ensure(provider);
		this.applyFilePermissions(provider);
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
			provider.setFileMode();
		}
		
		if (!((StringHelper.isEmpty(this.properties.getGroup())) && (StringHelper.isEmpty(this.properties.getOwner())))) {
			provider.setFileOwner();
		}
	}
	
	private void ensure(IFileProvider provider) {
		logger.info("Ensure " + this.properties.getEnsure() + " about " + this.properties.getPath());
		if (EnsureFileStatus.ABSENT.equals(this.properties.getEnsure())) {
			provider.ensureFileAbsence();
		} else {
			provider.ensureFilePresence();
		}
	}
}
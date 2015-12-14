package com.github.aureliano.achmed.os.fs;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.github.aureliano.achmed.command.CommandFacade;
import com.github.aureliano.achmed.command.CommandResponse;
import com.github.aureliano.achmed.exception.FileResourceException;
import com.github.aureliano.achmed.helper.BooleanHelper;
import com.github.aureliano.achmed.helper.FileHelper;
import com.github.aureliano.achmed.helper.StringHelper;
import com.github.aureliano.achmed.resources.properties.FileProperties;
import com.github.aureliano.achmed.types.EnsureFileStatus;

public class PosixFileProvider implements IFileProvider {

	private static final Logger logger = Logger.getLogger(PosixFileProvider.class);
	
	private FileProperties properties;
	
	public PosixFileProvider() {
		super();
	}

	@Override
	public void setFileMode() {
		CommandResponse res = CommandFacade.executeCommand(
			"chmod", this.properties.getMode(), this.properties.getPath());
		
		if (!res.isOK()) {
			throw new FileResourceException(res);
		}
	}

	@Override
	public void setFileOwner() {
		List<String> ownerAndGroup = new ArrayList<String>();
		if (!StringHelper.isEmpty(this.properties.getOwner())) {
			ownerAndGroup.add(this.properties.getOwner());
		}
		
		if ((!StringHelper.isEmpty(this.properties.getOwner())) && (!StringHelper.isEmpty(this.properties.getGroup()))) {
			ownerAndGroup.add(":");
		}
		
		if (!StringHelper.isEmpty(this.properties.getGroup())) {
			ownerAndGroup.add(this.properties.getOwner());
		}
		
		if (ownerAndGroup.isEmpty()) {
			logger.warn("Ignore setting file owner because no owner neither group was provided.");
			return;
		}
		
		ownerAndGroup.add(0, "chown");
		CommandResponse res = CommandFacade.executeCommand(ownerAndGroup.toArray(new String[0]));
		
		if (!res.isOK()) {
			throw new FileResourceException(res);
		}
	}
	
	@Override
	public void createFile() {
		if (EnsureFileStatus.DIRECTORY.equals(this.properties.getEnsure())) {
			this.createDirectory();
		} else if (EnsureFileStatus.LINK.equals(this.properties.getEnsure())) {
			this.createSymLink();
		} else if (EnsureFileStatus.ABSENT.equals(this.properties.getEnsure())) {
			 throw new FileResourceException("Cannot create file with absent status.");
		} else {
			this.ensureFile();
		}
	}
	
	@Override
	public void deleteFile() {
		if (!EnsureFileStatus.ABSENT.equals(this.properties.getEnsure())) {
			throw new FileResourceException("Cannot delete file with a different status absent.");
		}
		
		File target = new File(this.properties.getPath());
		if (target.isDirectory()) {
			this.deleteDirectory();
			FileHelper.assertDirectoryDoesNotExist(this.properties.getPath());
		} else {
			FileHelper.delete(target);
			FileHelper.assertFileDoesNotExist(this.properties.getPath());
		}
	}

	@Override
	public void copyFile() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public void copyFolder() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public void setFileProperties(FileProperties properties) {
		this.properties = properties;
	}

	@Override
	public FileProperties getFileProperties() {
		return this.properties;
	}
	
	private void ensureFile() {
		File target = new File(this.properties.getPath());
		
		if (target.exists()) {
			boolean shouldReplace = ((this.properties.isReplace() != null) && (this.properties.isReplace()));
			if (!StringHelper.isEmpty(this.properties.getBackup()) && (shouldReplace)) {
				Boolean shouldMakeBackup = BooleanHelper.parse(this.properties.getBackup());
				String backupPath = null;
				
				if (shouldMakeBackup == null) {
					backupPath = this.properties.getBackup();
				} else if (shouldMakeBackup) {
					backupPath = target.getAbsolutePath() + ".achmed-bak";
				}
			
				if (backupPath != null) {
					File bkp = new File(backupPath);
					FileHelper.copyFile(target, bkp);
					logger.debug("File " + target.getAbsolutePath() + " was backed up to " + bkp);
				}
			}
			
			if (shouldReplace) {
				this.writeContent(target);
			}
			
			logger.debug("Ignore file replacing.");
		} else {
			this.writeContent(target);
		}
		
		FileHelper.assertRegularFileExist(this.properties.getPath());
	}
	
	private void deleteDirectory() {
		if ((this.properties.isForce() == null) || (!this.properties.isForce())) {
			throw new FileResourceException("Cannot recreate directory " + this.properties.getPath() + " when force property isn't true.");
		}
		
		File target = new File(this.properties.getPath());
		FileHelper.delete(target, true);
	}
	
	private void writeContent(File target) {
		if (!StringHelper.isEmpty(this.properties.getSource())) {
			File source = new File(this.properties.getSource());
			
			logger.debug("Copy file " + source.getAbsolutePath() + " to " + target.getAbsolutePath());
			FileHelper.copyFile(source, target);
		} else {
			String content = (this.properties.getSource() != null) ? this.properties.getSource() : "";
			try (
				OutputStream stream = new FileOutputStream(target);
				BufferedOutputStream bos = new BufferedOutputStream(stream);
			) {
				bos.write(content.getBytes());
				bos.flush();
			} catch (IOException ex) {
				throw new FileResourceException(ex);
			}
		}
	}
	
	private void createDirectory() {
		File targetDir = new File(this.properties.getPath());
		
		if ((targetDir.exists()) && ((this.properties.isForce() == null || !this.properties.isForce()))) {
			throw new FileResourceException("Cannot recreate directory " + this.properties.getPath() + " when force property isn't true.");
		} else if (targetDir.isDirectory()) {
			FileHelper.delete(targetDir, true);
		}
		
		targetDir = new File(this.properties.getPath()); // Refresh file object.
		if (StringHelper.isEmpty(this.properties.getSource())) {
			FileHelper.createDirectory(targetDir, true);
			FileHelper.assertDirectoryExist(targetDir.getAbsolutePath());
			
			return;
		}
		
		File sourceDir = new File(this.properties.getSource());
		if (!sourceDir.isDirectory()) {
			throw new FileResourceException("Source " + sourceDir.getAbsolutePath() + " doesn't exist nor is a directory.");
		}
		
		FileHelper.copyDirectory(sourceDir, targetDir);
		FileHelper.assertDirectoryExist(this.properties.getPath());
	}
	
	private void createSymLink() {
		if (StringHelper.isEmpty(this.properties.getTarget())) {
			throw new FileResourceException("Property 'target' not provided to symlink file.");
		}
		
		logger.debug(String.format("Create symbolik link '%s' => '%s'", this.properties.getPath(), this.properties.getTarget()));
		CommandResponse res = CommandFacade.executeCommand("ln", "-s", this.properties.getPath(), this.properties.getTarget());
		if (!res.isOK()) {
			throw new FileResourceException(res);
		}
		
		FileHelper.assertSymbolicLinkExist(this.properties.getTarget());
	}
}
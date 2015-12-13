package com.github.aureliano.achmed.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import com.github.aureliano.achmed.exception.AchmedException;

public final class FileHelper {

	private FileHelper() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
	
	public static void copyFile(File sourceFile, File destFile) {
		FileChannel source = null;
		FileChannel destination = null;
		
		try {
			if(!destFile.exists()) {
				destFile.createNewFile();
			}
		
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
			
			source.close();
			destination.close();
		} catch (IOException ex) {
			throw new AchmedException(ex);
		}
	}
	
	public static void copyFile(File sourceFile, File destFile, boolean mkdirs) {
		if (mkdirs) {
			File parentDestFile = destFile.getParentFile();
			if ((parentDestFile != null) && (!parentDestFile.exists())) {
				if (!parentDestFile.mkdirs()) {
					throw new AchmedException("Could not create directory " + parentDestFile.getPath());
				}
			}
		}
		
		FileHelper.copyFile(sourceFile, destFile);
	}
}
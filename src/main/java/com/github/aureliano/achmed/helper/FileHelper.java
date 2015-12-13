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
		try(
			FileInputStream inputStream = new FileInputStream(sourceFile);
			FileOutputStream outputStream = new FileOutputStream(destFile);
		) {
			if(!destFile.exists()) {
				destFile.createNewFile();
			}
		
			FileChannel source = inputStream.getChannel();
			FileChannel destination = outputStream.getChannel();
			destination.transferFrom(source, 0, source.size());
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
	
	public static void delete(File file) {
		if (!file.delete()) {
			throw new AchmedException("Could not delete file " + file.getPath());
		}
	}
	
	public static void delete(File file, boolean recursively) {
		if (recursively) {
			forceDelete(file);
		} else {
			delete(file);
		}
	}
	
	private static void forceDelete(File file) {
		if (file.isDirectory()) {
			File[] children = file.listFiles();
			
			if ((children != null) && (children.length > 0)) {
				for (File child : children) {
					forceDelete(child);
				}
			}
		}
		
		if (!file.delete()) {
			throw new AchmedException("Could not delete file " + file.getPath());
		}
	}
}
package com.github.aureliano.achmed.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
	
	public static void copyDirectory(File sourceDirectory, File destDirectory) {
		final File[] srcFiles = sourceDirectory.listFiles();
		if (srcFiles == null) {
			throw new AchmedException("Failed to list contents of " + sourceDirectory);
		}
		
		if (destDirectory.exists()) {
			if (destDirectory.isDirectory() == false) {
				throw new AchmedException("Destination '" + destDirectory + "' exists but is not a directory");
			}
		} else {
			if (!destDirectory.mkdirs() && !destDirectory.isDirectory()) {
				throw new AchmedException("Destination '" + destDirectory + "' directory cannot be created");
			}
		}
		
		if (destDirectory.canWrite() == false) {
			throw new AchmedException("Destination '" + destDirectory + "' cannot be written to");
		}
		
		for (final File srcFile : srcFiles) {
			final File dstFile = new File(destDirectory, srcFile.getName());
		
			if (srcFile.isDirectory()) {
				copyDirectory(srcFile, dstFile);
			} else {
				copyFile(srcFile, dstFile);
			}
		}
	
		destDirectory.setLastModified(sourceDirectory.lastModified());
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
	
	public static void createDirectory(File file, boolean chain) {
		if (file.exists()) {
			return;
		}
		
		AchmedException ex = new AchmedException("Could not create directory " + file.getPath());
		if (chain) {
			if (!file.mkdirs()) {
				throw ex;
			}
		} else {
			if (!file.mkdir()) {
				throw ex;
			}
		}
	}
	
	public static void assertDirectoryExist(String path) {
		if (!isDirectory(path)) {
			throw new AchmedException("Directory " + path + " doesn't exist nor is a directory.");
		}
	}
	
	public static void assertDirectoryDoesNotExist(String path) {
		if (isDirectory(path)) {
			throw new AchmedException("Directory " + path + " present when expected to doesn't exist.");
		}
	}
	
	public static void assertSymbolicLinkExist(String path) {
		if (!isSymbolicLink(path)) {
			throw new AchmedException(path + " is not a symbolic link.");
		}
	}
	
	public static void assertRegularFileExist(String path) {
		if (!isRegularFile(path)) {
			throw new AchmedException(path + " is not a regular file.");
		}
	}
	
	public static void assertFileDoesNotExist(String path) {
		if (new File(path).exists()) {
			throw new AchmedException(path + " exist when expected to does not exist.");
		}
	}
	
	public static String readFile(String path) {
		return readFile(new File(path));
	}
	
	
	public static String readFile(File file) {
		try {
			return readFile(new FileInputStream(file));
		} catch (IOException ex) {
			throw new AchmedException(ex);
		}
	}
	
	public static String readFile(InputStream stream) {
		StringBuilder builder = new StringBuilder();
		
		try (
			InputStreamReader inputReader = new InputStreamReader(stream);
			BufferedReader reader = new BufferedReader(inputReader);
		) {
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				if (builder.length() > 0) {
					builder.append("\n");
				}
				builder.append(line);
			}
		} catch (IOException ex) {
			throw new AchmedException(ex);
		}
		
		return builder.toString();
	}
	
	public static String readResource(String resourceName) {
		InputStream stream = ClassLoader.getSystemResourceAsStream(resourceName);
		return FileHelper.readFile(stream);
	}
	
	public static String amendFilePath(String path) {
		String newPath = null;
		
		if (path.startsWith("~")) {
			String home = System.getenv("HOME");
			newPath = path.replaceFirst("^~", home.replaceAll("/$", ""));
		}
		
		if (newPath == null) {
			newPath = path;
		}
		
		String[] tokens = newPath.split(File.separator);
		List<String> paths = new ArrayList<String>();
		
		for (String token : tokens) {
			if (token.isEmpty()) {
				paths.add(File.separator);
			} else if (token.matches("^\\$[\\w\\d]+")) {
				String var = System.getenv(token.replaceFirst("^\\$", ""));
				paths.add(var);
			} else {
				paths.add(token);
			}
		}
		
		newPath = StringHelper.join(paths, File.separator);
		String doubleSeparatorRegex = Pattern.quote(File.separator + File.separator);
		
		return newPath.replaceAll(doubleSeparatorRegex, File.separator);
	}
	
	public static boolean isRegularFile(String path) {
		return Files.isRegularFile(Paths.get(path));
	}
	
	public static boolean isSymbolicLink(String path) {
		return Files.isSymbolicLink(Paths.get(path));
	}
	
	public static boolean isDirectory(String path) {
		File dir = new File(path);
		return dir.isDirectory();
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
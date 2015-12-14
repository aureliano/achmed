package com.github.aureliano.achmed.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.github.aureliano.achmed.exception.AchmedException;

public class FileHelperTest {

	@Test
	public void testCopyFile() {
		File srcFile = new File("src/test/resources/simple_file");
		File destFile = new File("target/thrash/copied");
		
		if (destFile.exists()) {
			destFile.delete();
		}
		
		assertTrue(srcFile.isFile());
		assertFalse(destFile.exists());
		
		FileHelper.copyFile(srcFile, destFile, true);
		destFile = new File(destFile.getPath()); // refresh file object.
		
		assertTrue(destFile.exists());
		assertTrue(destFile.isFile());
	}
	
	@Test
	public void testDelete() {
		this.createDirectoryStructure();
		
		FileHelper.delete(new File("target/thrash"), true);
		assertFalse(new File("target/thrash").exists());
	}
	
	@Test(expected = AchmedException.class)
	public void testAssertDirectoryExistEmpty() {
		String path = "";
		FileHelper.assertDirectoryExist(path);
	}
	
	@Test(expected = AchmedException.class)
	public void testAssertDirectoryExistFile() {
		String path = "src/test/resources/simple_file";
		FileHelper.assertDirectoryExist(path);
	}
	
	@Test
	public void testAssertDirectoryExist() {
		String path = "src/test/resources";
		FileHelper.assertDirectoryExist(path);
	}
	
	@Test
	public void testCopyDirectory() {
		this.createDirectoryStructure();
		
		File dir = new File("target/test-copy-directory");
		if (dir.exists()) {
			FileHelper.delete(dir, true);
		}
		
		assertFalse(dir.exists());
		
		FileHelper.copyDirectory(new File("src/test/resources"), dir);
		dir = new File(dir.getAbsolutePath());
		assertTrue(dir.exists());
		
		assertEquals(3, dir.list().length);
	}
	
	private void createDirectoryStructure() {
		File sourceDir = new File("src/test/resources");
		
		for (File file : sourceDir.listFiles()) {
			if (file.isFile()) {
				FileHelper.copyFile(file, new File("target/thrash/" + file.getName()), true);
			}
		}
		
		for (File file : sourceDir.listFiles()) {
			if (file.isFile()) {
				FileHelper.copyFile(file, new File("target/thrash/level2/" + file.getName()), true);
			}
		}
	}
}
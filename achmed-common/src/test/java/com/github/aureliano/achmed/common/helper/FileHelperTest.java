package com.github.aureliano.achmed.common.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Test;

import com.github.aureliano.achmed.common.exception.AchmedException;

public class FileHelperTest {
	
	private static final String SYMLINK_PATH = "target/symlink";
	
	@After
	public void tearDown() {
		String symlink = SYMLINK_PATH;
		new File(symlink).delete();
	}

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
	
	@Test(expected = AchmedException.class)
	public void testAssertDirectoryDoesNotExistError() {
		String path = "src/test/resources";
		FileHelper.assertDirectoryDoesNotExist(path);
	}
	
	@Test
	public void testAssertDirectoryDoesNotExist() {
		String path = "src/test/resources/nonexistent";
		FileHelper.assertDirectoryDoesNotExist(path);
	}
	
	@Test(expected = AchmedException.class)
	public void testAssertSymbolicLinkEmpty() {
		String path = "";
		FileHelper.assertSymbolicLinkExist(path);
	}
	
	@Test(expected = AchmedException.class)
	public void testAssertSymbolicLinkDirectory() {
		String path = "src/test/resources";
		FileHelper.assertSymbolicLinkExist(path);
	}
	
	@Test
	public void testAssertSymbolicLink() throws IOException {
		String path = "target/thrash/";
		String symlink = "target/symlink";
		
		Files.createSymbolicLink(Paths.get(symlink), Paths.get(path));
		FileHelper.assertSymbolicLinkExist(symlink);
	}
	
	@Test(expected = AchmedException.class)
	public void testAssertRegularFileExistEmpty() {
		String path = "";
		FileHelper.assertRegularFileExist(path);
	}
	
	@Test(expected = AchmedException.class)
	public void testAssertRegularFileExistDirectory() {
		String path = "src/test/resources";
		FileHelper.assertRegularFileExist(path);
	}
	
	@Test(expected = AchmedException.class)
	public void testAssertRegularFileExistSymlink() throws IOException {
		String path = "target/thrash/";
		String symlink = "target/symlink";
		
		Files.createSymbolicLink(Paths.get(symlink), Paths.get(path));
		
		FileHelper.assertRegularFileExist(symlink);
	}
	
	@Test
	public void testAssertRegularFileExist() {
		String path = "src/test/resources/simple_file";
		FileHelper.assertRegularFileExist(path);
	}
	
	@Test(expected = AchmedException.class)
	public void testAssertFileDoesNotExistError() {
		String path = "src/test/resources/simple_file";
		FileHelper.assertFileDoesNotExist(path);
	}
	
	@Test
	public void testAssertFileDoesNotExist() {
		String path = "src/test/resources/nonexistent";
		FileHelper.assertFileDoesNotExist(path);
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
	
	@Test
	public void testReadFile() throws FileNotFoundException {
		String path = "src/test/resources/simple_file";
		String expected = "Ecce quam bonum et quam icundum habitare fratres in unum.";
		
		assertEquals(expected, FileHelper.readFile(path));
		assertEquals(expected, FileHelper.readFile(new File(path)));
		assertEquals(expected, FileHelper.readFile(new FileInputStream(new File(path))));
	}
	
	@Test
	public void testReadResource() {
		String resourceName = "simple_file";
		String expected = "Ecce quam bonum et quam icundum habitare fratres in unum.";
		
		assertEquals(expected, FileHelper.readResource(resourceName));
	}
	
	@Test
	public void testBuildPath() {
		assertEquals("src/test/resources", FileHelper.buildPath("src", "test", "resources"));
	}
	
	@Test
	public void testBuildFile() {
		assertEquals(new File("src/test/resources"), FileHelper.buildFile("src", "test", "resources"));
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
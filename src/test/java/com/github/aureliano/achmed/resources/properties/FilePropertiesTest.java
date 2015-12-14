package com.github.aureliano.achmed.resources.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aureliano.achmed.types.EnsureFileStatus;

public class FilePropertiesTest {

	@Test
	public void testDefaultValues() {
		FileProperties file = new FileProperties();
		
		assertFalse(file.isForce());
		assertFalse(file.isPurge());
		assertFalse(file.isReplace());
	}

	@Test
	public void testConfigureAttributes() {
		FileProperties file = (FileProperties) new FileProperties()
			.put("path", "/path/to/file")
			.put("ensure", EnsureFileStatus.FILE)
			.put("backup", "/path/to/file.backup")
			.put("content", "File content")
			.put("force", false)
			.put("group", "terrorist")
			.put("ignore", "ignore")
			.put("mode", "0000")
			.put("owner", "Achmed")
			.put("purge", false)
			.put("recurse", false)
			.put("replace", true)
			.put("source", "/path/to/source/file")
			.put("target", "/path/to/target")
			.configureAttributes();
		
		assertEquals("/path/to/file", file.getPath());
		assertEquals(EnsureFileStatus.FILE, file.getEnsure());
		assertEquals("/path/to/file.backup", file.getBackup());
		assertEquals("File content", file.getContent());
		assertFalse(file.isForce());
		assertEquals("terrorist", file.getGroup());
		assertEquals("ignore", file.getIgnore());
		assertEquals("0000", file.getMode());
		assertEquals("Achmed", file.getOwner());
		assertFalse(file.isPurge());
		assertTrue(file.isReplace());
		assertEquals("/path/to/source/file", file.getSource());
		assertEquals("/path/to/target", file.getTarget());
		
		file.put("ensure", "direcTory").configureAttributes();
		assertEquals(EnsureFileStatus.DIRECTORY, file.getEnsure());
	}

	@Test
	public void testHashCode() {
		FileProperties f1 = new FileProperties();
		FileProperties f2 = new FileProperties();
		
		assertEquals(f1.hashCode(), f2.hashCode());
		
		f1.setEnsure(EnsureFileStatus.ABSENT);
		assertFalse(f1.hashCode() == f2.hashCode());
		
		f2.setEnsure(EnsureFileStatus.ABSENT);
		assertTrue(f1.hashCode() == f2.hashCode());
		
		f1.setPath("/path/to/file");
		assertFalse(f1.hashCode() == f2.hashCode());

		f2.setPath("/path/to/file");
		assertTrue(f1.hashCode() == f2.hashCode());
	}
	
	@Test
	public void testEquals() {
		FileProperties f1 = new FileProperties();
		FileProperties f2 = new FileProperties();
		
		assertEquals(f1, f2);
		
		f1.setEnsure(EnsureFileStatus.ABSENT);
		assertFalse(f1.equals(f2));
		
		f2.setEnsure(EnsureFileStatus.ABSENT);
		assertTrue(f1.equals(f2));
		
		f1.setPath("/path/to/file");
		assertFalse(f1.equals(f2));

		f2.setPath("/path/to/file");
		assertTrue(f1.equals(f2));
	}
}
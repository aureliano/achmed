package com.github.aureliano.achmed.client.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aureliano.achmed.client.resources.FileResource;
import com.github.aureliano.achmed.client.resources.ResourceType;
import com.github.aureliano.achmed.client.resources.properties.FileProperties;
import com.github.aureliano.achmed.client.types.EnsureFileStatus;

public class FileResourceTest {
	
	@Test
	public void testType() {
		FileResource r = new FileResource();
		assertEquals(ResourceType.FILE, r.type());
	}
	
	@Test
	public void testGetProperties() {
		FileResource r = new FileResource(this.createProperties());
		
		FileProperties p = (FileProperties) r.getProperties();
		this.checkProperties(p);
		
		r = new FileResource();
		r.setProperties(this.createProperties());
		
		p = (FileProperties) r.getProperties();
		this.checkProperties(p);
	}

	private FileProperties createProperties() {
		FileProperties p = new FileProperties();
		
		p.setPath("/path/to/file");
		p.setEnsure(EnsureFileStatus.FILE);
		p.setBackup("/path/to/file.backup");
		p.setContent("File content");
		p.setForce(false);
		p.setGroup("terrorist");
		p.setMode("0000");
		p.setOwner("Achmed");
		p.setReplace(true);
		p.setSource("/path/to/source/file");
		p.setTarget("/path/to/target");
		
		return p;
	}

	private void checkProperties(FileProperties p) {
		assertEquals("/path/to/file", p.getPath());
		assertEquals(EnsureFileStatus.FILE, p.getEnsure());
		assertEquals("/path/to/file.backup", p.getBackup());
		assertEquals("File content", p.getContent());
		assertFalse(p.getForce());
		assertEquals("terrorist", p.getGroup());
		assertEquals("0000", p.getMode());
		assertEquals("Achmed", p.getOwner());
		assertTrue(p.getReplace());
		assertEquals("/path/to/source/file", p.getSource());
		assertEquals("/path/to/target", p.getTarget());
	}
}
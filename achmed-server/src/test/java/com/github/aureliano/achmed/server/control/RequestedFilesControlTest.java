package com.github.aureliano.achmed.server.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import junit.framework.Assert;

import org.junit.Test;

import com.github.aureliano.achmed.server.model.RequestedFileModel;

public class RequestedFilesControlTest {

	@Test
	public void testInstance() {
		RequestedFilesControl c = RequestedFilesControl.instance();
		
		assertNotNull(c);
		assertTrue(c == RequestedFilesControl.instance());
		assertTrue(c == RequestedFilesControl.instance());
	}
	
	@Test
	public void testAddRequest() {
		RequestedFilesControl c = RequestedFilesControl.instance();
		boolean added = c.addRequest("1", "file1");
		
		assertTrue(added);
		assertEquals(1, c.size());
		
		added = c.addRequest("1", "file1");
		
		assertFalse(added);
		assertEquals(1, c.size());
		
		added = c.addRequest("2", "file1");
		
		assertTrue(added);
		assertEquals(2, c.size());
		
		added = c.addRequest("3", "/path/to/another/file");
		
		assertTrue(added);
		assertEquals(3, c.size());
	}
	
	@Test
	public void testGet() {
		RequestedFilesControl c = RequestedFilesControl.instance();
		c.addRequest("1", "file1");
		c.addRequest("2", "file2");
		c.addRequest("3", "file3");
		
		RequestedFileModel expected = new RequestedFileModel().withId("2");
		RequestedFileModel actual = c.get("2");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRemoveOldRequests() {
		RequestedFilesControl c = RequestedFilesControl.instance();
		c.addRequest("1", "file1");
		c.addRequest("2", "file2");
		c.addRequest("3", "file3");
		
		long timeSlept = 1500;
		this.sleep(timeSlept);
		
		c.addRequest("4", null);
		c.addRequest("5", null);
		
		assertEquals(5, c.size());
		c.removeOldRequests(timeSlept);
		assertEquals(2, c.size());
	}
	
	private void sleep(long timeMillis) {
		try {
			Thread.sleep(timeMillis);
		} catch (InterruptedException ex) {
			Assert.fail(ex.getMessage());
		}
	}
}
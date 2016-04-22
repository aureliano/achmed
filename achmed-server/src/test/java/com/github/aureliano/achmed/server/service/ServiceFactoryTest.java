package com.github.aureliano.achmed.server.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ServiceFactoryTest {

	@Test
	public void testCreateService() {
		IService service = ServiceFactory.createService(ServiceType.READ_FILE);
		assertTrue(service instanceof ReadFileService);
	}
}
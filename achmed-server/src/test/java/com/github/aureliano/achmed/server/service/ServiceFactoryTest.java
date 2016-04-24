package com.github.aureliano.achmed.server.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aureliano.achmed.common.exception.AchmedException;

public class ServiceFactoryTest {

	@Test
	public void testCreateServiceByType() {
		IService service = ServiceFactory.createService(ServiceType.READ_FILE);
		assertTrue(service instanceof CheckFileStatusService);
	}
	
	@Test(expected = AchmedException.class)
	public void testCreateServiceByRequestStringError1() {
		ServiceFactory.createService("");
	}
	
	@Test(expected = AchmedException.class)
	public void testCreateServiceByRequestStringError2() {
		ServiceFactory.createService("invalid");
	}
	
	@Test
	public void testCreateServiceByRequestString() {
		IService service = ServiceFactory.createService("service: read_file");
		assertTrue(service instanceof CheckFileStatusService);
	}
}
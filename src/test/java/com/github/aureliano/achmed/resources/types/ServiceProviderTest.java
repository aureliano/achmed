package com.github.aureliano.achmed.resources.types;

import static com.github.aureliano.achmed.resources.types.OperatingSystemFamily.*;
import static com.github.aureliano.achmed.resources.types.ServiceProvider.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ServiceProviderTest {

	@Test
	public void testGetOperatingSystemFamily() {
		assertEquals(UBUNTU, SERVICE.getOperatingSystemFamily());
	}
	
	@Test
	public void testGetServiceName() {
		assertEquals("service", SERVICE.getServiceName());
	}
}
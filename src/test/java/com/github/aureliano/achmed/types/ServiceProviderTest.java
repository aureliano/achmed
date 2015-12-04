package com.github.aureliano.achmed.types;

import static com.github.aureliano.achmed.types.OperatingSystemFamily.*;
import static com.github.aureliano.achmed.types.ServiceProvider.*;
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
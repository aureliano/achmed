package com.github.aureliano.achmed.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aureliano.achmed.resources.properties.ServiceProperties;
import com.github.aureliano.achmed.resources.types.ServiceProvider;

public class ServiceResourceTest {

	@Test(expected = UnsupportedOperationException.class)
	public void testApply() {
		ServiceResource r = new ServiceResource();
		r.apply();
	}
	
	@Test
	public void testType() {
		ServiceResource r = new ServiceResource();
		assertEquals(ResourceType.SERVICE, r.type());
	}
	
	@Test
	public void testGetProperties() {
		ServiceResource r = new ServiceResource(this.createProperties());
		
		ServiceProperties p = (ServiceProperties) r.getProperties();
		this.checkProperties(p);
		
		r = new ServiceResource();
		r.setProperties(this.createProperties());
		
		p = (ServiceProperties) r.getProperties();
		this.checkProperties(p);
	}

	private ServiceProperties createProperties() {
		ServiceProperties p = new ServiceProperties();
		
		p.setBinary("do something");
		p.setEnable(true);
		p.setEnsure(true);
		p.setFlags("-p param");
		p.setHasRestart(true);
		p.setHasStatus(true);
		p.setName("nginx");
		p.setPattern("ng[a-z]+x");
		p.setProvider(ServiceProvider.SERVICE);
		p.setRestart("restart");
		p.setStart("cmd to start");
		p.setStatus("get status");
		p.setStop("cmd to stop");
		
		return p;
	}

	private void checkProperties(ServiceProperties p) {
		assertEquals("do something", p.getBinary());
		assertTrue(p.isEnable());
		assertTrue(p.isEnsure());
		assertEquals("-p param", p.getFlags());
		assertTrue(p.getHasRestart());
		assertTrue(p.getHasStatus());
		assertEquals("nginx", p.getName());
		assertEquals("ng[a-z]+x", p.getPattern());
		assertEquals(ServiceProvider.SERVICE, p.getProvider());
		assertEquals("restart", p.getRestart());
		assertEquals("cmd to start", p.getStart());
		assertEquals("get status", p.getStatus());
		assertEquals("cmd to stop", p.getStop());
	}
}
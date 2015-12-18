package com.github.aureliano.achmed.resources.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import com.github.aureliano.achmed.validation.ConstraintViolation;
import com.github.aureliano.achmed.validation.ObjectValidator;

public class ServicePropertiesTest {
	
	@Test
	public void testDefaultValues() {
		ServiceProperties service = (ServiceProperties) new ServiceProperties()
			.put("name", "service-name")
			.configureAttributes();
		
		assertEquals("service-name", service.getName());
		assertEquals("service-name", service.getPattern());
		assertEquals(service.getName(), service.getPattern());
		assertFalse(service.getHasRestart());
		assertTrue(service.getHasStatus());
		
		service.setPattern("any pattern");
		service.configureAttributes();
		
		assertFalse(service.getPattern().equals(service.getName()));
	}

	@Test
	public void testConfigureAttributes() {
		ServiceProperties service = (ServiceProperties) new ServiceProperties()
			.put("name", "nginx")
			.put("ensure", true)
			.put("binary", "do something")
			.put("enable", false)
			.put("flags", "-p param")
			.put("hasRestart", false)
			.put("hasStatus", true)
			.put("pattern", "ng[a-z]+x")
			.put("restart", "restart")
			.put("start", "cmd to start")
			.put("status", "get status")
			.put("stop", "cmd to stop")
			.configureAttributes();
		
		assertEquals("nginx", service.getName());
		assertTrue(service.getEnsure());
		assertEquals("do something", service.getBinary());
		assertFalse(service.isEnable());
		assertEquals("-p param", service.getFlags());
		assertFalse(service.getHasRestart());
		assertTrue(service.getHasStatus());
		assertEquals("ng[a-z]+x", service.getPattern());
		assertEquals("restart", service.getRestart());
		assertEquals("cmd to start", service.getStart());
		assertEquals("get status", service.getStatus());
		assertEquals("cmd to stop", service.getStop());
	}

	@Test
	public void testHashCode() {
		ServiceProperties s1 = new ServiceProperties();
		ServiceProperties s2 = new ServiceProperties();
		
		assertEquals(s1.hashCode(), s2.hashCode());
		
		s1.setBinary("./command");
		assertFalse(s1.hashCode() == s2.hashCode());
		
		s2.setBinary("./command");
		assertTrue(s1.hashCode() == s2.hashCode());
		
		s1.setEnsure(false);
		assertFalse(s1.hashCode() == s2.hashCode());
		
		s2.setEnsure(false);
		assertTrue(s1.hashCode() == s2.hashCode());
		
		s1.setName("service");
		assertFalse(s1.hashCode() == s2.hashCode());
		
		s2.setName("service");
		assertTrue(s1.hashCode() == s2.hashCode());
	}
	
	@Test
	public void testEquals() {
		ServiceProperties s1 = new ServiceProperties();
		ServiceProperties s2 = new ServiceProperties();
		
		assertEquals(s1, s2);
		
		s1.setBinary("./command");
		assertFalse(s1.equals(s2));
		
		s2.setBinary("./command");
		assertTrue(s1.equals(s2));
		
		s1.setEnsure(false);
		assertFalse(s1.equals(s2));
		
		s2.setEnsure(false);
		assertTrue(s1.equals(s2));
		
		s1.setName("service");
		assertFalse(s1.equals(s2));
		
		s2.setName("service");
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testValidation() {
		ServiceProperties s = new ServiceProperties();
		s.setName(null);
		s.setEnsure(true);
		
		Set<ConstraintViolation> violations = ObjectValidator.instance().validate(s);
		assertEquals(1, violations.size());
		assertEquals(
			"Expected to find a not empty text for field name.",
			violations.iterator().next().getMessage()
		);
		
		s.setName("");
		violations = ObjectValidator.instance().validate(s);
		assertEquals(1, violations.size());
		assertEquals(
			"Expected to find a not empty text for field name.",
			violations.iterator().next().getMessage()
		);
		
		s.setName("service_name");
		s.setEnsure(null);
		violations = ObjectValidator.instance().validate(s);
		assertEquals(1, violations.size());
		assertEquals(
			"Expected to find a not null value for field ensure.",
			violations.iterator().next().getMessage()
		);
	}
}
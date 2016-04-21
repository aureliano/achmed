package com.github.aureliano.achmed.common.helper;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Test;

import com.github.aureliano.achmed.common.helper.PropertyHelper;

public class PropertyHelperTest {

	@Test	
	public void testLoadProperties() {
		Properties p = PropertyHelper.loadProperties("internationalization.properties");
		
		assertEquals("Install", p.getProperty("en_us.resource.package.provider.install"));
		assertEquals("Instalar", p.getProperty("pt_br.resource.package.provider.install"));
	}
}
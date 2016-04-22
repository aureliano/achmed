package com.github.aureliano.achmed.common.helper;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Properties;

import org.junit.Test;

import com.github.aureliano.achmed.common.helper.PropertyHelper;

public class PropertyHelperTest {

	@Test	
	public void testLoadPropertiesByResourseName() {
		Properties p = PropertyHelper.loadProperties("internationalization.properties");
		
		assertEquals("Install", p.getProperty("en_us.resource.package.provider.install"));
		assertEquals("Instalar", p.getProperty("pt_br.resource.package.provider.install"));
	}
	
	@Test	
	public void testLoadPropertiesByFile() {
		Properties p = PropertyHelper.loadProperties(new File("src/test/resources/internationalization.properties"));
		
		assertEquals("Install", p.getProperty("en_us.resource.package.provider.install"));
		assertEquals("Instalar", p.getProperty("pt_br.resource.package.provider.install"));
	}
}
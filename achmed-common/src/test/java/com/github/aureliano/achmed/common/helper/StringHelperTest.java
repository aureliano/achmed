package com.github.aureliano.achmed.common.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.github.aureliano.achmed.common.helper.StringHelper;

public class StringHelperTest {

	@Test
	public void testIsEmpty() {
		assertTrue(StringHelper.isEmpty(null));
		assertTrue(StringHelper.isEmpty(""));
		assertFalse(StringHelper.isEmpty("text"));
	}
	
	@Test
	public void testToString() {
		assertEquals("null", StringHelper.toString(null));
		assertEquals("text", StringHelper.toString("text"));
		assertEquals("23", StringHelper.toString(23));
	}
	
	@Test
	public void testParse() {
		assertEquals("30", StringHelper.parse(30));
		assertEquals("", StringHelper.parse(""));
		assertNull(StringHelper.parse(null));
	}
	
	@Test
	public void testCapitalize() {
		assertEquals("House", StringHelper.capitalize("house"));
		assertEquals("House", StringHelper.capitalize("HOUSE"));
		assertEquals("House", StringHelper.capitalize("HoUSE"));
		assertEquals("House of torments", StringHelper.capitalize("house OF tormentS"));
	}
	
	@Test
	public void testJoin() {
		assertEquals("12345", StringHelper.join(new Object[] {1, 2, 3, 4, 5}));
		assertEquals("1, 2, 3, 4, 5", StringHelper.join(new Object[] {1, 2, 3, 4, 5}, ", "));
	}
	
	@Test
	public void testIsNumeric() {
		assertTrue(StringHelper.isNumeric("1"));
		assertTrue(StringHelper.isNumeric("45"));
		assertTrue(StringHelper.isNumeric("-54"));
		assertTrue(StringHelper.isNumeric("+54"));
		assertTrue(StringHelper.isNumeric("25.97854"));
		assertTrue(StringHelper.isNumeric("-3365.8787"));
		assertTrue(StringHelper.isNumeric("+3365.8787"));
	}
	
	@Test
	public void testMatch() {
		assertEquals("Ubuntu", StringHelper.match("^Ubuntu", "Ubuntu 12.04.5 LTS \\n \\l").get(0));
		assertEquals("Debian", StringHelper.match("^Debian", "Debian GNU/Linux 6.0 \\n \\l").get(0));
		assertEquals("CentOS", StringHelper.match("^CentOS", "CentOS release 5.2 (Final)\nKernel \\r on an \\m\n").get(0));
		
		assertEquals(" GNU/", StringHelper.match("\\sGNU/", "Debian GNU/Linux 6.0 \\n \\l").get(0));
		
		List<String> found = StringHelper.match("Candidato:\\s+(\\S+)\\s", "telnet:\n  Instalado: 0.17-36build1\n  Candidato: 0.17-36build1\n  Tabela de versão:");
		assertEquals(2, found.size());
		assertEquals("0.17-36build1", found.get(1));
	}
}
package com.github.aureliano.achmed.helper;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class YamlHelperTest {

	@Test
	public void testParseYaml() {
		Map<String, Object> yaml = YamlHelper.parseYaml("src/test/resources/yaml/main.yaml");
		assertEquals("The main infrastructure abstraction.", yaml.get("title"));
		assertEquals("Synchronize the development environment configuration.", yaml.get("description"));
		
		List<String> includes = (List<String>) yaml.get("includes");
		assertEquals("src/test/resources/yaml/sub1.yaml", includes.get(0));
		assertEquals("src/test/resources/yaml/sub2.yaml", includes.get(1));
		
		this.checkSub1(includes);
		this.checkSub2(includes);
	}
	
	private void checkSub1(List<String> includes) {
		Map<String, Object> sub1 = YamlHelper.parseYaml(includes.get(0));
		List<Map<String, Object>> resources = (List<Map<String, Object>>) sub1.get("resources");
		
		Map<String, Object> exec = (Map<String, Object>) resources.get(0).get("exec");
		assertEquals("echo \"Hello World!\"", exec.get("command"));
		
		Map<String, Object> file = (Map<String, Object>) resources.get(1).get("file");
		assertEquals("/path/to/file", file.get("path"));
		assertEquals("present", file.get("ensure"));
		assertEquals(true, file.get("backup"));
		assertEquals("/path/to/source/file", file.get("source"));
	}
	
	private void checkSub2(List<String> includes) {
		Map<String, Object> sub2 = YamlHelper.parseYaml(includes.get(1));
		List<Map<String, Object>> resources = (List<Map<String, Object>>) sub2.get("resources");
		
		Map<String, Object> pkg = (Map<String, Object>) resources.get(0).get("package");
		assertEquals("zip", pkg.get("name"));
		assertEquals("installed", pkg.get("ensure"));
		
		Map<String, Object> service = (Map<String, Object>) resources.get(1).get("service");

		assertEquals("httpd", service.get("name"));
		assertEquals(true, service.get("ensure"));
		assertEquals(false, service.get("enable"));
	}
}
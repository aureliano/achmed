package com.github.aureliano.achmed.common.helper;

import java.util.Map;

import org.yaml.snakeyaml.Yaml;


public final class YamlHelper {

	private static final Yaml YAML_PARSER = new Yaml();
	
	private YamlHelper() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
	
	public static Map<String, Object> parseYaml(String path) {
		String text = FileHelper.readFile(FileHelper.amendFilePath(path));
		return (Map<String, Object>) YAML_PARSER.load(text);
	}
}
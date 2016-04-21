package com.github.aureliano.achmed.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import com.github.aureliano.achmed.Agent;
import com.github.aureliano.achmed.common.exception.AchmedException;
import com.github.aureliano.achmed.common.helper.FileHelper;
import com.github.aureliano.achmed.common.helper.PropertyHelper;
import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.common.helper.YamlHelper;
import com.github.aureliano.achmed.logging.LoggingFactory;
import com.github.aureliano.achmed.resources.IResource;
import com.github.aureliano.achmed.resources.ResourceFactory;

public final class ApplicationHelper {

	private static final Logger logger = LoggingFactory.createLogger(ApplicationHelper.class);
	
	private ApplicationHelper() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
	
	public static void execute(String path) {
		List<IResource> resources = buildAllResources(path);
		
		logger.info("Found " + resources.size() + " resources.");
		new Agent().withResources(resources).apply();
	}
	
	protected static List<IResource> buildAllResources(String path) {
		Map<String, Object> map = parseYamlFile(path);
		
		List<IResource> resources = new ArrayList<>();
		resources.addAll(buildResources(map));
		
		if (map.get("includes") != null) {
			List<String> includes = (List<String>) map.get("includes");
			for (String include : includes) {
				resources.addAll(buildAllResources(include));
			}
		}
		
		return resources;
	}
	
	private static List<IResource> buildResources(Map<String, Object> map) {
		logger.info("Build resource objects from schema " + map.get("schemaPath"));
		logger.info("Title: " + map.get("title"));
		logger.info("Description: " + map.get("description"));
		
		if (map.get("resources") == null) {
			logger.info("There's no resources in schema " + map.get("schemaPath"));
			return new ArrayList<IResource>(0);
		}
		
		List<Map<String, Object>> resources = (List<Map<String, Object>>) map.get("resources");
		List<IResource> objectResources = new ArrayList<>(resources.size());
		for (Map<String, Object> m : resources) {
			if (m.size() > 1) {
				throw new AchmedException("Found more than one resource type inside array. Found: " + m);
			} else if (m.isEmpty()) {
				logger.warning("Empty map?! " + m);
				continue;
			}
			
			String type = m.keySet().iterator().next();
			Map<String, Object> properties = (Map<String, Object>) m.get(type);
			
			IResource resource = ResourceFactory.createResource(type);
			for (String key : properties.keySet()) {
				resource.getProperties().put(key, properties.get(key));
			}
			
			objectResources.add(resource);
		}
		
		return objectResources;
	}
	
	private static Map<String, Object> parseYamlFile(String path) {
		logger.info("Parse yaml file " + path);
		Map<String, Object> map = YamlHelper.parseYaml(path);
		map.put("schemaPath", path);

		return map;
	}
	
	public static String help() {
		String help = FileHelper.readResource("meta/help");
		return help;
	}
	
	public static String version() {
		String art = FileHelper.readResource("meta/version.asc-art");
		Properties properties = PropertyHelper.loadProperties("meta/version.properties");
		String space = "               ";
		
		return new StringBuilder(art)
			.append("\n")
			.append(space)
			.append("\n")
			.append(space)
			.append(properties.get("version"))
			.append(":")
			.append(properties.get("release"))
			.append(" (")
			.append(properties.get("date"))
			.append(")\n")
			.toString();
	}
	
	public static String error(String[] args) {
		String param = StringHelper.join(args, " ");
		if ("".equals(param)) {
			return EasterEggHelper.silence();
		} else if ("greeting".equalsIgnoreCase(param)) {
			return EasterEggHelper.greeting();
		} else if ("hello".equalsIgnoreCase(param)) {
			return EasterEggHelper.hello();
		} else if ("you are dead".equalsIgnoreCase(param)) {
			return EasterEggHelper.youAreDead();
		} else if ("do you like jews?".equalsIgnoreCase(param)) {
			return EasterEggHelper.doYouLikeJews();
		} else if ("bin laden is dead".equalsIgnoreCase(param)) {
			return EasterEggHelper.binLadenIsDead();
		} else if ("jingle bombs".equalsIgnoreCase(param)) {
			return EasterEggHelper.jingleBombs();
		} else if ("joker".equalsIgnoreCase(param)) {
			return EasterEggHelper.tellMeanJewishJoke();
		} else if ("blow up".equalsIgnoreCase(param)) {
			return EasterEggHelper.blowUp();
		} else if ("portrait".equalsIgnoreCase(param)) {
			return EasterEggHelper.portrait();
		} else {
			return "Don't know how to handle such command: " + param;
		}
	}
}
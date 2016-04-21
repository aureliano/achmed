package com.github.aureliano.achmed.common.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.aureliano.achmed.exception.AchmedException;

public final class StringHelper {

	public static final String EMPTY = "";
	private static final String NUMBER_REGEX = "[+-]?\\d+\\.?\\d*";
	
	private StringHelper() {
		super();
	}
	
	public static boolean isEmpty(String text) {
		return ((text == null) || (text.equals(EMPTY)));
	}
	
	public static String toString(Object object) {
		return ((object == null) ? "null" : object.toString());
	}
	
	public static String parse(Object object) {
		return ((object == null) ? null : object.toString());
	}
	
	public static String capitalize(String text) {
		return Character.toUpperCase(text.charAt(0)) + text.substring(1).toLowerCase();
	}
	
	public static String join(Object[] objects) {
		return join(objects, "");
	}
	
	public static String join(Object[] objects, String separator) {
		StringBuilder builder = new StringBuilder();
		int count = 0;
		for (Object object : objects) {
			if (count > 0) {
				builder.append(separator);
			}
		        
		builder.append(object);
		count++;
		}
	
		return builder.toString();
	}

	public static String join(List<?> objects, String separator) {
		return join(objects.toArray(), separator);
	}
	
	public static boolean isNumeric(String value) {
		return (value == null) ? false : value.matches(NUMBER_REGEX);
	}
	
	public static List<String> match(String regex, String target) {
		Matcher matcher = Pattern.compile(regex).matcher(target);
		List<String> groups = new ArrayList<String>();
		
		while (matcher.find()) {
			if (groups.isEmpty()) {
				groups.add(matcher.group());
			}
			
			if (matcher.groupCount() > 0) {
				for (byte i = 0; i < matcher.groupCount(); i++) {
					groups.add(matcher.group(i + 1));
				}
			}
		}
		
		return groups;
	}
	
	public static String amendEnvVars(String text) {
		List<String> match = StringHelper.match("(\\$[\\w\\d]+)", text);
		if (match.isEmpty()) {
			return text;
		}
		
		for (byte i = 1; i < match.size(); i++) {
			String regex = Pattern.quote(match.get(i));
			String var = System.getenv(match.get(i).replaceFirst("^\\$", ""));
			if (var == null) {
				throw new AchmedException("Could not find any environment variable with name " + match.get(i));
			}
			text = text.replaceFirst(regex, var);
		}
		
		return text;
	}
}
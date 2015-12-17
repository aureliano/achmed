package com.github.aureliano.achmed.helper;

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
	
	public static String[] match(String regex, String target) {
		Matcher matcher = Pattern.compile(regex).matcher(target);
		if (!matcher.find()) {
			return null;
		} else if (matcher.groupCount() == 0) {
			return new String[] { matcher.group() };
		}
		
		String[] groups = new String[matcher.groupCount() + 1];
		for (int i = 0; i < groups.length; i++) {
			groups[i] = matcher.group(i);
		}
		
		return groups;
	}
	
	public static String amendEnvVars(String text) {
		String[] match = StringHelper.match("(\\$[\\w\\d]+)", text);
		if (match == null) {
			return text;
		}
		
		for (byte i = 1; i < match.length; i++) {
			String regex = Pattern.quote(match[i]);
			String var = System.getenv(match[i].replaceFirst("^\\$", ""));
			if (var == null) {
				throw new AchmedException("Could not find any environment variable with name " + match[i]);
			}
			text = text.replaceFirst(regex, var);
		}
		
		return text;
	}
}
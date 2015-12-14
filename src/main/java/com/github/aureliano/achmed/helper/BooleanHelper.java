package com.github.aureliano.achmed.helper;

public final class BooleanHelper {

	private BooleanHelper() {
		super();
	}
	
	public static Boolean parse(Object object) {
		if (object == null) {
			return null;
		} else if (object instanceof Boolean) {
			return (Boolean) object;
		} else {
			if (object.toString().toLowerCase().matches("(true|false)")) {
				return Boolean.parseBoolean(object.toString());
			} else {
				return null;
			}
		}
	}
}
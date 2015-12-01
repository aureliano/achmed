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
			return Boolean.parseBoolean(object.toString());
		}
	}
}
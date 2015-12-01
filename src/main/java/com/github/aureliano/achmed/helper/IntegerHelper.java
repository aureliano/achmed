package com.github.aureliano.achmed.helper;

public final class IntegerHelper {

	private IntegerHelper() {
		super();
	}
	
	public static Integer parse(Object object) {
		if (object == null) {
			return null;
		} else if (object instanceof Integer) {
			return (Integer) object;
		} else {
			return Integer.parseInt(object.toString());
		}
	}
}
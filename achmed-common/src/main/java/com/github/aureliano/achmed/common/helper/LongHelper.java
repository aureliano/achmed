package com.github.aureliano.achmed.common.helper;

public final class LongHelper {

	private LongHelper() {
		super();
	}
	
	public static Long parse(Object object) {
		if (object == null) {
			return null;
		} else if (object instanceof Long) {
			return (Long) object;
		} else {
			return Long.parseLong(object.toString());
		}
	}
}
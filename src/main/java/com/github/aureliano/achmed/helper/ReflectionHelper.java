package com.github.aureliano.achmed.helper;

import java.lang.reflect.Method;

public final class ReflectionHelper {

	private ReflectionHelper() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
    
    public static String getSimpleAccessMethodName(Method method) {
    	String name = method.getName().replaceFirst("^(get|is|set|with)", "");
    	return Character.toLowerCase(name.charAt(0)) + name.substring(1);
    }
}
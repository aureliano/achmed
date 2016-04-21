package com.github.aureliano.achmed.common.helper;

import java.lang.reflect.Method;

import com.github.aureliano.achmed.common.exception.AchmedException;

public final class ReflectionHelper {

	private ReflectionHelper() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
    
    public static Object newInstance(Class<?> clazz) {
    	try {
    		return clazz.newInstance();
    	} catch (Exception ex) {
    		throw new AchmedException(ex);
    	}
    }
    
    public static Object callMethod(Object object, String methodName, Class<?>[] parametersType, Object[] methodParameters) {
    	Class<?> clazz = object.getClass();
    	try {
    		Method method = clazz.getMethod(methodName, parametersType);
    		return method.invoke(object, methodParameters);
    	} catch (Exception ex) {
    		throw new AchmedException(ex);
    	}
    }
    
    public static String getSimpleAccessMethodName(Method method) {
    	String name = method.getName().replaceFirst("^(get|is|set|with)", "");
    	return Character.toLowerCase(name.charAt(0)) + name.substring(1);
    }
}
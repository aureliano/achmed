package com.github.aureliano.achmed.os;

import java.util.HashMap;
import java.util.Map;

import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.exception.UnsupportedOperatingSystemException;

public final class LinuxResolver {

	private static final Map<String, Class<? extends IOperatingSystem>> osMap;
	
	static {
		osMap = new HashMap<String, Class<? extends IOperatingSystem>>();
		
		osMap.put("debian", Debian.class);
		osMap.put("ubuntu", Ubuntu.class);
		osMap.put("centos", CentOS.class);
	}
	
	private LinuxResolver() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
	
	public static IOperatingSystem resolveLinux(String name) {
		Class<? extends IOperatingSystem> type = osMap.get(StringHelper.toString(name.toLowerCase()));
		try {
			return type.newInstance();
		} catch (InstantiationException|IllegalAccessException ex) {
			throw new UnsupportedOperatingSystemException("Unresolvable Operating System '" + name + "'.");
		}
	}
}
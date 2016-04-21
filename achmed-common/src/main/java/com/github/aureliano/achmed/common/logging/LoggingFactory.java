package com.github.aureliano.achmed.common.logging;

import java.util.logging.Handler;
import java.util.logging.Logger;

public final class LoggingFactory {

	private LoggingFactory() {}
	
	public static Logger createLogger(Class<?> clazz) {
		Logger logger = Logger.getLogger(clazz.getName());
		Handler[] handlers = logger.getHandlers();
		
		for(Handler handler : handlers) {
			logger.removeHandler(handler);
		}
		
		logger.setUseParentHandlers(false);
		logger.addHandler(new LoggingConsoleHandler());
		
		return logger;
	}
}
package com.github.aureliano.achmed.common.logging;

import java.util.logging.ErrorManager;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LoggingConsoleHandler extends Handler {

	private Formatter formatter;
	
	public LoggingConsoleHandler() {
		this.formatter = new LoggingFormatter();
	}
	
	@Override
	public void publish(LogRecord record) {
		try {
			String message = this.formatter.format(record);
			if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
				System.err.write(message.getBytes());                       
			} else {
				System.out.write(message.getBytes());
			}
		} catch (Exception exception) {
		    super.reportError(null, exception, ErrorManager.FORMAT_FAILURE);
		}
	}

	@Override
	public void flush() {}

	@Override
	public void close() throws SecurityException {}
}
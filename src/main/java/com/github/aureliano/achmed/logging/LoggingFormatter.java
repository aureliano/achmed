package com.github.aureliano.achmed.logging;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LoggingFormatter extends Formatter {

	private static final DateFormat DATE_FORMAT = DateFormat
			.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.getDefault());
	
	@Override
	public String format(LogRecord record) {
		return new StringBuilder()
			.append(DATE_FORMAT.format(new Date()))
			.append(" ")
			.append(record.getLevel())
			.append(" [")
			.append(record.getSourceClassName())
			.append("] ")
			.append(record.getMessage())
			.append(System.getProperty("line.separator"))
			.toString();
	}
}
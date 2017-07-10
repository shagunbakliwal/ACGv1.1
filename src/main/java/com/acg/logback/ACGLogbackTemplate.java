package com.acg.logback;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;

public final class ACGLogbackTemplate extends LayoutBase<ILoggingEvent> {
	public String doLayout(ILoggingEvent event) {
		StringBuilder sbuf = new StringBuilder(128);
		Timestamp t = new Timestamp(event.getTimeStamp());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		sbuf.append(simpleDateFormat.format(t.getTime()));
		sbuf.append(" ");
		sbuf.append(event.getLevel());
		sbuf.append(" [");
		sbuf.append(event.getThreadName());
		sbuf.append("] ");
		sbuf.append(event.getLoggerName());
		sbuf.append(" - ");
		sbuf.append(event.getFormattedMessage());
		sbuf.append(CoreConstants.LINE_SEPARATOR);
		return sbuf.toString();
	}
}
package com.acg.logback;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acg.adapters.ACGAdapter;

public final class AnonymousLogbackTemplate extends ACGAdapter{

	static Logger logger = LoggerFactory.getLogger(AnonymousLogbackTemplate.class);

	@Override
	public void create(String fileStructure, String packageName) {
		File file = new File(fileStructure + "/logback");
		if (!file.exists())
			file.mkdirs();
		RandomAccessFile randomAccessFile = null;
		try {
			randomAccessFile = new RandomAccessFile(file + "/LogbackTemplate.java", "rw");
			randomAccessFile.writeBytes("package " + packageName
					+ ".logback;\r\nimport java.sql.Timestamp;\r\nimport ch.qos.logback.classic.spi.ILoggingEvent;\r\nimport ch.qos.logback.core.CoreConstants;\r\nimport ch.qos.logback.core.LayoutBase;\r\npublic class LogbackTemplate extends LayoutBase<ILoggingEvent> {\r\n\tpublic String doLayout(ILoggingEvent event) {\r\n\t\tStringBuilder sbuf = new StringBuilder(128);\r\n\t\tTimestamp t = new Timestamp(event.getTimeStamp());\r\n\t\tSimpleDateFormat simpleDateFormat = new SimpleDateFormat(\"EEE, d MMM yyyy HH:mm:ss\");\r\n\t\tsbuf.append(simpleDateFormat.format(t.getTime()));\r\n\t\tsbuf.append(\" \");\r\n\t\tsbuf.append(event.getLevel());\r\n\t\tsbuf.append(\" [\");\r\n\t\tsbuf.append(event.getThreadName());\r\n\t\tsbuf.append(\"] \");\r\n\t\tsbuf.append(event.getLoggerName());\r\n\t\tsbuf.append(\" - \");\r\n\t\tsbuf.append(event.getFormattedMessage());\r\n\t\tsbuf.append(CoreConstants.LINE_SEPARATOR);\r\n\t\treturn sbuf.toString();\r\n\t}\r\n}");
			randomAccessFile.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
			logger.error("" + e);
		} finally {
			try {
				if (randomAccessFile != null)
					randomAccessFile.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("" + e);
			}
		}
	}

}

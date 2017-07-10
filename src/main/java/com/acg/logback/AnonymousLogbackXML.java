package com.acg.logback;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acg.adapters.ACGAdapter;
import com.acg.constants.ACGConstants;

public final class AnonymousLogbackXML extends ACGAdapter {

	static Logger logger = LoggerFactory.getLogger(AnonymousLogbackXML.class);

	@Override
	public void create(String fileStructure, String packageName) {
		File file = new File(fileStructure);
		if (!file.exists())
			file.mkdirs();
		RandomAccessFile randomAccessFile = null;
		try {
			randomAccessFile = new RandomAccessFile(file + "/logback.out.xml", "rw");
			try {
				if (randomAccessFile != null)
					randomAccessFile.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("" + e);
			}
			randomAccessFile = new RandomAccessFile(file + "/logback.xml", "rw");
			randomAccessFile.writeBytes("<configuration>\r\n\t");
			randomAccessFile.writeBytes("<appender name=\"SizeAndTime\"");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_TWO_TAB_SEPARATOR);
			randomAccessFile.writeBytes("class=\"ch.qos.logback.core.rolling.RollingFileAppender\">");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_TWO_TAB_SEPARATOR);
			randomAccessFile.writeBytes("<file>logs\\SizeAndTime\\logFile.log</file>");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_TWO_TAB_SEPARATOR);
			randomAccessFile.writeBytes("<rollingPolicy");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_THREE_TAB_SEPARATOR);
			randomAccessFile.writeBytes("class=\"ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy\">");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_THREE_TAB_SEPARATOR);
			randomAccessFile.writeBytes("<maxFileSize>100KB</maxFileSize>");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_THREE_TAB_SEPARATOR);
			randomAccessFile.writeBytes("<fileNamePattern>logs\\SizeAndTime\\logFile.%d{yyyy-MM-dd_HH_mm}.%i.log");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_THREE_TAB_SEPARATOR);
			randomAccessFile.writeBytes("</fileNamePattern>");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_TWO_TAB_SEPARATOR);
			randomAccessFile.writeBytes("</rollingPolicy>");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_TWO_TAB_SEPARATOR);
			randomAccessFile.writeBytes("<encoder class=\"ch.qos.logback.core.encoder.LayoutWrappingEncoder\">");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_THREE_TAB_SEPARATOR);
			randomAccessFile.writeBytes("<layout class=\"" + packageName + ".logback.LogbackTemplate\" />");
			randomAccessFile.writeBytes("\r\n\t\t</encoder>");
			randomAccessFile.writeBytes("\r\n\t</appender>");
			randomAccessFile.writeBytes(
					"\r\n\r\n\t<statusListener class=\"ch.qos.logback.core.status.OnConsoleStatusListener\" />");
			randomAccessFile.writeBytes(
					"\r\n\t<appender name=\"APPLICATIONSIFT\" class=\"ch.qos.logback.classic.sift.SiftingAppender\">");
			randomAccessFile.writeBytes("\r\n\t\t<discriminator");
			randomAccessFile
					.writeBytes("\r\n\t\t\tclass=\"ch.qos.logback.classic.sift.JNDIBasedContextDiscriminator\">");
			randomAccessFile.writeBytes(
					"\r\n\t\t\t<defaultValue>Logging</defaultValue>\r\n\t\t</discriminator>\r\n\t\t<sift>\r\n\t\t\t<timestamp key=\"year\" datePattern=\"yyyy\"\r\n\t\t\t\ttimeReference=\"contextBirth\" />\r\n\t\t\t<timestamp key=\"month\" datePattern=\"MM\" timeReference=\"contextBirth\" />\r\n\t\t\t<timestamp key=\"date\" datePattern=\"dd\" timeReference=\"contextBirth\" />\r\n\t\t\t<appender name=\"FILE-${contextName}\"\r\n\t\t\t\tclass=\"ch.qos.logback.core.rolling.RollingFileAppender\">\r\n\t\t\t\t<file>logs\\APPLICATIONSIFT\\${year}\\${month}\\${date}\\${contextName}_%d{yyyy-MM-dd_HH_mm}.log\r\n\t\t\t\t</file>\r\n\t\t\t\t<prudent>true</prudent>\r\n\t\t\t\t<append>true</append>\r\n\r\n\t\t\t\t<encoder class=\"ch.qos.logback.core.encoder.LayoutWrappingEncoder\">\r\n\t\t\t\t\t<layout class=\""
							+ packageName
							+ ".logback.LogbackTemplate\" />\r\n\t\t\t\t</encoder>\r\n\t\t\t\t<rollingPolicy\r\n\t\t\t\t\tclass=\"ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy\">\r\n\t\t\t\t\t<maxFileSize>100KB</maxFileSize>\r\n\t\t\t\t\t<fileNamePattern>logs\\APPLICATIONSIFT\\${year}\\${month}\\${date}\\${contextName}_%d{yyyy-MM-dd_HH_mm}.%i.log\r\n\t\t\t\t\t</fileNamePattern>\r\n\t\t\t\t</rollingPolicy>\r\n\t\t\t</appender>\r\n\t\t</sift>\r\n\t</appender>\r\n\r\n\t<appender name=\"HTMLLayout\" class=\"ch.qos.logback.core.FileAppender\">\r\n\t\t<encoder class=\"ch.qos.logback.core.encoder.LayoutWrappingEncoder\">\r\n\t\t\t<layout class=\"ch.qos.logback.classic.html.HTMLLayout\">\r\n\t\t\t\t<pattern>%d{HH:mm:ss.SSS}%relative%thread%mdc%level%logger%line%msg\r\n\t\t\t\t</pattern>\r\n\t\t\t</layout>\r\n\t\t</encoder>\r\n\t\t<file>logs\\HTMLLayout\\HTMLViewlog.html</file>\r\n\t</appender>\r\n\r\n\t<root level=\"INFO\">\r\n\t\t<appender-ref ref=\"SizeAndTime\" />\r\n\t\t<appender-ref ref=\"APPLICATIONSIFT\" />\r\n\t\t<appender-ref ref=\"HTMLLayout\" />\r\n\t</root>\r\n</configuration>");

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

package com.acg.exceptions;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acg.adapters.ACGAdapter;
import com.acg.constants.ACGConstants;

public final class AnonymousException extends ACGAdapter {

	static Logger logger = LoggerFactory.getLogger(AnonymousException.class);

	@Override
	public void create(String fileStructure, String packageName) {

		File file = new File(fileStructure + "/exceptions");
		if (!file.exists())
			file.mkdirs();

		RandomAccessFile randomAccessFile = null;
		try {
			randomAccessFile = new RandomAccessFile(file + "/DAOException.java", "rw");
			randomAccessFile.writeBytes("package " + packageName + ".exceptions;");
			randomAccessFile.writeBytes(ACGConstants.TWO_LINE_SEPARATOR);
			randomAccessFile.writeBytes("public class DAOException extends Exception {");
			randomAccessFile.writeBytes(ACGConstants.TWO_LINE_ONE_TAB_SEPARATOR);
			randomAccessFile.writeBytes("private static final long serialVersionUID = 91695585441212747L;");
			randomAccessFile.writeBytes(ACGConstants.TWO_LINE_ONE_TAB_SEPARATOR);
			randomAccessFile.writeBytes(
					"public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_TWO_TAB_SEPARATOR);
			randomAccessFile.writeBytes("super(message, cause, enableSuppression, writableStackTrace);");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_ONE_TAB_SEPARATOR);
			randomAccessFile.writeBytes("}");
			randomAccessFile.writeBytes(ACGConstants.TWO_LINE_ONE_TAB_SEPARATOR);
			randomAccessFile.writeBytes("public DAOException(String message, Throwable cause) {");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_TWO_TAB_SEPARATOR);
			randomAccessFile.writeBytes("super(message, cause);");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_ONE_TAB_SEPARATOR);
			randomAccessFile.writeBytes("}");
			randomAccessFile.writeBytes(ACGConstants.TWO_LINE_ONE_TAB_SEPARATOR);
			randomAccessFile.writeBytes("public DAOException(String message) {");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_TWO_TAB_SEPARATOR);
			randomAccessFile.writeBytes("super(message);");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_ONE_TAB_SEPARATOR);
			randomAccessFile.writeBytes("}");
			randomAccessFile.writeBytes(ACGConstants.TWO_LINE_ONE_TAB_SEPARATOR);
			randomAccessFile.writeBytes("public DAOException(Throwable cause) {");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_TWO_TAB_SEPARATOR);
			randomAccessFile.writeBytes("super(cause);");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_ONE_TAB_SEPARATOR);
			randomAccessFile.writeBytes("}");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_SEPARATOR);
			randomAccessFile.writeBytes("}");
			randomAccessFile.writeBytes(ACGConstants.ONE_LINE_SEPARATOR);
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

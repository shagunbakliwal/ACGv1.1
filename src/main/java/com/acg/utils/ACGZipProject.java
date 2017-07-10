package com.acg.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acg.adapters.ACGAdapter;

public final class ACGZipProject extends ACGAdapter{

	static Logger logger = LoggerFactory.getLogger(ACGZipProject.class);

	static List<String> filesListInDir = new ArrayList<String>();

	private static void zipDirectory(File dir, String zipDirName,boolean delete) {
		System.out.println(dir.getAbsolutePath());
		try {
			populateFilesList(dir);
			FileOutputStream fos = new FileOutputStream(zipDirName);
			ZipOutputStream zos = new ZipOutputStream(fos);
			for (String filePath : filesListInDir) {
				ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length() + 1, filePath.length()));
				zos.putNextEntry(ze);
				FileInputStream fis = new FileInputStream(filePath);
				byte[] buffer = new byte[1024];
				int len;
				while ((len = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				zos.closeEntry();
				fis.close();
			}
			zos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void populateFilesList(File dir) throws IOException {
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile())
				filesListInDir.add(file.getAbsolutePath());
			else
				populateFilesList(file);
		}
	}

	@Override
	public void create(String fileStructure, String databaseName) {
		File dir = new File(fileStructure + "\\" + databaseName);
		String zipDirName = fileStructure + "\\" + databaseName + ".zip";
		zipDirectory(dir, zipDirName,true);
	}
}

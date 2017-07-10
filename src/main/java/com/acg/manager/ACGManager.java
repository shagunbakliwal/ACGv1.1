package com.acg.manager;

import java.io.File;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.acg.config.ACGConfiguration;
import com.acg.connection.ACGConnection;
import com.acg.exceptions.AnonymousException;
import com.acg.logback.AnonymousLogbackTemplate;
import com.acg.logback.AnonymousLogbackXML;
import com.acg.utils.ACGZipProject;

@Configuration
public class ACGManager {
	static Logger logger = LoggerFactory.getLogger(ACGManager.class);

	@Autowired
	ACGConfiguration acgConfiguration;

	@Autowired
	ACGConnection acgConnection;
	@Value("${directory}")
	private String directory;

	public void createException() {
		String fileStructure = directory + "\\" + acgConfiguration.databaseName + "\\src\\main\\java\\"
				+ acgConfiguration.fileStructure;
		new AnonymousException().create(fileStructure, acgConfiguration.packageName);
	}

	public void createLogback() {
		String fileStructure = directory + "\\" + acgConfiguration.databaseName + "\\src\\main\\resources";
		new AnonymousLogbackXML().create(fileStructure, acgConfiguration.packageName);
	}

	public void createLogbackXML() {
		String fileStructure = directory + "\\" + acgConfiguration.databaseName + "\\src\\main\\java\\"
				+ acgConfiguration.fileStructure;
		new AnonymousLogbackTemplate().create(fileStructure, acgConfiguration.packageName);
	}

	public File createProject(String fileName) throws FileNotFoundException {
		flushDirectory(fileName);
		createException();
		createLogback();
		createLogbackXML();
		createConnection();
		return createZip(fileName);

	}

	private void createConnection() {
		String fileStructure = directory + "\\" + acgConfiguration.databaseName + "\\src\\main\\java\\"
				+ acgConfiguration.fileStructure;
		acgConnection.create(fileStructure, acgConfiguration.packageName);
	}

	private void flushDirectory(String fileName) {
		File file = new File(directory+"\\"+fileName);
		file.delete();
	}

	private File createZip(String fileName) throws FileNotFoundException {
		new ACGZipProject().create(directory, acgConfiguration.databaseName);
		return new File(directory + "\\" + fileName);
	}

}

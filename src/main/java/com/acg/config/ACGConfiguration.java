package com.acg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ACGConfiguration {
	@Value("${database.name}")
	public String databaseName;

	@Value("${package.name}")
	public String packageName;

	@Value("${fileStructure.name}")
	public String fileStructure;

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

}

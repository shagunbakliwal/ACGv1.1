package com.acg.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.acg.adapters.ACGAdapter;
import com.acg.constants.ACGConstants;

@Configuration
public class ACGConnection extends ACGAdapter {

	static Logger logger = LoggerFactory.getLogger(ACGConnection.class);
	@Value("${rdbms.name}")
	private String rdbms;

	@Override
	public void create(String fileStructure, String packageName) {
		if(rdbms.equals(ACGConstants.POSTGRES)){
			
		}
		
		if(rdbms.equalsIgnoreCase(ACGConstants.MONGODB)){

		}
		super.create(fileStructure, packageName);
	}

}

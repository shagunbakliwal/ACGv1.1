package com.acg.controller;

import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acg.connection.ACGConnection;
import com.acg.manager.ACGManager;

@RestController
public class ACGController {

	static Logger logger = LoggerFactory.getLogger(ACGController.class);

	@Autowired
	ACGManager acgManager;

	@RequestMapping(value = "/createException")
	public void createException() {
		acgManager.createException();
	}

	@RequestMapping(value = "/createLogbackXML")
	public void createLogbackXML() {
		acgManager.createLogbackXML();
	}

	@RequestMapping(value = "/createLogback")
	public void createLogback() {
		acgManager.createLogback();
	}

	@RequestMapping(value = "/createProject/{fileName}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> downloadStuff(@PathVariable String fileName) throws IOException {
		fileName += ".zip";
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentDispositionFormData("attachment", fileName);
		InputStreamResource isr = new InputStreamResource(new FileInputStream(acgManager.createProject(fileName)));
		logger.info("Project Zip ready to download.");
		return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
	}
}

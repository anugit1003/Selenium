package com.tests.util.automation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {
	private FileInputStream stream = null;
	private Properties propFile = null;

	public Properties loadFile(String filename) {

		propFile = new Properties();
		String propertyFilePath = null;
		switch (filename) {
		case "applicationDataProperties":
			propertyFilePath = Constants.APPLICATION_PROPERTIES;
			break;
		}
		try {
			stream = new FileInputStream(propertyFilePath);
			propFile.load(stream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return propFile;
	}

	public String getPropertyValue(String key) {
		String value = propFile.getProperty(key);
		return value;
	}

}

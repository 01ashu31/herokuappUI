package com.herokuappUI.datahelpers;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class PropertiesReader {
	public Properties readPropertyFile(String filePath) {
		FileReader fileReader;
		Properties properties;
		try {
			fileReader = new FileReader(new File(filePath));
			properties = new Properties();
			properties.load(fileReader);
		} catch (Exception e) {
			return null;
		}
		return properties;
	}

	public Properties readPropertyFile() throws Exception {
		if (System.getProperty("env") == null || System.getProperty("env").equalsIgnoreCase("staging")) {
			FileReader fileReader = new FileReader(
					new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
							+ File.separator + "resources" + File.separator + "environment.properties"));
			Properties properties = new Properties();
			properties.load(fileReader);
			return properties;
		} else if (System.getProperty("env") != null) {
			FileReader fileReader = new FileReader(new File(
					System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
							+ "resources" + File.separator + System.getProperty("env").toLowerCase() + ".properties"));
			Properties properties = new Properties();
			properties.load(fileReader);
			return properties;
		}
		FileReader fileReader = new FileReader(new File(System.getProperty("user.dir") + File.separator + "src"
				+ File.separator + "main" + File.separator + "resources" + File.separator + "environment.properties"));
		Properties properties = new Properties();
		properties.load(fileReader);
		return properties;
	}


}

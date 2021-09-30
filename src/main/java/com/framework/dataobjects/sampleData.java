package com.framework.dataobjects;

import java.io.FileInputStream;
import java.util.Properties;

public class sampleData {

	public static Properties properties;
	public static FileInputStream fileInputStream;

	public static class LoginDetails {

		public static String getEmailAddress() throws Exception {
			// Fetch data from config.properties
			fileInputStream = new FileInputStream(System.getProperty("user.dir").replace("\\", "/")
					+ "/src/main/resources/AppConfig/config.properties");
			properties = new Properties();
			properties.load(fileInputStream);

			String emailAddress = properties.getProperty("email").toString();
			return emailAddress;
		}
	}
}

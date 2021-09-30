package com.framework.core;

public class ConfigFileReader {

	@SuppressWarnings("unused")
	public String getReportConfigPath() {
		String reportConfigPath = System.getProperty("user.dir").replace("\\", "/")
				+ "/src/main/resources/Config/extent-config.xml";
		if (reportConfigPath != null)
			return reportConfigPath;
		else
			throw new RuntimeException("No config found!");
	}

}

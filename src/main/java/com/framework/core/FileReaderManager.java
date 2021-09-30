package com.framework.core;

public class FileReaderManager {

	private static ConfigFileReader configFileReader;
	private static FileReaderManager fileReaderManager = new FileReaderManager();

	public static FileReaderManager getInstance() {
		return fileReaderManager;
	}

	private FileReaderManager() {
	}

	public ConfigFileReader getConfigReader() {
		return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
	}

}

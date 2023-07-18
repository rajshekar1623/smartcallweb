package com.aakhya.smartcall.application.integration.service;

import com.aakhya.smartcall.application.integration.entity.IntegrationFileFormatType;

public class FileReaderFactory {

	public static FileReader getFileReader(IntegrationFileFormatType fileFormatType) {
		FileReader fileReader = null;
		if(fileFormatType.equals(IntegrationFileFormatType.FIXED_LENGTH_TXT))
			fileReader = new FixedLengthTextFileReader();
		else if(fileFormatType.equals(IntegrationFileFormatType.DILIMITED_TXT))
			fileReader = new DilimitedTextFileReader();
		else if(fileFormatType.equals(IntegrationFileFormatType.XML_FILE))
			fileReader = new XmlFileReader();
		else if(fileFormatType.equals(IntegrationFileFormatType.JSON_FILE))
			fileReader = new JSonFileReader();
		return fileReader;
	}
}

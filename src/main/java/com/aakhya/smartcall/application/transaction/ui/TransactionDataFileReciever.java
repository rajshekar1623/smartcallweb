package com.aakhya.smartcall.application.transaction.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.vaadin.flow.component.upload.Receiver;

public class TransactionDataFileReciever implements Receiver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -825069855050661447L;
	public static String BASE_PATH = "D:\\documentmanagement\\smartcall\\";
    private File file;
    private String filename;
    
	@Override
	public OutputStream receiveUpload(String fileName, String mimeType) {
		 // Create upload stream
        FileOutputStream fos = null; // Stream to write to
        try {
            // Open the file for writing.
            this.filename = TransactionDataFileReciever.BASE_PATH + filename;

            file = new File(this.filename);
            fos = new FileOutputStream(file);
        } catch (final java.io.FileNotFoundException e) {
            return null;
        }
        return fos; // Return the output stream to write to
	}
	
	public String getFilename() {
        return filename;
    }

}
package com.aakhya.smartcall.application.integration.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aakhya.smartcall.application.integration.entity.IntegrationFileFormatDetail;
import com.aakhya.smartcall.application.integration.entity.IntegrationMaster;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;

public class DilimitedTextFileReader implements FileReader{

	@Override
	public boolean checkFile(IntegrationMaster im, List<String> linesFromFile) {
		boolean isValid = true;
		if(null != im.getIntegrationFileFormatDetails() && !im.getIntegrationFileFormatDetails().isEmpty()) {
			if(null != linesFromFile && !linesFromFile.isEmpty()) {
				for(String lineFromFile:linesFromFile) {
					String[] columnsFromFile = lineFromFile.split(im.getDelimiter());
					if(im.getIntegrationFileFormatDetails().size() != columnsFromFile.length)
						isValid = false;
				}
			}
		}
		return isValid;
	}

	@Override
	public List<TransactionDataSet> processFile(IntegrationMaster im, List<String> linesFromFile) {
		List<TransactionDataSet> transactionDataSets = new ArrayList<TransactionDataSet>();
		if(null != im.getIntegrationFileFormatDetails() && !im.getIntegrationFileFormatDetails().isEmpty()) {
			Map<Integer,IntegrationFileFormatDetail> integrationFileFormats = new HashMap<Integer,IntegrationFileFormatDetail>();
			for(IntegrationFileFormatDetail ifm:im.getIntegrationFileFormatDetails()) {
				integrationFileFormats.put(ifm.getColumnSequence(), ifm);
			}
			if(null != linesFromFile && !linesFromFile.isEmpty()) {
				for(String lineFromFile:linesFromFile) {
					TransactionDataSet transactionDataSet = new TransactionDataSet();
					String[] columnsFromFile = lineFromFile.split(im.getDelimiter());
					for(int i=0;i<columnsFromFile.length;i++) {
						Object columnValue = columnsFromFile[i];
						IntegrationFileFormatDetail ifm = integrationFileFormats.get(i+1);
						try {
							Field f = transactionDataSet.getClass().getDeclaredField(ifm.getMappingColumn());
							f.set(transactionDataSet, columnValue);
							transactionDataSets.add(transactionDataSet);
						} catch (NoSuchFieldException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}		
		return transactionDataSets;
	}

}

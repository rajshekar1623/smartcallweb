package com.aakhya.smartcall.application.integration.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aakhya.smartcall.application.integration.entity.IntegrationFileFormatDetail;
import com.aakhya.smartcall.application.integration.entity.IntegrationMaster;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;

public class FixedLengthTextFileReader implements FileReader{

	public boolean checkFile(IntegrationMaster im,List<String> linesFromFile) {
		boolean isValid = true;
		if(null != im.getIntegrationFileFormatDetails() && !im.getIntegrationFileFormatDetails().isEmpty()) {
			Integer totalLength = 0;
			for(IntegrationFileFormatDetail fileFormatDetail:im.getIntegrationFileFormatDetails()) {
				int startIndex = fileFormatDetail.getStartIndex();
				int endIndex = fileFormatDetail.getEndIndex();
				int columnLength = endIndex - startIndex + 1;
				totalLength = totalLength+columnLength;
			}
			if(null != linesFromFile && !linesFromFile.isEmpty()) {
				for(String lineFromFile:linesFromFile) {
					if(lineFromFile.length() != totalLength)
						isValid = false;
				}
			}else
				isValid = false;
		}else
			isValid = false;
		return isValid;
	}
	
	public List<TransactionDataSet> processFile(IntegrationMaster im,List<String> linesFromFile) {
		List<TransactionDataSet> transactionDataSets = new ArrayList<TransactionDataSet>();
		if(null != im.getIntegrationFileFormatDetails() && !im.getIntegrationFileFormatDetails().isEmpty()) {
			Map<Integer, IntegrationFileFormatDetail> orderedFileFormatDetails = new HashMap<Integer, IntegrationFileFormatDetail>();
			for(IntegrationFileFormatDetail fileFormatDetail:im.getIntegrationFileFormatDetails()) {
				orderedFileFormatDetails.put(fileFormatDetail.getColumnSequence(), fileFormatDetail);
			}
			int colSeq = 1;
			for(String lineFromFile:linesFromFile) {
				TransactionDataSet transactionDataSet = new TransactionDataSet();
				IntegrationFileFormatDetail detailForColumn = orderedFileFormatDetails.get(colSeq);
				Object columnValue = lineFromFile.substring(detailForColumn.getStartIndex(), detailForColumn.getEndIndex());
				String columnName = detailForColumn.getMappingColumn();
				try {
					Field f = transactionDataSet.getClass().getDeclaredField(columnName);
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
		return transactionDataSets;
	}
}

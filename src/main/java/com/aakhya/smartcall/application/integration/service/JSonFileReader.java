package com.aakhya.smartcall.application.integration.service;

import java.lang.reflect.Type;
import java.util.List;

import com.aakhya.smartcall.application.integration.entity.IntegrationMaster;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JSonFileReader implements FileReader {

	@Override
	public boolean checkFile(IntegrationMaster im, List<String> linesFromFile) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<TransactionDataSet> processFile(IntegrationMaster im, List<String> linesFromFile) {
		Gson gson = new Gson();
		if(null != linesFromFile && !linesFromFile.isEmpty()) {
			StringBuffer jsonAsString = new StringBuffer();
			for(String str:linesFromFile) {
				jsonAsString.append(str);
				Type type = new TypeToken<List<TransactionDataSet>>(){}.getType();
				List<TransactionDataSet> transactionDataSets = gson.fromJson(jsonAsString.toString(), type);
				return transactionDataSets;
			}
		}
		return null;
	}

}

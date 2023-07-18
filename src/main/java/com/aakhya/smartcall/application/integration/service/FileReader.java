package com.aakhya.smartcall.application.integration.service;

import java.util.List;

import com.aakhya.smartcall.application.integration.entity.IntegrationMaster;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;

public interface FileReader {

	boolean checkFile(IntegrationMaster im,List<String> linesFromFile);
	List<TransactionDataSet> processFile(IntegrationMaster im,List<String> linesFromFile);
}

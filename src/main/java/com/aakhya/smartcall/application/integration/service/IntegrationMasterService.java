package com.aakhya.smartcall.application.integration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.admin.entity.EntityNameType;
import com.aakhya.smartcall.application.admin.service.SequenceService;
import com.aakhya.smartcall.application.integration.entity.IntegrationFileFormatType;
import com.aakhya.smartcall.application.integration.entity.IntegrationMaster;
import com.aakhya.smartcall.application.integration.entity.IntegrationMasterPk;
import com.aakhya.smartcall.application.integration.repository.IntegrationMasterRepository;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;
import com.aakhya.smartcall.application.transaction.data.service.TransactionDataSetService;

@Service
public class IntegrationMasterService {

	@Autowired
	private IntegrationMasterRepository integrationMasterRepository;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private TransactionDataSetService transactionDataSetService;
	
	public List<IntegrationMaster> findAllIntegrationMasters(String searchFilter){
		return integrationMasterRepository.findAll();
	}
	
	public IntegrationMaster findIntegrationMasterByPk(IntegrationMasterPk integrationMasterPk) {
		Optional<IntegrationMaster> optionalIM = integrationMasterRepository.findById(integrationMasterPk);
		if(null != optionalIM && optionalIM.isPresent()) {
			IntegrationMaster im = optionalIM.get();
			return im;
		}else
			return null;
	}
	
	public void saveIntegrationMaster(IntegrationMaster im) {
		if(null != im) {
			if(null == im.getIntegrationId() || im.getIntegrationId().equals(0L)) {
				Long integrationId = sequenceService.getNewSequence(EntityNameType.INTEGRATION_MASTER, im.getCompanyId());
				im.setIntegrationId(integrationId);
			}
		}
		integrationMasterRepository.save(im);
	}
	
	@SuppressWarnings("unused")
	public void processUploadedFile(IntegrationMaster im,List<String> linesFromFile) {
		if(null != im && null != im.getFileFormat()) {
			FileReader fileReader = null;
			if(IntegrationFileFormatType.FIXED_LENGTH_TXT.getValue().equals(im.getFileFormatType())) 
				FileReaderFactory.getFileReader(IntegrationFileFormatType.FIXED_LENGTH_TXT);
			else if(IntegrationFileFormatType.DILIMITED_TXT.getValue().equals(im.getFileFormatType())) 
				FileReaderFactory.getFileReader(IntegrationFileFormatType.DILIMITED_TXT);
			else if(IntegrationFileFormatType.XML_FILE.getValue().equals(im.getFileFormatType())) 
				FileReaderFactory.getFileReader(IntegrationFileFormatType.XML_FILE);
			else if(IntegrationFileFormatType.JSON_FILE.getValue().equals(im.getFileFormatType())) 
				FileReaderFactory.getFileReader(IntegrationFileFormatType.JSON_FILE);
				if(null != fileReader && fileReader.checkFile(im, linesFromFile)) {
					List<TransactionDataSet> transactionDataSets = fileReader.processFile(im, linesFromFile);
//					transactionDataSetService.createTransactionDataSet(transactionDataSets);
				}
			
		}
	}
	
//	private boolean checkFixedLengthTextFile(IntegrationMaster im,List<String> linesFromFile) {
//		boolean isValid = true;
//		if(null != im.getIntegrationFileFormatDetails() && !im.getIntegrationFileFormatDetails().isEmpty()) {
//			Integer totalLength = 0;
//			for(IntegrationFileFormatDetail fileFormatDetail:im.getIntegrationFileFormatDetails()) {
//				int startIndex = fileFormatDetail.getStartIndex();
//				int endIndex = fileFormatDetail.getEndIndex();
//				int columnLength = endIndex - startIndex + 1;
//				totalLength = totalLength+columnLength;
//			}
//			if(null != linesFromFile && !linesFromFile.isEmpty()) {
//				for(String lineFromFile:linesFromFile) {
//					if(lineFromFile.length() != totalLength)
//						isValid = false;
//				}
//			}else
//				isValid = false;
//		}else
//			isValid = false;
//		return isValid;
//	}
//	
//	private List<TransactionDataSet> processFixedLengthTextFile(IntegrationMaster im,List<String> linesFromFile) {
//		List<TransactionDataSet> transactionDataSets = new ArrayList<TransactionDataSet>();
//		if(null != im.getIntegrationFileFormatDetails() && !im.getIntegrationFileFormatDetails().isEmpty()) {
//			Map<Integer, IntegrationFileFormatDetail> orderedFileFormatDetails = new HashMap<Integer, IntegrationFileFormatDetail>();
//			for(IntegrationFileFormatDetail fileFormatDetail:im.getIntegrationFileFormatDetails()) {
//				orderedFileFormatDetails.put(fileFormatDetail.getColumnSequence(), fileFormatDetail);
//			}
//			int colSeq = 1;
//			for(String lineFromFile:linesFromFile) {
//				TransactionDataSet transactionDataSet = new TransactionDataSet();
//				IntegrationFileFormatDetail detailForColumn = orderedFileFormatDetails.get(colSeq);
//				Object columnValue = lineFromFile.substring(detailForColumn.getStartIndex(), detailForColumn.getEndIndex());
//				String columnName = detailForColumn.getMappingColumn();
//				try {
//					Field f = transactionDataSet.getClass().getDeclaredField(columnName);
//					f.set(transactionDataSet, columnValue);
//					transactionDataSets.add(transactionDataSet);
//				} catch (NoSuchFieldException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (SecurityException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalArgumentException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		return transactionDataSets;
//	}
}

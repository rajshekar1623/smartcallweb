package com.aakhya.smartcall.application.transaction.data.service;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aakhya.smartcall.application.admin.entity.EntityNameType;
import com.aakhya.smartcall.application.admin.entity.GenericClassifier;
import com.aakhya.smartcall.application.admin.entity.RecordStatusType;
import com.aakhya.smartcall.application.admin.service.SequenceService;
import com.aakhya.smartcall.application.integration.service.ExcelFileReader;
import com.aakhya.smartcall.application.security.entity.User;
import com.aakhya.smartcall.application.transaction.data.entity.DataSetType;
import com.aakhya.smartcall.application.transaction.data.entity.DetailViewObject;
import com.aakhya.smartcall.application.transaction.data.entity.DpdQueue;
import com.aakhya.smartcall.application.transaction.data.entity.DpdQueueList;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;
import com.aakhya.smartcall.application.transaction.data.repository.TransactionDataSetJdbcRepository;
import com.aakhya.smartcall.application.transaction.data.repository.TransactionDataSetRepository;

@Service
public class TransactionDataSetService {

	@Autowired
	private TransactionDataSetRepository transactionDataSetRepository;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private TransactionDataSetJdbcRepository jdbcRepository;

	public List<TransactionDataSet> findAllTransactionDataSet(DataSetType dataSetType) {
		return transactionDataSetRepository.findAllByDataSetType(dataSetType.getValue());
	}

	public TransactionDataSet findTransactionDataSetById(Long transactionDataSetId) {
		Optional<TransactionDataSet> optionalTDS = transactionDataSetRepository.findById(transactionDataSetId);
		if (null != optionalTDS && optionalTDS.isPresent()) {
			return optionalTDS.get();
		} else
			return null;
	}

	public void saveTransactionDataSet(TransactionDataSet transactionDataSet) {
		if (null != transactionDataSet
				&& (null == transactionDataSet.getDataSetId() || transactionDataSet.getDataSetId().equals(0L))) {
			Long dataSetId = sequenceService.getNewSequence(EntityNameType.TRANS_DATA_SET,
					transactionDataSet.getCompanyId());
			transactionDataSet.setDataSetId(dataSetId);
		}
		transactionDataSetRepository.save(transactionDataSet);
	}

	public void saveTransactionDataSet(List<TransactionDataSet> transactionDataSets) {
		if (null != transactionDataSets && !transactionDataSets.isEmpty()) {
//			int totalCount = transactionDataSets.size();
//			int processedCount = 0;
			List<TransactionDataSet> transactionDataSetsForInsert = new ArrayList<TransactionDataSet>();
			List<TransactionDataSet> transactionDataSetsForUpdate = new ArrayList<TransactionDataSet>();

			for (TransactionDataSet transactionDataSet : transactionDataSets) {
				if (null != transactionDataSet.getGenericNumber2()) {
					TransactionDataSet existingData = transactionDataSetRepository
							.findDataSetByLoanAccountNumber(transactionDataSet.getGenericNumber2());
					if (null != existingData) {
						existingData.setFirstName(transactionDataSet.getFirstName());
						existingData.setDateOfBirth(transactionDataSet.getDateOfBirth());
						existingData.setGender(transactionDataSet.getGender());
						existingData.setPanCardNumber(transactionDataSet.getPanCardNumber());
						existingData.setGenericString1(transactionDataSet.getGenericString1());
						existingData.setGenericString2(transactionDataSet.getGenericString2());
						existingData.setGenericString3(transactionDataSet.getGenericString3());
						existingData.setGenericString4(transactionDataSet.getGenericString4());
						existingData.setGenericString5(transactionDataSet.getGenericString5());
						existingData.setGenericDate1(transactionDataSet.getGenericDate1());
						existingData.setGenericDate2(transactionDataSet.getGenericDate2());
						existingData.setGenericNumber1(transactionDataSet.getGenericNumber1());
						existingData.setGenericNumber3(transactionDataSet.getGenericNumber3());
						existingData.setGenericNumber4(transactionDataSet.getGenericNumber4());
						existingData.setGenericDecimal1(transactionDataSet.getGenericDecimal1());
						existingData.setGenericDecimal2(transactionDataSet.getGenericDecimal2());
						existingData.setGenericDecimal3(transactionDataSet.getGenericDecimal3());
						existingData.setGenericDecimal4(transactionDataSet.getGenericDecimal4());
						existingData.setGenericDecimal5(transactionDataSet.getGenericDecimal5());
						existingData.setGenericDecimal6(transactionDataSet.getGenericDecimal6());
						existingData.setGenericDecimal1(transactionDataSet.getGenericDecimal7());
						existingData.setUpdatedBy("Admin");
						existingData.setUpdatedDateTime(new Date());
						transactionDataSetsForUpdate.add(existingData);
					} else {
						if (null == transactionDataSet.getDataSetId() || transactionDataSet.getDataSetId().equals(0L)) {
							Long dataSetId = sequenceService.getNewSequence(EntityNameType.TRANS_DATA_SET, 1L);
							transactionDataSet.setDataSetId(dataSetId);
							transactionDataSet.setCompanyId(1L);
							transactionDataSet.setStatus(RecordStatusType.ACTIVE.getValue());
							transactionDataSet.setCreatedBy("Admin");
							transactionDataSet.setCreatedDateTime(new Date());
							transactionDataSet.setValidFrom(new Date());
							transactionDataSet.setDataSetType(2L);
							transactionDataSet.setDataSetDescription("CUSTOMER_COLLECTION");
							try {
								transactionDataSet
										.setRemoveDateTime(new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2200"));
								transactionDataSet.setValidTo(new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2200"));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							transactionDataSetsForInsert.add(transactionDataSet);
						}

					}
//				processedCount++;
//				float processedPercentage = (float) processedCount/totalCount;
//				progressListner.accept(processedPercentage);
					if (null != transactionDataSetsForInsert && !transactionDataSetsForInsert.isEmpty())
						jdbcRepository.createTransactions(transactionDataSetsForInsert);
					if (null != transactionDataSetsForUpdate && !transactionDataSetsForUpdate.isEmpty())
						jdbcRepository.updateTransactions(transactionDataSetsForUpdate);
				}
			}
//			transactionDataSetRepository.saveAll(transactionDataSets);
		}
//		succeededLister.run();
	}

	public List<TransactionDataSet> findTransactionDataSetForStatusView(Long mobileNumber, String parentBranchCode,
			String branchCode, String assignedTo, String product, String queue, GenericClassifier dpdQueue,
			Long pincode, Long loanAccountNumber, String firstName) {
		return jdbcRepository.findTransactionDataSetForStatusView(mobileNumber, parentBranchCode, branchCode,
				assignedTo, product, loanAccountNumber, mobileNumber, pincode, loanAccountNumber, firstName);
	}

	public List<TransactionDataSet> findTransactionDataSetForAssignment(Long mobileNumber, String parentBranchCode,
			String branchCode, String assignedTo, String product, String queue, GenericClassifier dpdQueue,
			Long pincode, Long loanAccountNumber, String firstName) {
		Long queueLongValue = 0L;
		if (null != queue && "Marketing".equals(queue))
			queueLongValue = 1L;
		else if (null != queue && "NPA".equals(queue))
			queueLongValue = 2L;
		else if (null != queue && "Welcome Call".equals(queue))
			queueLongValue = 3L;
		if (null != dpdQueue) {
			System.out.println("DPD Queue in Service is :: " + dpdQueue.getGenericId());
			return jdbcRepository.findTransactionDataSetForAssignment(mobileNumber, parentBranchCode, branchCode,
					assignedTo, product, queueLongValue, dpdQueue.getGenericId(), pincode, loanAccountNumber,
					firstName);
		} else
			return jdbcRepository.findTransactionDataSetForAssignment(mobileNumber, parentBranchCode, branchCode,
					assignedTo, product, queueLongValue, null, pincode, loanAccountNumber, firstName);
	}

	public void save(TransactionDataSet transactionDataSet) {
		System.out.println("The dataSetId being saved is :: " + transactionDataSet.getDataSetId());
		System.out.println("The gender value being saved is :: " + transactionDataSet.getGender());
		transactionDataSetRepository.save(transactionDataSet);
	}

	public void delete(TransactionDataSet transactionDataSet) {
		// TODO Auto-generated method stub

	}

	public List<TransactionDataSet> uploadData(FileInputStream fis) {
		List<TransactionDataSet> transactionDataSets = new ArrayList<TransactionDataSet>();
		Long initialCount = getDataSetCount();
//		List<TemporaryTransaction> temporaryTransactions = ExcelFileReader.processDilimitedFile(fis, initialCount, this);
//		if(null != temporaryTransactions && !temporaryTransactions.isEmpty()) {
//			for(TemporaryTransaction tempTran:temporaryTransactions) {
//				TransactionDataSet dataSet = new TransactionDataSet();
//				dataSet.setFirstName(tempTran.getFirstname());
//				dataSet.setDateOfBirth(tempTran.getDob());
//				dataSet.setGender(tempTran.getGender());
//				dataSet.setReligion(tempTran.getReligion());
//				dataSet.setSocialCategory(tempTran.getSocialcategory());
//				dataSet.setVoterId(tempTran.getVoterid());
//				dataSet.setDrivingLicenseNumber(tempTran.getDrivinglicense());
////				dataSet.set(tempTran.getRationcard());
//				dataSet.setPanCardNumber(tempTran.getPancard());
////				if(null != tempTran.getGp())
////				dataSet.setGrampanchayat();
//				dataSet.setPincode(tempTran.getPincode());
////				dataSet.setVillage(tempTran.getVillage());
//				dataSet.setBranchCode(tempTran.getBranchcode());
//				dataSet.setGenericString4(tempTran.getBranchname());
////				dataSet.setFathe(tempTran.getFathersname());
//				dataSet.setGenericString2(tempTran.getProduct());
//				if(null != tempTran.getMobilenumber())
//				dataSet.setGenericNumber1(Long.valueOf(tempTran.getMobilenumber()));
//				if(null != tempTran.getLoanaccountnumber())
//				dataSet.setGenericNumber2(Long.valueOf(tempTran.getLoanaccountnumber()));
//				dataSet.setGenericNumber3(tempTran.getDpdqueue());
//				dataSet.setGenericDecimal4(tempTran.getCurrentoutstandingbalance());
//				dataSet.setGenericDecimal5(tempTran.getPrincipledue());
//				dataSet.setGenericDecimal6(tempTran.getInterestdue());
//				dataSet.setGenericDecimal7(tempTran.getInterestrate());
//				dataSet.setGenericDate1(tempTran.getLastinterestapplieddate());
//				dataSet.setGenericDate2(tempTran.getNpadate());
//				transactionDataSets.add(dataSet);
//			}
//		}
		transactionDataSets = ExcelFileReader.processDilimitedFile(fis, initialCount, this);
		return transactionDataSets;
	}

	public Long getDataSetCount() {
		return transactionDataSetRepository.queryForCount();
	}

	public boolean checkIfLoanAccountNumberExisit(Long loanAccountNumber) {
		TransactionDataSet transactionDataSet = transactionDataSetRepository
				.findDataSetByLoanAccountNumber(loanAccountNumber);
		if (null != transactionDataSet)
			return true;
		else
			return false;
	}

	public void sendSms(Map<Long, String> messages) {
		for (Long mobileNumber : messages.keySet()) {
			String url = "http://173.45.76.227/send.aspx?username=IDFFIBC&pass=IdfFibc1234&route=trans1&senderid=IDSKCC&numbers="
					+ mobileNumber + "&templateid=1207162374761377641&message=" + messages.get(mobileNumber);
			System.out.println(url);
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(url, String.class);
			System.out.println(result);
		}
	}

	public void sendWhatsApp(Map<Long, String> messages) {
		for (Long mobileNumber : messages.keySet()) {
			String url = "http://173.45.76.226/WhatsAppAPI/WhatsApp/SendURL?Username=IDFFIBC&Password=IdfFibc1234&Route=whatsappnew&MobileNumbers="
					+ mobileNumber + "&Type=U&SmsContent=" + messages.get(mobileNumber);
			System.out.println(url);
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(url, String.class);
			System.out.println(result);
		}
	}

	public void uploadDataToDb() {
	}
	
	/*
	 * All Methods below are for Mobile Application Backend
	 */
	private TransactionDataSet findTransactionDataSetByPk(Long dataSetId) {
		Optional<TransactionDataSet> optionalTds = transactionDataSetRepository.findById(dataSetId);
		if(null != optionalTds && optionalTds.isPresent()) {
			TransactionDataSet tds = optionalTds.get();
			if(null != tds.getDateOfBirth()) {
				tds.setGenericString10(new SimpleDateFormat("dd-MM-yyyy").format(tds.getDateOfBirth()));
			}
			return tds;
		}else
			return null;
	}

	public List<TransactionDataSet> findAllTransactionDataSetByUser(DataSetType dataSetType, User user) {
		return transactionDataSetRepository.findAllByDataSetTypeAndBranch(dataSetType.getValue(), user.getBranchCode());
	}

	public void createTransactionDataSet(List<TransactionDataSet> transactionDataSets) {
		if (null != transactionDataSets && !transactionDataSets.isEmpty()) {
			for (TransactionDataSet transactionDataSet : transactionDataSets) {
				saveTransactionDataSet(transactionDataSet);
			}
		}
	}
	
	public List<DpdQueue> findDpdQueus(String userId,String branchCode) {
		List<DpdQueue> dpdQueues = new ArrayList<DpdQueue>();
		DpdQueue dpdQueue30Day = jdbcRepository.getDpDQueue(userId,branchCode, 3738L);
		dpdQueues.add(dpdQueue30Day);
		DpdQueue dpdQueue60Day = jdbcRepository.getDpDQueue(userId,branchCode, 3739L);
		dpdQueues.add(dpdQueue60Day);
		DpdQueue dpdQueue90Day = jdbcRepository.getDpDQueue(userId,branchCode, 3740L);
		dpdQueues.add(dpdQueue90Day);
		DpdQueue dpdQueueAbove90 = jdbcRepository.getDpDQueue(userId,branchCode, 3750L);
		dpdQueues.add(dpdQueueAbove90);
		return dpdQueues;
	}
	
	public List<DpdQueueList> getDpdQueueList(String userId,String branchCode,Long queue){
		return jdbcRepository.getDpdQueueList(userId,branchCode, queue);
	}
	
	public List<DetailViewObject> getDetailView(Long dataSetId, Long queue) {
		TransactionDataSet transactionDataSet = findTransactionDataSetByPk(dataSetId);
//		System.out.println("Member Name is :: "+transactionDataSet.getFirstName());
//		List<DetailViewObject> detailViewObjects = transactionDataSetJdbcRepository.getDetailView(transactionDataSet, queue);
//		if(null != detailViewObjects && !detailViewObjects.isEmpty()) {
//			for(DetailViewObject detailViewObject:detailViewObjects) {
//				System.out.println("******** [Key = "+detailViewObject.getLable()+"][Value = "+detailViewObject.getValue()+"]");
//			}
//		}else {
//			System.out.println("Detail View Objects cannot be retrieved");
//		}
		return jdbcRepository.getDetailView(transactionDataSet, queue);
	}
	
	public List<DpdQueueList> getVisitList(String userId,String branchCode) {
		return jdbcRepository.getVisitList(userId, branchCode);
	}
	public List<DpdQueueList> getCallList(String userId,String branchCode) {
		return jdbcRepository.getCallList(userId, branchCode);
	}
	
	public String updateLocation(Long dataSetId,String lat,String lon,String dist) {
		Optional<TransactionDataSet> otds = transactionDataSetRepository.findById(dataSetId);
		if(null != otds && otds.isPresent()) {
			TransactionDataSet tds = otds.get();
			if(null != lat && null != lon && null != dist) {
				try {
					Double latDouble = Double.valueOf(lat);
					BigDecimal lattitude = BigDecimal.valueOf(latDouble);
					Double lonDouble = Double.valueOf(lon);
					BigDecimal longitude = BigDecimal.valueOf(lonDouble);
					Double distDouble = Double.valueOf(dist);
					BigDecimal distance = BigDecimal.valueOf(distDouble);
					tds.setGenericDecimal10(lattitude);
					tds.setGenericDecimal11(longitude);
					tds.setGenericDecimal12(distance);
					transactionDataSetRepository.save(tds);
					return "SUCCESS";
				}catch(NumberFormatException e) {
					return "Invalid Data";
				}
			}else
				return "Invalid Data";
		}else
			return "Invalid DataSetId";
	}
}

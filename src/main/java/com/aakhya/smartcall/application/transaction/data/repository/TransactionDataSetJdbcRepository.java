package com.aakhya.smartcall.application.transaction.data.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;

@Repository
public class TransactionDataSetJdbcRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<TransactionDataSet> findTransactionDataSetForAssignment(Long mobileNumber,
			String parentBranchCode,String branchCode,String assignedTo,String product,
			Long queue,Long dpdQueue,Long pincode,Long loanAccountNumber,
			String firstName){
		List<TransactionDataSet> transactionDataSetForAssignment = new ArrayList<TransactionDataSet>();
		StringBuffer query = new StringBuffer();
		query.append("select dataSetId,companyId,dataSetType,dataSetDescription,firstName,middleName,");
		query.append(" lastName,dateOfBirth,gender,religion,socialCategory,aadhaarNumber,");
		query.append(" voterId,drivingLicenseNumber,panCardNumber,gramPanchayat,village,");
		query.append(" branchCode,genericString1,genericString2,genericString3,genericString4,");
		query.append("genericString5,genericString6,genericString7,	genericString8,	genericString9,	genericString10,");
		query.append("genericString11,genericString12,genericString13,genericString14,");
		query.append("genericString15,genericString16,genericString17,genericString18,");
		query.append("genericString19,genericString20,genericString21,genericString22,");
		query.append("genericString23,genericString24,genericString25,genericString26,");
		query.append("genericString27,genericString28,genericString29,genericString30,");
		query.append("genericString31,genericString32,genericString33,genericString35,");
		query.append("genericString36,genericString37,genericString38,genericString39,");
		query.append("genericString40,genericString41,genericString42,genericString43,");
		query.append("genericString44,genericString45,genericString46,genericString47,");
		query.append("genericString48,genericString49,genericString50,genericNumber1,");
		query.append("genericNumber2,genericNumber3,genericNumber4,genericNumber5,");
		query.append("genericNumber6,genericNumber7,genericNumber8,genericNumber9,");
		query.append("genericNumber10,genericNumber11,genericNumber12,genericNumber13,");
		query.append("genericNumber14,genericNumber15,genericNumber16,genericNumber17,");
		query.append("genericNumber18,genericNumber19,genericNumber20,genericNumber21,");
		query.append("genericNumber22,genericNumber23,genericNumber24,genericNumber25,");
		query.append("genericNumber26,genericNumber27,genericNumber28,genericNumber29,");
		query.append("genericNumber30,genericNumber31,genericNumber32,genericNumber33,");
		query.append("genericNumber34,genericNumber35,genericNumber36,genericNumber37,");
		query.append("genericNumber38,genericNumber39,genericNumber40,genericNumber41,");
		query.append("genericNumber42,genericNumber43,genericNumber44,genericNumber45,");
		query.append("genericNumber46,genericNumber47,genericNumber48,genericNumber49,");
		query.append("genericNumber50,genericDecimal1,genericDecimal2,genericDecimal3,");
		query.append("genericDecimal4,genericDecimal5,genericDecimal6,genericDecimal7,");
		query.append("genericDecimal8,genericDecimal9,genericDecimal10,genericDecimal11,");
		query.append("genericDecimal12,genericDecimal13,genericDecimal14,genericDecimal15,");
		query.append("genericDecimal16,genericDecimal17,genericDecimal18,genericDecimal19,");
		query.append("genericDecimal20,genericDecimal21,genericDecimal22,genericDecimal23,");
		query.append("genericDecimal24,genericDecimal25,genericDecimal26,genericDecimal27,");
		query.append("genericDecimal28,genericDecimal29,genericDecimal30,genericDecimal31,");
		query.append("genericDecimal32,genericDecimal33,genericDecimal34,genericDecimal35,");
		query.append("genericDecimal36,genericDecimal37,genericDecimal38,genericDecimal39,");
		query.append("genericDecimal40,genericDecimal41,genericDecimal42,genericDecimal43,");
		query.append("genericDecimal44,genericDecimal45,genericDecimal46,genericDecimal47,");
		query.append("genericDecimal48,genericDecimal49,genericDecimal50,genericDate1,genericDate2,genericDate3,genericDate4,genericDate5,genericDate6,genericDate7,");
		query.append("genericDate8,genericDate9,genericDate10,genericDate11,genericDate12,genericDate13,genericDate14,genericDate15,genericDate16,genericDate17,");
		query.append("genericDate18,genericDate19,genericDate20,genericDate21,genericDate22,genericDate23,genericDate24,genericDate25,genericDate26,genericDate27,");
		query.append("genericDate28,genericDate29,genericDate30,genericDate31,genericDate32,genericDate33,genericDate34,genericDate35,genericDate36,genericDate37,");
		query.append("genericDate38,genericDate39,genericDate40,genericDate41,genericDate42,genericDate43,genericDate44,genericDate45,genericDate46,genericDate47,");
		query.append("genericDate48,genericDate49,genericDate50,actionType,scheduledDatetime,genericString34,status,createdBy,createdDateTime,");
		if(null != assignedTo && assignedTo.length() > 0) {
			query.append("'Assigned' as actionStatus,");
		}else
			query.append("'Not Assigned' as actionStatus,");
		query.append("updatedBy,updatedDateTime,removeDateTime,validFrom,validTo ");
		query.append(" from sc_transactionDataSet trn where 1 = 1 ");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(null != mobileNumber && !mobileNumber.equals(0L)){
			query.append(" and genericNumber1 = :mobileNumber");
			paramMap.put("mobileNumber", mobileNumber);
		}
		if(null != pincode && !pincode.equals(0L)) {
			query.append(" and genericNumber4 = :pincode");
			paramMap.put("pincode", pincode);
		}
		if(null != loanAccountNumber && !loanAccountNumber.equals(0L)) {
			query.append(" and genericNumber2 = :loanAccountNumber");
			paramMap.put("loanAccountNumber", loanAccountNumber);
		}
		if(null != firstName && firstName.length() > 0) {
			query.append(" and firstName like ('%"+firstName+"%')");
//			paramMap.put("firstName", firstName);
		}
		if(null != parentBranchCode && !parentBranchCode.isEmpty()){
			query.append(" and branchCode in (select branchCode from sc_branch where parentbranch = :parentBranchCode)");
			paramMap.put("parentBranchCode", parentBranchCode);
		}
		if(null != branchCode && branchCode.length() > 0){
			query.append(" and branchCode = :branchCode");
			paramMap.put("branchCode", branchCode);
		}
		if(null != product && product.length() > 0){
			query.append(" and genericString2 = :product");
			paramMap.put("product", product);
		}
		if(null != queue && !queue.equals(0L)) {
			query.append(" and dataSetType = :queue");
			paramMap.put("queue", queue);
		}
		if(null != dpdQueue && !dpdQueue.equals(0L)) {
			query.append(" and genericNumber3 = :dpdQueue");
			paramMap.put("dpdQueue", dpdQueue);
		}
		if(null != assignedTo && assignedTo.length() > 0) {
			query.append(" and dataSetId in (select dataSetId from sc_activity where userId = :assignedTo and activityStatus in ('PENDING','IN-PROCESS'))");
			paramMap.put("assignedTo", assignedTo);
		}else {
			query.append(" and dataSetId not in (select dataSetId from sc_activity )");
		}
		System.out.println(query);
		transactionDataSetForAssignment = namedParameterJdbcTemplate.query(query.toString(),
				paramMap,new TransactionDataSetRowMapper());
		return transactionDataSetForAssignment;
	}
	
	public void saveAllTransactions(List<TransactionDataSet> transactionDataSets) {
//		namedParameterJdbcTemplate.b
	}
	
}

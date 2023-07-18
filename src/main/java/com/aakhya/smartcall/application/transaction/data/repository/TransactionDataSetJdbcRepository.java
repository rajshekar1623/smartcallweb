package com.aakhya.smartcall.application.transaction.data.repository;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;

@Repository
public class TransactionDataSetJdbcRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<TransactionDataSet> findTransactionDataSetForAssignment(Long mobileNumber, String parentBranchCode,
			String branchCode, String assignedTo, String product, Long queue, Long dpdQueue, Long pincode,
			Long loanAccountNumber, String firstName) {
		List<TransactionDataSet> transactionDataSetForAssignment = new ArrayList<TransactionDataSet>();
		StringBuffer query = new StringBuffer();
		query.append("select trn.dataSetId,trn.companyId,dataSetType,dataSetDescription,firstName,middleName,");
		query.append(" lastName,dateOfBirth,gender,dbo.fn_getGenClass(gender,trn.companyId) genderStr,");
		query.append(" religion,dbo.fn_getGenClass(religion,trn.companyId) religionStr,socialCategory,");
		query.append(" dbo.fn_getGenClass(religion,trn.companyId) socialCategoryStr,aadhaarNumber,");
		query.append(" voterId,drivingLicenseNumber,panCardNumber,gramPanchayat,village,");
		query.append(" trn.branchCode,genericString1,genericString2,genericString3,genericString4,");
		query.append(
				"trn.genericString5,trn.genericString6,trn.genericString7,	trn.genericString8,	trn.genericString9,	trn.genericString10,");
		query.append("trn.genericString11,trn.genericString12,trn.genericString13,trn.genericString14,");
		query.append("trn.genericString15,trn.genericString16,trn.genericString17,trn.genericString18,");
		query.append("trn.genericString19,trn.genericString20,trn.genericString21,trn.genericString22,");
		query.append("trn.genericString23,trn.genericString24,trn.genericString25,trn.genericString26,");
		query.append("trn.genericString27,trn.genericString28,trn.genericString29,trn.genericString30,");
		query.append("trn.genericString31,trn.genericString32,trn.genericString33,trn.genericString35,");
		query.append("trn.genericString36,trn.genericString37,trn.genericString38,trn.genericString39,");
		query.append("trn.genericString40,trn.genericString41,trn.genericString42,trn.genericString43,");
		query.append("trn.genericString44,trn.genericString45,trn.genericString46,trn.genericString47,");
		query.append("trn.genericString48,trn.genericString49,trn.genericString50,trn.genericNumber1,");
		query.append("trn.genericNumber2,trn.genericNumber3,trn.genericNumber4,trn.genericNumber5,");
		query.append("trn.genericNumber6,trn.genericNumber7,trn.genericNumber8,trn.genericNumber9,");
		query.append("trn.genericNumber10,trn.genericNumber11,trn.genericNumber12,trn.genericNumber13,");
		query.append("trn.genericNumber14,trn.genericNumber15,trn.genericNumber16,trn.genericNumber17,");
		query.append("trn.genericNumber18,trn.genericNumber19,trn.genericNumber20,trn.genericNumber21,");
		query.append("trn.genericNumber22,trn.genericNumber23,trn.genericNumber24,trn.genericNumber25,");
		query.append("trn.genericNumber26,trn.genericNumber27,trn.genericNumber28,trn.genericNumber29,");
		query.append("trn.genericNumber30,trn.genericNumber31,trn.genericNumber32,trn.genericNumber33,");
		query.append("trn.genericNumber34,trn.genericNumber35,trn.genericNumber36,trn.genericNumber37,");
		query.append("trn.genericNumber38,trn.genericNumber39,trn.genericNumber40,trn.genericNumber41,");
		query.append("trn.genericNumber42,trn.genericNumber43,trn.genericNumber44,trn.genericNumber45,");
		query.append("trn.genericNumber46,trn.genericNumber47,trn.genericNumber48,trn.genericNumber49,");
		query.append("trn.genericNumber50,trn.genericDecimal1,trn.genericDecimal2,trn.genericDecimal3,");
		query.append("trn.genericDecimal4,trn.genericDecimal5,trn.genericDecimal6,trn.genericDecimal7,");
		query.append("trn.genericDecimal8,trn.genericDecimal9,trn.genericDecimal10,trn.genericDecimal11,");
		query.append("trn.genericDecimal12,trn.genericDecimal13,trn.genericDecimal14,trn.genericDecimal15,");
		query.append("trn.genericDecimal16,trn.genericDecimal17,trn.genericDecimal18,trn.genericDecimal19,");
		query.append("trn.genericDecimal20,trn.genericDecimal21,trn.genericDecimal22,trn.genericDecimal23,");
		query.append("trn.genericDecimal24,trn.genericDecimal25,trn.genericDecimal26,trn.genericDecimal27,");
		query.append("trn.genericDecimal28,trn.genericDecimal29,trn.genericDecimal30,trn.genericDecimal31,");
		query.append("trn.genericDecimal32,trn.genericDecimal33,trn.genericDecimal34,trn.genericDecimal35,");
		query.append("trn.genericDecimal36,trn.genericDecimal37,trn.genericDecimal38,trn.genericDecimal39,");
		query.append("trn.genericDecimal40,trn.genericDecimal41,trn.genericDecimal42,trn.genericDecimal43,");
		query.append("trn.genericDecimal44,trn.genericDecimal45,trn.genericDecimal46,trn.genericDecimal47,");
		query.append(
				"trn.genericDecimal48,trn.genericDecimal49,trn.genericDecimal50,trn.genericDate1,trn.genericDate2,trn.genericDate3,trn.genericDate4,trn.genericDate5,trn.genericDate6,trn.genericDate7,");
		query.append(
				"trn.genericDate8,trn.genericDate9,trn.genericDate10,trn.genericDate11,trn.genericDate12,trn.genericDate13,trn.genericDate14,trn.genericDate15,trn.genericDate16,trn.genericDate17,");
		query.append(
				"trn.genericDate18,trn.genericDate19,trn.genericDate20,trn.genericDate21,trn.genericDate22,trn.genericDate23,trn.genericDate24,trn.genericDate25,trn.genericDate26,trn.genericDate27,");
		query.append(
				"trn.genericDate28,trn.genericDate29,trn.genericDate30,trn.genericDate31,trn.genericDate32,trn.genericDate33,trn.genericDate34,trn.genericDate35,trn.genericDate36,trn.genericDate37,");
		query.append(
				"trn.genericDate38,trn.genericDate39,trn.genericDate40,trn.genericDate41,trn.genericDate42,trn.genericDate43,trn.genericDate44,trn.genericDate45,trn.genericDate46,trn.genericDate47,");
		query.append(
				"trn.genericDate48,trn.genericDate49,trn.genericDate50,actionType,scheduledDatetime,trn.genericString34,trn.status,trn.createdBy,trn.createdDateTime,getDate() activityDateTime,null userName,");
		if (null != assignedTo && assignedTo.length() > 0) {
			query.append("'Assigned' as actionStatus,");
		} else
			query.append("'Not Assigned' as actionStatus,");
		query.append("updatedBy,updatedDateTime,removeDateTime,validFrom,validTo ");
		query.append(" from sc_transactionDataSet trn where 1 = 1 ");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (null != mobileNumber && !mobileNumber.equals(0L)) {
			query.append(" and genericNumber1 = :mobileNumber");
			paramMap.put("mobileNumber", mobileNumber);
		}
		if (null != pincode && !pincode.equals(0L)) {
			query.append(" and genericNumber4 = :pincode");
			paramMap.put("pincode", pincode);
		}
		if (null != loanAccountNumber && !loanAccountNumber.equals(0L)) {
			query.append(" and genericNumber2 = :loanAccountNumber");
			paramMap.put("loanAccountNumber", loanAccountNumber);
		}
		if (null != firstName && firstName.length() > 0) {
			query.append(" and firstName like ('%" + firstName + "%')");
//			paramMap.put("firstName", firstName);
		}
		if (null != parentBranchCode && !parentBranchCode.isEmpty()) {
			query.append(
					" and trn.branchCode in (select branchCode from sc_branch where parentbranch = :parentBranchCode)");
			paramMap.put("parentBranchCode", parentBranchCode);
		}
		if (null != branchCode && branchCode.length() > 0) {
			query.append(" and trn.branchCode = :branchCode");
			paramMap.put("branchCode", branchCode);
		}
		if (null != product && product.length() > 0) {
			query.append(" and genericString2 = :product");
			paramMap.put("product", product);
		}
//		if(null != queue && !queue.equals(0L)) {
//			query.append(" and dataSetType = :queue");
//			paramMap.put("queue", queue);
//		}
		if(null != dpdQueue && !dpdQueue.equals(0L)) {
			query.append(" and genericNumber3 = :dpdQueue");
			paramMap.put("dpdQueue", dpdQueue);
		}
		if (null != assignedTo && assignedTo.length() > 0) {
			query.append(
					" and dataSetId in (select dataSetId from sc_activity where userId = :assignedTo and activityStatus in ('PENDING','IN-PROCESS'))");
			paramMap.put("assignedTo", assignedTo);
		} else {
			query.append(" and dataSetId not in (select dataSetId from sc_activity )");
		}
		System.out.println(query);
		transactionDataSetForAssignment = namedParameterJdbcTemplate.query(query.toString(), paramMap,
				new TransactionDataSetRowMapper());
		return transactionDataSetForAssignment;
	}

	public List<TransactionDataSet> findTransactionDataSetForStatusView(Long mobileNumber, String parentBranchCode,
			String branchCode, String assignedTo, String product, Long queue, Long dpdQueue, Long pincode,
			Long loanAccountNumber, String firstName) {
		List<TransactionDataSet> transactionDataSetForAssignment = new ArrayList<TransactionDataSet>();
		StringBuffer query = new StringBuffer();
		query.append("select trn.dataSetId,trn.companyId,dataSetType,dataSetDescription,firstName,middleName,");
		query.append(" lastName,dateOfBirth,gender,dbo.fn_getGenClass(gender,trn.companyId) genderStr,");
		query.append(" religion,dbo.fn_getGenClass(religion,trn.companyId) religionStr,socialCategory,");
		query.append(" dbo.fn_getGenClass(religion,trn.companyId) socialCategoryStr,aadhaarNumber,");
		query.append(" voterId,drivingLicenseNumber,panCardNumber,gramPanchayat,village,");
		query.append(" trn.branchCode,trn.genericString1,trn.genericString2,trn.genericString3,trn.genericString4,");
		query.append(
				"trn.genericString5,trn.genericString6,trn.genericString7,	trn.genericString8,	trn.genericString9,	trn.genericString10,");
		query.append("trn.genericString11,trn.genericString12,trn.genericString13,trn.genericString14,");
		query.append("trn.genericString15,trn.genericString16,trn.genericString17,trn.genericString18,");
		query.append("trn.genericString19,trn.genericString20,trn.genericString21,trn.genericString22,");
		query.append("trn.genericString23,trn.genericString24,trn.genericString25,trn.genericString26,");
		query.append("trn.genericString27,trn.genericString28,trn.genericString29,trn.genericString30,");
		query.append("trn.genericString31,trn.genericString32,trn.genericString33,trn.genericString35,");
		query.append("trn.genericString36,trn.genericString37,trn.genericString38,trn.genericString39,");
		query.append("trn.genericString40,trn.genericString41,trn.genericString42,trn.genericString43,");
		query.append("trn.genericString44,trn.genericString45,trn.genericString46,trn.genericString47,");
		query.append("trn.genericString48,trn.genericString49,trn.genericString50,trn.genericNumber1,");
		query.append("trn.genericNumber2,trn.genericNumber3,trn.genericNumber4,trn.genericNumber5,");
		query.append("trn.genericNumber6,trn.genericNumber7,trn.genericNumber8,trn.genericNumber9,");
		query.append("trn.genericNumber10,trn.genericNumber11,trn.genericNumber12,trn.genericNumber13,");
		query.append("trn.genericNumber14,trn.genericNumber15,trn.genericNumber16,trn.genericNumber17,");
		query.append("trn.genericNumber18,trn.genericNumber19,trn.genericNumber20,trn.genericNumber21,");
		query.append("trn.genericNumber22,trn.genericNumber23,trn.genericNumber24,trn.genericNumber25,");
		query.append("trn.genericNumber26,trn.genericNumber27,trn.genericNumber28,trn.genericNumber29,");
		query.append("trn.genericNumber30,trn.genericNumber31,trn.genericNumber32,trn.genericNumber33,");
		query.append("trn.genericNumber34,trn.genericNumber35,trn.genericNumber36,trn.genericNumber37,");
		query.append("trn.genericNumber38,trn.genericNumber39,trn.genericNumber40,trn.genericNumber41,");
		query.append("trn.genericNumber42,trn.genericNumber43,trn.genericNumber44,trn.genericNumber45,");
		query.append("trn.genericNumber46,trn.genericNumber47,trn.genericNumber48,trn.genericNumber49,");
		query.append("trn.genericNumber50,trn.genericDecimal1,trn.genericDecimal2,trn.genericDecimal3,");
		query.append("trn.genericDecimal4,trn.genericDecimal5,trn.genericDecimal6,trn.genericDecimal7,");
		query.append("trn.genericDecimal8,trn.genericDecimal9,trn.genericDecimal10,trn.genericDecimal11,");
		query.append("trn.genericDecimal12,trn.genericDecimal13,trn.genericDecimal14,trn.genericDecimal15,");
		query.append("trn.genericDecimal16,trn.genericDecimal17,trn.genericDecimal18,trn.genericDecimal19,");
		query.append("trn.genericDecimal20,trn.genericDecimal21,trn.genericDecimal22,trn.genericDecimal23,");
		query.append("trn.genericDecimal24,trn.genericDecimal25,trn.genericDecimal26,trn.genericDecimal27,");
		query.append("trn.genericDecimal28,trn.genericDecimal29,trn.genericDecimal30,trn.genericDecimal31,");
		query.append("trn.genericDecimal32,trn.genericDecimal33,trn.genericDecimal34,trn.genericDecimal35,");
		query.append("trn.genericDecimal36,trn.genericDecimal37,trn.genericDecimal38,trn.genericDecimal39,");
		query.append("trn.genericDecimal40,trn.genericDecimal41,trn.genericDecimal42,trn.genericDecimal43,");
		query.append("trn.genericDecimal44,trn.genericDecimal45,trn.genericDecimal46,trn.genericDecimal47,");
		query.append(
				"trn.genericDecimal48,trn.genericDecimal49,trn.genericDecimal50,trn.genericDate1,trn.genericDate2,trn.genericDate3,trn.genericDate4,trn.genericDate5,trn.genericDate6,trn.genericDate7,");
		query.append(
				"trn.genericDate8,trn.genericDate9,trn.genericDate10,trn.genericDate11,trn.genericDate12,trn.genericDate13,trn.genericDate14,trn.genericDate15,trn.genericDate16,trn.genericDate17,");
		query.append(
				"trn.genericDate18,trn.genericDate19,trn.genericDate20,trn.genericDate21,trn.genericDate22,trn.genericDate23,trn.genericDate24,trn.genericDate25,trn.genericDate26,trn.genericDate27,");
		query.append(
				"trn.genericDate28,trn.genericDate29,trn.genericDate30,trn.genericDate31,trn.genericDate32,trn.genericDate33,trn.genericDate34,trn.genericDate35,trn.genericDate36,trn.genericDate37,");
		query.append(
				"trn.genericDate38,trn.genericDate39,trn.genericDate40,trn.genericDate41,trn.genericDate42,trn.genericDate43,trn.genericDate44,trn.genericDate45,trn.genericDate46,trn.genericDate47,");
		query.append(
				"trn.genericDate48,trn.genericDate49,trn.genericDate50,actionType,scheduledDatetime,trn.genericString34,trn.status,trn.createdBy,trn.createdDateTime,");
		if (null != assignedTo && assignedTo.length() > 0) {
			query.append("'Assigned' as actionStatus,");
		} else
			query.append("'Not Assigned' as actionStatus,");
		query.append(
				" trn.updatedBy,trn.updatedDateTime,trn.removeDateTime,trn.validFrom,trn.validTo,act.activityDateTime,dbo.fn_getUserName(act.userId,1) userName ");
		query.append(" from sc_transactionDataSet trn join sc_activity act  ");
		query.append(
				" on (trn.dataSetId = act.dataSetId and act.parentActivity is null and act.activityStatus <> 'RE-ASSIGNED') ");
		query.append(" where 1 = 1 ");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (null != mobileNumber && !mobileNumber.equals(0L)) {
			query.append(" and trn.genericNumber1 = :mobileNumber");
			paramMap.put("mobileNumber", mobileNumber);
		}
		if (null != pincode && !pincode.equals(0L)) {
			query.append(" and trn.genericNumber4 = :pincode");
			paramMap.put("pincode", pincode);
		}
		if (null != loanAccountNumber && !loanAccountNumber.equals(0L)) {
			query.append(" and trn.genericNumber2 = :loanAccountNumber");
			paramMap.put("loanAccountNumber", loanAccountNumber);
		}
		if (null != firstName && firstName.length() > 0) {
			query.append(" and firstName like ('%" + firstName + "%')");
//			paramMap.put("firstName", firstName);
		}
		if (null != parentBranchCode && !parentBranchCode.isEmpty()) {
			query.append(
					" and trn.branchCode in (select branchCode from sc_branch where parentbranch = :parentBranchCode)");
			paramMap.put("parentBranchCode", parentBranchCode);
		}
		if (null != branchCode && branchCode.length() > 0) {
			query.append(" and trn.branchCode = :branchCode");
			paramMap.put("branchCode", branchCode);
		}
		if (null != product && product.length() > 0) {
			query.append(" and trn.genericString2 = :product");
			paramMap.put("product", product);
		}
//		if(null != queue && !queue.equals(0L)) {
//			query.append(" and trn.dataSetType = :queue");
//			paramMap.put("queue", queue);
//		}
//		if(null != dpdQueue && !dpdQueue.equals(0L)) {
//			query.append(" and trn.genericNumber3 = :dpdQueue");
//			paramMap.put("dpdQueue", dpdQueue);
//		}
		if (null != assignedTo && assignedTo.length() > 0) {
			query.append(" and act.userId = :assignedTo ");
			paramMap.put("assignedTo", assignedTo);
		}
		System.out.println(query);
		transactionDataSetForAssignment = namedParameterJdbcTemplate.query(query.toString(), paramMap,
				new TransactionDataSetRowMapper());
		return transactionDataSetForAssignment;
	}

	public void createTransactions(List<TransactionDataSet> transactions) {
		String sql = "insert into sc_transactionDataSet (dataSetId,dataSetType,dataSetDescription,"
				+ "firstName,dateOfBirth,gender,religion,panCardNumber,branchCode,genericString2,"
				+ "genericString4,genericString5,genericNumber1,genericNumber2,genericNumber3,genericNumber4,"
				+ "genericDecimal1,genericDecimal4,genericDecimal5,genericDecimal6,genericDecimal7,"
				+ "genericDate1,genericDate2,companyId,status,createdBy,createdDateTime,removeDateTime,"
				+ "validFrom,validTo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.batchUpdate(sql, transactions, 1000,
				(PreparedStatement ps, TransactionDataSet transactionDataSet) -> {
					Long dataSetId = transactionDataSet.getDataSetId();
					ps.setLong(1, dataSetId++);
					ps.setLong(2, transactionDataSet.getDataSetType());
					ps.setString(3, transactionDataSet.getDataSetDescription());
					ps.setString(4, transactionDataSet.getFirstName());
					if (null != transactionDataSet.getDateOfBirth())
						ps.setDate(5, new java.sql.Date(transactionDataSet.getDateOfBirth().getTime()));
					else
						ps.setNull(5, Types.DATE);
					if (null != transactionDataSet.getGender())
						ps.setLong(6, transactionDataSet.getGender());
					else
						ps.setNull(6, Types.BIGINT);
					if (null != transactionDataSet.getReligion())
						ps.setLong(7, transactionDataSet.getReligion());
					else
						ps.setNull(7, Types.BIGINT);
					if (null != transactionDataSet.getPanCardNumber())
						ps.setString(8, transactionDataSet.getPanCardNumber());
					else
						ps.setNull(8, Types.VARCHAR);
					if (null != transactionDataSet.getBranchCode())
						ps.setString(9, transactionDataSet.getBranchCode());
					else
						ps.setNull(9, Types.VARCHAR);
					if (null != transactionDataSet.getGenericString2())
						ps.setString(10, transactionDataSet.getGenericString2());
					else
						ps.setNull(10, Types.VARCHAR);
					if (null != transactionDataSet.getGenericString4())
						ps.setString(11, transactionDataSet.getGenericString4());
					else
						ps.setNull(11, Types.VARCHAR);
					if (null != transactionDataSet.getGenericString5())
						ps.setString(12, transactionDataSet.getGenericString5());
					else
						ps.setNull(12, Types.VARCHAR);
					if (null != transactionDataSet.getGenericNumber1())
						ps.setLong(13, transactionDataSet.getGenericNumber1());
					else
						ps.setNull(13, Types.BIGINT);
					if (null != transactionDataSet.getGenericNumber2())
						ps.setLong(14, transactionDataSet.getGenericNumber2());
					else
						ps.setNull(14, Types.BIGINT);
					if (null != transactionDataSet.getGenericNumber3())
						ps.setLong(15, transactionDataSet.getGenericNumber3());
					else
						ps.setNull(15, Types.BIGINT);
					if (null != transactionDataSet.getGenericNumber4())
						ps.setLong(16, transactionDataSet.getGenericNumber4());
					else
						ps.setNull(16, Types.BIGINT);
					if (null != transactionDataSet.getGenericDecimal1())
						ps.setBigDecimal(17, transactionDataSet.getGenericDecimal1());
					else
						ps.setNull(17, Types.NUMERIC);
					if (null != transactionDataSet.getGenericDecimal4())
						ps.setBigDecimal(18, transactionDataSet.getGenericDecimal4());
					else
						ps.setNull(18, Types.NUMERIC);
					if (null != transactionDataSet.getGenericDecimal5())
						ps.setBigDecimal(19, transactionDataSet.getGenericDecimal5());
					else
						ps.setNull(19, Types.NUMERIC);
					if (null != transactionDataSet.getGenericDecimal6())
						ps.setBigDecimal(20, transactionDataSet.getGenericDecimal6());
					else
						ps.setNull(20, Types.NUMERIC);
					if (null != transactionDataSet.getGenericDecimal7())
						ps.setBigDecimal(21, transactionDataSet.getGenericDecimal7());
					else
						ps.setNull(21, Types.NUMERIC);
					if (null != transactionDataSet.getGenericDecimal7())
						ps.setDate(22, new java.sql.Date(transactionDataSet.getGenericDate1().getTime()));
					else
						ps.setNull(22, Types.DATE);
					ps.setDate(23, new java.sql.Date(transactionDataSet.getGenericDate2().getTime()));
					ps.setLong(24, transactionDataSet.getCompanyId());
					ps.setString(25, transactionDataSet.getStatus());
					ps.setString(26, transactionDataSet.getCreatedBy());
					ps.setDate(27, new java.sql.Date(transactionDataSet.getCreatedDateTime().getTime()));
					ps.setDate(28, new java.sql.Date(transactionDataSet.getRemoveDateTime().getTime()));
					ps.setDate(29, new java.sql.Date(transactionDataSet.getValidFrom().getTime()));
					ps.setDate(30, new java.sql.Date(transactionDataSet.getValidFrom().getTime()));
				});
	}

	public void updateTransactions(List<TransactionDataSet> transactions) {
		String sql = "update sc_transactionDataSet set firstName = ?,dateOfBirth = ?,gender = ?,"
				+ "religion = ?,panCardNumber = ?,branchCode = ?,genericString2 = ?,"
				+ "genericString4 = ?,genericString5 = ?,genericNumber1 = ?,genericNumber2 = ?,genericNumber3 = ?,genericNumber4 = ?,"
				+ "genericDecimal1 = ?,genericDecimal4 = ?,genericDecimal5 = ?,genericDecimal6 = ?,genericDecimal7 = ?,"
				+ "genericDate1 = ?,genericDate2 = ? where dataSetId = ?";
		jdbcTemplate.batchUpdate(sql, transactions, 1000,
				(PreparedStatement ps, TransactionDataSet transactionDataSet) -> {
					ps.setString(1, transactionDataSet.getFirstName());
					if (null != transactionDataSet.getDateOfBirth())
						ps.setDate(2, new java.sql.Date(transactionDataSet.getDateOfBirth().getTime()));
					else
						ps.setNull(2,Types.DATE);
					if(null != transactionDataSet.getGender())
					ps.setLong(3, transactionDataSet.getGender());
					else
						ps.setNull(3,Types.BIGINT);
					if (null != transactionDataSet.getReligion())
						ps.setLong(4, transactionDataSet.getReligion());
					else
						ps.setNull(4, Types.BIGINT);
					if (null != transactionDataSet.getPanCardNumber())
						ps.setString(5, transactionDataSet.getPanCardNumber());
					else
						ps.setNull(5, Types.VARCHAR);
					if (null != transactionDataSet.getBranchCode())
						ps.setString(6, transactionDataSet.getBranchCode());
					else
						ps.setNull(6, Types.VARCHAR);
					if (null != transactionDataSet.getGenericString2())
						ps.setString(7, transactionDataSet.getGenericString2());
					else
						ps.setNull(7, Types.VARCHAR);
					if (null != transactionDataSet.getGenericString4())
						ps.setString(8, transactionDataSet.getGenericString4());
					else
						ps.setNull(8, Types.VARCHAR);
					if (null != transactionDataSet.getGenericString5())
						ps.setString(9, transactionDataSet.getGenericString5());
					else
						ps.setNull(9, Types.VARCHAR);
					if (null != transactionDataSet.getGenericNumber1())
						ps.setLong(10, transactionDataSet.getGenericNumber1());
					else
						ps.setNull(10, Types.BIGINT);
					if (null != transactionDataSet.getGenericNumber2())
						ps.setLong(11, transactionDataSet.getGenericNumber2());
					else
						ps.setNull(11, Types.BIGINT);
					if (null != transactionDataSet.getGenericNumber3())
						ps.setLong(12, transactionDataSet.getGenericNumber3());
					else
						ps.setNull(12, Types.BIGINT);
					if (null != transactionDataSet.getGenericNumber4())
						ps.setLong(13, transactionDataSet.getGenericNumber4());
					else
						ps.setNull(13, Types.BIGINT);
					if (null != transactionDataSet.getGenericDecimal1())
						ps.setBigDecimal(14, transactionDataSet.getGenericDecimal1());
					else
						ps.setNull(14, Types.BIGINT);
					if (null != transactionDataSet.getGenericDecimal4())
						ps.setBigDecimal(15, transactionDataSet.getGenericDecimal4());
					else
						ps.setNull(15, Types.BIGINT);
					if (null != transactionDataSet.getGenericDecimal5())
						ps.setBigDecimal(16, transactionDataSet.getGenericDecimal5());
					else
						ps.setNull(16, Types.BIGINT);
					if (null != transactionDataSet.getGenericDecimal6())
						ps.setBigDecimal(17, transactionDataSet.getGenericDecimal6());
					else
						ps.setNull(17, Types.BIGINT);
					if (null != transactionDataSet.getGenericDecimal7())
						ps.setBigDecimal(18, transactionDataSet.getGenericDecimal7());
					else
						ps.setNull(18, Types.BIGINT);
					if (null != transactionDataSet.getGenericDate1())
						ps.setDate(19, new java.sql.Date(transactionDataSet.getGenericDate1().getTime()));
					else
						ps.setNull(19, Types.DATE);
					if (null != transactionDataSet.getGenericDate2())
						ps.setDate(20, new java.sql.Date(transactionDataSet.getGenericDate2().getTime()));
					else
						ps.setNull(20, Types.DATE);
					ps.setLong(21, transactionDataSet.getDataSetId());
				});
	}
}

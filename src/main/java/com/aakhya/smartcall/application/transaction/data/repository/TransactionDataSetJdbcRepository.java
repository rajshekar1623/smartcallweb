package com.aakhya.smartcall.application.transaction.data.repository;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;
import com.aakhyatech.smartcall.application.utils.StringUtils;
import com.aakhya.smartcall.application.transaction.data.entity.DetailViewConfig;
import com.aakhya.smartcall.application.transaction.data.entity.DetailViewObject;
import com.aakhya.smartcall.application.transaction.data.entity.DpdQueue;
import com.aakhya.smartcall.application.transaction.data.entity.DpdQueueList;

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
		if (null != dpdQueue && !dpdQueue.equals(0L)) {
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
						ps.setNull(2, Types.DATE);
					if (null != transactionDataSet.getGender())
						ps.setLong(3, transactionDataSet.getGender());
					else
						ps.setNull(3, Types.BIGINT);
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
	
	/**
	 * The following methods are for Mobile App backend services
	 */
	
	public DpdQueue getDpDQueue(String userId,String branchCode, Long queue) {
		DpdQueue dpdQueue = new DpdQueue();
		if (null != queue && queue.equals(3738L))
			dpdQueue.setDpdQueueName("1-30 days");
		else if (null != queue && queue.equals(3739L))
			dpdQueue.setDpdQueueName("31-60 days");
		else if (null != queue && queue.equals(3740L))
			dpdQueue.setDpdQueueName("60-90 days");
		else if (null != queue && queue.equals(3750L))
			dpdQueue.setDpdQueueName("Above 90 days");
		else if (null != queue && queue.equals(3741L))
			dpdQueue.setDpdQueueName("Renewal Queue");
		else if (null != queue && queue.equals(3742L))
			dpdQueue.setDpdQueueName("Renewal Followup");
		Integer pendingCountForQueue = getDpdQueueCount(userId,branchCode, queue, "PENDING");
		Integer completedCountForQueue = getDpdQueueCount(userId,branchCode, queue, "COMPLETE");
		Integer inprocessCountForQueue = getDpdQueueCount(userId,branchCode, queue, "IN-PROCESS");
		Integer noOfMembers = pendingCountForQueue + completedCountForQueue;
		dpdQueue.setNoOfMembers(noOfMembers);
		dpdQueue.setCompleted(completedCountForQueue);
		dpdQueue.setPending(pendingCountForQueue);
		dpdQueue.setInprocess(inprocessCountForQueue);
		return dpdQueue;
	}

	private Integer getDpdQueueCount(String userId,String branchCode, Long queue, String actionStatus) {
//		String query = "select count(1) from sc_transactionDataSet where branchCode = :branchCode and genericNumber3 = :queue and actionStatus = :actionStatus";
		String query = null;
		if("PENDING".equals(actionStatus)) {
		query = "select count(1) from sc_transactionDataSet trx join sc_activity act "
				+ " on(trx.dataSetId = act.dataSetId and act.userId = :userId and trx.genericNumber3 = :queue "
				+ " and trx.dataSetType = 2 and act.activityType = 1001 and act.activityStatus in ('PENDING','RE-ASSIGNED'))";
		}else {
			query = "select count(1) from sc_transactionDataSet trx join sc_activity act "
					+ " on(trx.dataSetId = act.dataSetId and act.userId = :userId and trx.genericNumber3 = :queue "
					+ " and trx.dataSetType = 2 and act.activityType = 1001 and act.activityStatus = :actionStatus)";
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("branchCode", branchCode);
		params.put("queue", queue);
		params.put("actionStatus", actionStatus);
		Integer count = namedParameterJdbcTemplate.queryForObject(query, params, Integer.class);
		return count;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DpdQueueList> getDpdQueueList(String userId,String branchCode, Long queue) {
		List<DpdQueueList> dpdQueueLists = new ArrayList<DpdQueueList>();
		StringBuffer query = new StringBuffer();
//		query.append("select top 100 dataSetId,case when firstName is not null then firstName else '' end +' '+");
//		query.append("case when middleName is not null then middleName else '' end+' '+");
//		query.append("case when LastName is not null then lastName else '' end as memberName,");
//		query.append("dbo.fn_getGenericClassifier(village,7) as location ");
//		query.append("from sc_transactionDataSet where branchCode = :branchCode and genericNumber3 = :queue");
		query.append(" select trn.dataSetId,dbo.fn_getName(trn.dataSetId) memberName, ");
		query.append(" dbo.fn_getGenericClassifier(village,7) as location, ");
		query.append(" trn.genericNumber1,trn.genericNumber4,trn.genericDecimal10,trn.genericDecimal11,trn.genericDecimal12,");
//		query.append(" case when dbo.fn_checkActivityStatus(trn.dataSetId,'COMPLETE') = 'Y' then 'Complete'  ");
//		query.append(" when dbo.fn_checkActivityStatus(trn.dataSetId,'INPROCESS') = 'Y' then 'In Process' ");
//		query.append(" when dbo.fn_checkActivityStatus(trn.dataSetId,'PENDING') = 'Y' then 'Pending' end ");
		query.append(" activityStatus actionStatus from sc_transactionDataSet trn join sc_activity act  ");
		query.append(" on(trn.dataSetId = act.dataSetId and trn.dataSetType = 2 and activityType = 1001 ");
		query.append(" and trn.genericNumber3 = :queue and act.userId = :userId) ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("branchCode", branchCode);
		params.put("userId", userId);
		params.put("queue", queue);
		dpdQueueLists = namedParameterJdbcTemplate.query(query.toString(), params, new RowMapper() {

			@Override
			public DpdQueueList mapRow(ResultSet rs, int rowNum) throws SQLException {
				DpdQueueList dpdQueueList = new DpdQueueList();
				dpdQueueList.setDataSetId(rs.getLong("dataSetId"));
				dpdQueueList.setMemberName(rs.getString("memberName"));
				dpdQueueList.setLocation(rs.getString("location"));
				dpdQueueList.setActionStatus(rs.getString("actionStatus"));
				dpdQueueList.setMobileNumber(rs.getString("genericNumber1"));
				dpdQueueList.setPinCode(rs.getString("genericNumber4"));
				dpdQueueList.setLattitute(rs.getDouble("genericDecimal10"));
				dpdQueueList.setLongitute(rs.getDouble("genericDecimal11"));
				dpdQueueList.setDistance(rs.getDouble("genericDecimal12"));
				return dpdQueueList;
			}
		});
		return dpdQueueLists;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DpdQueueList> getVisitList(String userId,String branchCode) {
		List<DpdQueueList> dpdQueueLists = new ArrayList<DpdQueueList>();
		StringBuffer query = new StringBuffer();
		query.append(" select trn.dataSetId,dbo.fn_getName(trn.dataSetId) memberName, ");
		query.append(" dbo.fn_getGenericClassifier(village,7) as location, ");
		query.append(" activityStatus actionStatus,scheduleDateTime,trn.genericNumber1,trn.genericNumber4, "); 
		query.append(" trn.genericDecimal10,trn.genericDecimal11,trn.genericDecimal12 ");
		query.append(" from sc_transactionDataSet trn join sc_activity act  ");
		query.append(" on(trn.dataSetId = act.dataSetId and trn.dataSetType = 2 and activityType = 1004 ");
		query.append(" and act.userId = :userId  and activityStatus = 'PENDING') ");
		query.append(" join sc_activityDetail det on(act.activityId = det.activityId and det.scheduleType = 'VISIT' ");
		query.append(" and convert(varchar,det.scheduleDateTime,105) = convert(varchar,getDate(),105))");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("branchCode", branchCode);
		params.put("userId", userId);
		dpdQueueLists = namedParameterJdbcTemplate.query(query.toString(), params, new RowMapper() {

			@Override
			public DpdQueueList mapRow(ResultSet rs, int rowNum) throws SQLException {
				DpdQueueList dpdQueueList = new DpdQueueList();
				dpdQueueList.setDataSetId(rs.getLong("dataSetId"));
				dpdQueueList.setMemberName(rs.getString("memberName"));
				dpdQueueList.setLocation(rs.getString("location"));
				dpdQueueList.setActionStatus(rs.getString("actionStatus"));
				Date scheduleDateTime = rs.getTimestamp("scheduleDateTime");
				if(null != scheduleDateTime) {
					String scheduleDateTimeStr = new SimpleDateFormat("dd-MM-yyyy hh:mm a").
							format(scheduleDateTime);
					dpdQueueList.setScheduleDateTime(scheduleDateTimeStr);
				}
				dpdQueueList.setMobileNumber(rs.getString("genericNumber1"));
				dpdQueueList.setPinCode(rs.getString("genericNumber4"));
				dpdQueueList.setLattitute(rs.getDouble("genericDecimal10"));
				dpdQueueList.setLongitute(rs.getDouble("genericDecimal11"));
				dpdQueueList.setDistance(rs.getDouble("genericDecimal12"));
				return dpdQueueList;
			}
		});
		
		return dpdQueueLists;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DpdQueueList> getCallList(String userId,String branchCode) {
		List<DpdQueueList> dpdQueueLists = new ArrayList<DpdQueueList>();
		StringBuffer query = new StringBuffer();
		query.append(" select trn.dataSetId,dbo.fn_getName(trn.dataSetId) memberName, ");
		query.append(" dbo.fn_getGenericClassifier(village,7) as location, ");
		query.append(" activityStatus actionStatus,scheduleDateTime,trn.genericNumber1,trn.genericNumber4, "); 
		query.append(" trn.genericDecimal10,trn.genericDecimal11,trn.genericDecimal12 ");
		query.append(" from sc_transactionDataSet trn join sc_activity act  ");
		query.append(" on(trn.dataSetId = act.dataSetId and trn.dataSetType = 2 and activityType = 1004 ");
		query.append(" and act.userId = :userId and activityStatus = 'PENDING') ");
		query.append(" join sc_activityDetail det on(act.activityId = det.activityId and det.scheduleType = 'CALL' ");
		query.append(" and convert(varchar,det.scheduleDateTime,105) = convert(varchar,getDate(),105))");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("branchCode", branchCode);
		params.put("userId", userId);
		dpdQueueLists = namedParameterJdbcTemplate.query(query.toString(), params, new RowMapper() {

			@Override
			public DpdQueueList mapRow(ResultSet rs, int rowNum) throws SQLException {
				DpdQueueList dpdQueueList = new DpdQueueList();
				dpdQueueList.setDataSetId(rs.getLong("dataSetId"));
				dpdQueueList.setMemberName(rs.getString("memberName"));
				dpdQueueList.setLocation(rs.getString("location"));
				dpdQueueList.setActionStatus(rs.getString("actionStatus"));
				Date scheduleDateTime = rs.getTimestamp("scheduleDateTime");
				if(null != scheduleDateTime) {
					String scheduleDateTimeStr = new SimpleDateFormat("dd-MM-yyyy hh:mm a").
							format(scheduleDateTime);
					dpdQueueList.setScheduleDateTime(scheduleDateTimeStr);
				}
				dpdQueueList.setMobileNumber(rs.getString("genericNumber1"));
				dpdQueueList.setPinCode(rs.getString("genericNumber4"));
				dpdQueueList.setLattitute(rs.getDouble("genericDecimal10"));
				dpdQueueList.setLongitute(rs.getDouble("genericDecimal11"));
				dpdQueueList.setDistance(rs.getDouble("genericDecimal12"));
				return dpdQueueList;
			}
		});
		
		return dpdQueueLists;
	}

	@SuppressWarnings("unused")
	private double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}
		return (dist);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}

//	private int generateRandomNumber(int min, int max) {
//		return (int) (Math.random() * (max - min + 1) + min);
//	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DetailViewObject> getDetailView(TransactionDataSet transactionDataSet, Long queue) {
		List<DetailViewObject> detailViewObjects = new ArrayList<DetailViewObject>();
		StringBuffer query = new StringBuffer();
		query.append("select queue,sequence,fieldName,fieldLable,columFunction,editable");
		query.append(" ,editDataType,button,buttonLable,buttonAction from sc_configDetails where queue = :queue");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("queue", queue);
		try {
			List<DetailViewConfig> detailViewConfigs = namedParameterJdbcTemplate.query(query.toString(), paramMap,
					new RowMapper() {

						@Override
						public DetailViewConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
							DetailViewConfig detailViewConfig = new DetailViewConfig();
							detailViewConfig.setQueue(rs.getLong("queue"));
							detailViewConfig.setSequence(rs.getInt("sequence"));
							detailViewConfig.setFieldName(rs.getString("fieldName"));
							detailViewConfig.setFieldLable(rs.getString("fieldLable"));
							detailViewConfig.setColumFunction(rs.getString("columFunction"));
							detailViewConfig.setEditable(rs.getString("editable"));
							detailViewConfig.setEditDataType(rs.getString("editDataType"));
							detailViewConfig.setButton(rs.getString("button"));
							detailViewConfig.setButtonLable(rs.getString("buttonLable"));
							detailViewConfig.setButtonAction(rs.getString("buttonAction"));
							return detailViewConfig;
						}
					});
//			System.
			if (null != detailViewConfigs && !detailViewConfigs.isEmpty()) {
				for (DetailViewConfig detailViewConfig : detailViewConfigs) {
					DetailViewObject detailViewObject = new DetailViewObject();
					if(detailViewConfig.getFieldLable().endsWith("as on"))
						detailViewObject.setLable(detailViewConfig.getFieldLable()+" "+(new SimpleDateFormat("dd-MMM-yyyy").format(new Date())));
					else
					detailViewObject.setLable(detailViewConfig.getFieldLable());
					detailViewObject.setSequence(detailViewConfig.getSequence());
					detailViewObject.setEditable(detailViewConfig.getEditable());
					detailViewObject.setEditDataType(detailViewConfig.getEditDataType());
					detailViewObject.setButton(detailViewConfig.getButton());
					detailViewObject.setButtonLable(detailViewConfig.getButtonLable());
					detailViewObject.setButtonAction(detailViewConfig.getButtonAction());
					if ("C".equals(detailViewConfig.getColumFunction())) {
						try {
							Field f = transactionDataSet.getClass().getDeclaredField(detailViewConfig.getFieldName());
							f.setAccessible(true);
							Object fieldValue = f.get(transactionDataSet);
							if(null != fieldValue) {
							if ("aadhaarNumber".equals(detailViewConfig.getFieldName())) {
								try {
									String decryptedAadhaar = StringUtils.decrypt(fieldValue.toString());
									detailViewObject.setValue(decryptedAadhaar);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else if(("genericNumber1".equals(detailViewConfig.getFieldName())
									|| "genericNumber2".equals(detailViewConfig.getFieldName())
									|| "genericNumber3".equals(detailViewConfig.getFieldName()))
									&& null != fieldValue){
								String stringValue = fieldValue.toString();
								detailViewObject.setValue(stringValue);
//						} else if("genericNumber2".equals(detailViewConfig.getFieldName())){
////								|| "genericNumber2".equals(detailViewConfig.getFieldName())
////								|| "genericNumber3".equals(detailViewConfig.getFieldName()))
////								&& null != fieldValue){
//							String stringValue = fieldValue.toString();
//							detailViewObject.setValue(stringValue);
							} else if("genericDate2".equals(detailViewConfig.getFieldName())){
								if(null != fieldValue && fieldValue instanceof Date) {
									Date scheduledDateTime = (Date) fieldValue;
									String scheduledDateTimeStr = new SimpleDateFormat("dd-MM-yyyy hh:mm a").format(scheduledDateTime);
									detailViewObject.setValue(scheduledDateTimeStr);
								}else
									detailViewObject.setValue(fieldValue);
							} else 
								detailViewObject.setValue(fieldValue);
							}
						} catch (NoSuchFieldException | SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else  if ("F".equals(detailViewConfig.getColumFunction())) {
						if ("getName".equals(detailViewConfig.getFieldName())) {
							detailViewObject.setValue(getName(transactionDataSet));
						} else if ("getLocation".equals(detailViewConfig.getFieldName())) {
							detailViewObject.setValue(getLocation(transactionDataSet));
						} else if ("calculateInterest".equals(detailViewConfig.getFieldName())) {
							detailViewObject.setValue(calculateInterest(transactionDataSet));
						} else if ("calculateTotalPayable".equals(detailViewConfig.getFieldName())) {
							detailViewObject.setValue(calculateTotalPayable(transactionDataSet));
						}
					}
					detailViewObjects.add(detailViewObject);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return detailViewObjects;
	}

	private String getName(TransactionDataSet transactionDataSet) {
		String name = null;
		if (null != transactionDataSet.getFirstName() && null != transactionDataSet.getMiddleName()
				&& null != transactionDataSet.getLastName())
			name = transactionDataSet.getFirstName() + " " + transactionDataSet.getMiddleName() + " "
					+ transactionDataSet.getLastName();
		else if (null != transactionDataSet.getFirstName() && null != transactionDataSet.getLastName())
			name = transactionDataSet.getFirstName() + " " + transactionDataSet.getLastName();
		else
			name = transactionDataSet.getFirstName();
		return name;
	}

	private String getLocation(TransactionDataSet transactionDataSet) {
		String location = null;
		String query = "select dbo.fn_getGenericClassifier(:location,:companyId)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("location", transactionDataSet.getVillage());
		paramMap.put("companyId", 7L);
		location = namedParameterJdbcTemplate.queryForObject(query, paramMap, String.class);
		return location;
	}

	private BigDecimal calculateInterest(TransactionDataSet transactionDataSet) {
		Date lastInterestPaidDate = transactionDataSet.getGenericDate1();
		Date today = new Date();
		LocalDateTime lastInterestPaidDateLDT = Instant.ofEpochMilli(lastInterestPaidDate.getTime())
				.atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime totayLDT = Instant.ofEpochMilli(today.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
		Long days = Duration.between(lastInterestPaidDateLDT, totayLDT).toDays();
		System.out.println("************* The no of days calculated is :: "+days);
		BigDecimal balanceInterest = ((transactionDataSet.getGenericDecimal4().add(
				transactionDataSet.getGenericDecimal5())).multiply(
						transactionDataSet.getGenericDecimal7()).
				multiply(new BigDecimal(days))).divide(new BigDecimal(36500),2, RoundingMode.HALF_UP);
		System.out.println("************* The balanceInterest calculated is :: "+balanceInterest);
		return balanceInterest;
	}
	
	private BigDecimal calculateTotalPayable(TransactionDataSet transactionDataSet) {
		BigDecimal totalPayable = transactionDataSet.getGenericDecimal4().add(transactionDataSet.getGenericDecimal5())
				.add(calculateInterest(transactionDataSet));
		return totalPayable;
	}
}

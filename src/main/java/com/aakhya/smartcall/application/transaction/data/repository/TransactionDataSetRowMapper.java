package com.aakhya.smartcall.application.transaction.data.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;

public class TransactionDataSetRowMapper implements RowMapper<TransactionDataSet> {

	@Override
	public TransactionDataSet mapRow(ResultSet rs, int rowNum) throws SQLException {
		TransactionDataSet transactionDataSet = new TransactionDataSet();
		transactionDataSet.setDataSetId(rs.getLong("DataSetId"));
		transactionDataSet.setCompanyId(rs.getLong("CompanyId"));
		transactionDataSet.setDataSetType(rs.getLong("DataSetType"));
		transactionDataSet.setDataSetDescription(rs.getString("DataSetDescription"));
		transactionDataSet.setFirstName(rs.getString("FirstName"));
		transactionDataSet.setMiddleName(rs.getString("MiddleName"));
		transactionDataSet.setLastName(rs.getString("LastName"));
		transactionDataSet.setDateOfBirth(rs.getDate("DateOfBirth"));
		transactionDataSet.setGender(rs.getLong("Gender"));
		transactionDataSet.setReligion(rs.getLong("Religion"));
		transactionDataSet.setSocialCategory(rs.getLong("SocialCategory"));
		transactionDataSet.setAadhaarNumber(rs.getString("AadhaarNumber"));
		transactionDataSet.setVoterId(rs.getString("VoterId"));
		transactionDataSet.setDrivingLicenseNumber(rs.getString("DrivingLicenseNumber"));
		transactionDataSet.setPanCardNumber(rs.getString("PanCardNumber"));
		transactionDataSet.setGrampanchayat(rs.getLong("Grampanchayat"));
		transactionDataSet.setVillage(rs.getLong("Village"));
		transactionDataSet.setBranchCode(rs.getString("BranchCode"));
		transactionDataSet.setGenericString1(rs.getString("GenericString1"));
		transactionDataSet.setGenericString2(rs.getString("GenericString2"));
		transactionDataSet.setGenericString3(rs.getString("GenericString3"));
		transactionDataSet.setGenericString4(rs.getString("GenericString4"));
		transactionDataSet.setGenericString5(rs.getString("GenericString5"));
		transactionDataSet.setGenericString6(rs.getString("GenericString6"));
		transactionDataSet.setGenericString7(rs.getString("GenericString7"));
		transactionDataSet.setGenericString8(rs.getString("GenericString8"));
		transactionDataSet.setGenericString9(rs.getString("GenericString9"));
		transactionDataSet.setGenericString10(rs.getString("GenericString10"));
		transactionDataSet.setGenericString11(rs.getString("GenericString11"));
		transactionDataSet.setGenericString12(rs.getString("GenericString12"));
		transactionDataSet.setGenericString13(rs.getString("GenericString13"));
		transactionDataSet.setGenericString14(rs.getString("GenericString14"));
		transactionDataSet.setGenericString15(rs.getString("GenericString15"));
		transactionDataSet.setGenericString16(rs.getString("GenericString16"));
		transactionDataSet.setGenericString17(rs.getString("GenericString17"));
		transactionDataSet.setGenericString18(rs.getString("GenericString18"));
		transactionDataSet.setGenericString19(rs.getString("GenericString19"));
		transactionDataSet.setGenericString20(rs.getString("GenericString20"));
		transactionDataSet.setGenericString21(rs.getString("GenericString21"));
		transactionDataSet.setGenericString22(rs.getString("GenericString22"));
		transactionDataSet.setGenericString23(rs.getString("GenericString23"));
		transactionDataSet.setGenericString24(rs.getString("GenericString24"));
		transactionDataSet.setGenericString25(rs.getString("GenericString25"));
		transactionDataSet.setGenericString26(rs.getString("GenericString26"));
		transactionDataSet.setGenericString27(rs.getString("GenericString27"));
		transactionDataSet.setGenericString28(rs.getString("GenericString28"));
		transactionDataSet.setGenericString29(rs.getString("GenericString29"));
		transactionDataSet.setGenericString30(rs.getString("GenericString30"));
		transactionDataSet.setGenericString31(rs.getString("GenericString31"));
		transactionDataSet.setGenericString32(rs.getString("GenericString32"));
		transactionDataSet.setGenericString33(rs.getString("GenericString33"));
		transactionDataSet.setGenericString35(rs.getString("GenericString35"));
		transactionDataSet.setGenericString36(rs.getString("GenericString36"));
		transactionDataSet.setGenericString37(rs.getString("GenericString37"));
		transactionDataSet.setGenericString38(rs.getString("GenericString38"));
		transactionDataSet.setGenericString39(rs.getString("GenericString39"));
		transactionDataSet.setGenericString40(rs.getString("GenericString40"));
		transactionDataSet.setGenericString41(rs.getString("GenericString41"));
		transactionDataSet.setGenericString42(rs.getString("GenericString42"));
		transactionDataSet.setGenericString43(rs.getString("GenericString43"));
		transactionDataSet.setGenericString44(rs.getString("GenericString44"));
		transactionDataSet.setGenericString45(rs.getString("GenericString45"));
		transactionDataSet.setGenericString46(rs.getString("GenericString46"));
		transactionDataSet.setGenericString47(rs.getString("GenericString47"));
		transactionDataSet.setGenericString48(rs.getString("GenericString48"));
		transactionDataSet.setGenericString49(rs.getString("GenericString49"));
		transactionDataSet.setGenericString50(rs.getString("GenericString50"));
		transactionDataSet.setGenericNumber1(rs.getLong("GenericNumber1"));
		transactionDataSet.setGenericNumber2(rs.getLong("GenericNumber2"));
		transactionDataSet.setGenericNumber3(rs.getLong("GenericNumber3"));
		transactionDataSet.setGenericNumber4(rs.getLong("GenericNumber4"));
		transactionDataSet.setGenericNumber5(rs.getLong("GenericNumber5"));
		transactionDataSet.setGenericNumber6(rs.getLong("GenericNumber6"));
		transactionDataSet.setGenericNumber7(rs.getLong("GenericNumber7"));
		transactionDataSet.setGenericNumber8(rs.getLong("GenericNumber8"));
		transactionDataSet.setGenericNumber9(rs.getLong("GenericNumber9"));
		transactionDataSet.setGenericNumber10(rs.getLong("GenericNumber10"));
		transactionDataSet.setGenericNumber11(rs.getLong("GenericNumber11"));
		transactionDataSet.setGenericNumber12(rs.getLong("GenericNumber12"));
		transactionDataSet.setGenericNumber13(rs.getLong("GenericNumber13"));
		transactionDataSet.setGenericNumber14(rs.getLong("GenericNumber14"));
		transactionDataSet.setGenericNumber15(rs.getLong("GenericNumber15"));
		transactionDataSet.setGenericNumber16(rs.getLong("GenericNumber16"));
		transactionDataSet.setGenericNumber17(rs.getLong("GenericNumber17"));
		transactionDataSet.setGenericNumber18(rs.getLong("GenericNumber18"));
		transactionDataSet.setGenericNumber19(rs.getLong("GenericNumber19"));
		transactionDataSet.setGenericNumber20(rs.getLong("GenericNumber20"));
		transactionDataSet.setGenericNumber21(rs.getLong("GenericNumber21"));
		transactionDataSet.setGenericNumber22(rs.getLong("GenericNumber22"));
		transactionDataSet.setGenericNumber23(rs.getLong("GenericNumber23"));
		transactionDataSet.setGenericNumber24(rs.getLong("GenericNumber24"));
		transactionDataSet.setGenericNumber25(rs.getLong("GenericNumber25"));
		transactionDataSet.setGenericNumber26(rs.getLong("GenericNumber26"));
		transactionDataSet.setGenericNumber27(rs.getLong("GenericNumber27"));
		transactionDataSet.setGenericNumber28(rs.getLong("GenericNumber28"));
		transactionDataSet.setGenericNumber29(rs.getLong("GenericNumber29"));
		transactionDataSet.setGenericNumber30(rs.getLong("GenericNumber30"));
		transactionDataSet.setGenericNumber31(rs.getLong("GenericNumber31"));
		transactionDataSet.setGenericNumber32(rs.getLong("GenericNumber32"));
		transactionDataSet.setGenericNumber33(rs.getLong("GenericNumber33"));
		transactionDataSet.setGenericNumber34(rs.getLong("GenericNumber34"));
		transactionDataSet.setGenericNumber35(rs.getLong("GenericNumber35"));
		transactionDataSet.setGenericNumber36(rs.getLong("GenericNumber36"));
		transactionDataSet.setGenericNumber37(rs.getLong("GenericNumber37"));
		transactionDataSet.setGenericNumber38(rs.getLong("GenericNumber38"));
		transactionDataSet.setGenericNumber39(rs.getLong("GenericNumber39"));
		transactionDataSet.setGenericNumber40(rs.getLong("GenericNumber40"));
		transactionDataSet.setGenericNumber41(rs.getLong("GenericNumber41"));
		transactionDataSet.setGenericNumber42(rs.getLong("GenericNumber42"));
		transactionDataSet.setGenericNumber43(rs.getLong("GenericNumber43"));
		transactionDataSet.setGenericNumber44(rs.getLong("GenericNumber44"));
		transactionDataSet.setGenericNumber45(rs.getLong("GenericNumber45"));
		transactionDataSet.setGenericNumber46(rs.getLong("GenericNumber46"));
		transactionDataSet.setGenericNumber47(rs.getLong("GenericNumber47"));
		transactionDataSet.setGenericNumber48(rs.getLong("GenericNumber48"));
		transactionDataSet.setGenericNumber49(rs.getLong("GenericNumber49"));
		transactionDataSet.setGenericNumber50(rs.getLong("GenericNumber50"));
		transactionDataSet.setGenericDecimal1(rs.getBigDecimal("GenericDecimal1"));
		transactionDataSet.setGenericDecimal2(rs.getBigDecimal("GenericDecimal2"));
		transactionDataSet.setGenericDecimal3(rs.getBigDecimal("GenericDecimal3"));
		transactionDataSet.setGenericDecimal4(rs.getBigDecimal("GenericDecimal4"));
		transactionDataSet.setGenericDecimal5(rs.getBigDecimal("GenericDecimal5"));
		transactionDataSet.setGenericDecimal6(rs.getBigDecimal("GenericDecimal6"));
		transactionDataSet.setGenericDecimal7(rs.getBigDecimal("GenericDecimal7"));
		transactionDataSet.setGenericDecimal8(rs.getBigDecimal("GenericDecimal8"));
		transactionDataSet.setGenericDecimal9(rs.getBigDecimal("GenericDecimal9"));
		transactionDataSet.setGenericDecimal10(rs.getBigDecimal("GenericDecimal10"));
		transactionDataSet.setGenericDecimal11(rs.getBigDecimal("GenericDecimal11"));
		transactionDataSet.setGenericDecimal12(rs.getBigDecimal("GenericDecimal12"));
		transactionDataSet.setGenericDecimal13(rs.getBigDecimal("GenericDecimal13"));
		transactionDataSet.setGenericDecimal14(rs.getBigDecimal("GenericDecimal14"));
		transactionDataSet.setGenericDecimal15(rs.getBigDecimal("GenericDecimal15"));
		transactionDataSet.setGenericDecimal16(rs.getBigDecimal("GenericDecimal16"));
		transactionDataSet.setGenericDecimal17(rs.getBigDecimal("GenericDecimal17"));
		transactionDataSet.setGenericDecimal18(rs.getBigDecimal("GenericDecimal18"));
		transactionDataSet.setGenericDecimal19(rs.getBigDecimal("GenericDecimal19"));
		transactionDataSet.setGenericDecimal20(rs.getBigDecimal("GenericDecimal20"));
		transactionDataSet.setGenericDecimal21(rs.getBigDecimal("GenericDecimal21"));
		transactionDataSet.setGenericDecimal22(rs.getBigDecimal("GenericDecimal22"));
		transactionDataSet.setGenericDecimal23(rs.getBigDecimal("GenericDecimal23"));
		transactionDataSet.setGenericDecimal24(rs.getBigDecimal("GenericDecimal24"));
		transactionDataSet.setGenericDecimal25(rs.getBigDecimal("GenericDecimal25"));
		transactionDataSet.setGenericDecimal26(rs.getBigDecimal("GenericDecimal26"));
		transactionDataSet.setGenericDecimal27(rs.getBigDecimal("GenericDecimal27"));
		transactionDataSet.setGenericDecimal28(rs.getBigDecimal("GenericDecimal28"));
		transactionDataSet.setGenericDecimal29(rs.getBigDecimal("GenericDecimal29"));
		transactionDataSet.setGenericDecimal30(rs.getBigDecimal("GenericDecimal30"));
		transactionDataSet.setGenericDecimal31(rs.getBigDecimal("GenericDecimal31"));
		transactionDataSet.setGenericDecimal32(rs.getBigDecimal("GenericDecimal32"));
		transactionDataSet.setGenericDecimal33(rs.getBigDecimal("GenericDecimal33"));
		transactionDataSet.setGenericDecimal34(rs.getBigDecimal("GenericDecimal34"));
		transactionDataSet.setGenericDecimal35(rs.getBigDecimal("GenericDecimal35"));
		transactionDataSet.setGenericDecimal36(rs.getBigDecimal("GenericDecimal36"));
		transactionDataSet.setGenericDecimal37(rs.getBigDecimal("GenericDecimal37"));
		transactionDataSet.setGenericDecimal38(rs.getBigDecimal("GenericDecimal38"));
		transactionDataSet.setGenericDecimal39(rs.getBigDecimal("GenericDecimal39"));
		transactionDataSet.setGenericDecimal40(rs.getBigDecimal("GenericDecimal40"));
		transactionDataSet.setGenericDecimal41(rs.getBigDecimal("GenericDecimal41"));
		transactionDataSet.setGenericDecimal42(rs.getBigDecimal("GenericDecimal42"));
		transactionDataSet.setGenericDecimal43(rs.getBigDecimal("GenericDecimal43"));
		transactionDataSet.setGenericDecimal44(rs.getBigDecimal("GenericDecimal44"));
		transactionDataSet.setGenericDecimal45(rs.getBigDecimal("GenericDecimal45"));
		transactionDataSet.setGenericDecimal46(rs.getBigDecimal("GenericDecimal46"));
		transactionDataSet.setGenericDecimal47(rs.getBigDecimal("GenericDecimal47"));
		transactionDataSet.setGenericDecimal48(rs.getBigDecimal("GenericDecimal48"));
		transactionDataSet.setGenericDecimal49(rs.getBigDecimal("GenericDecimal49"));
		transactionDataSet.setGenericDecimal50(rs.getBigDecimal("GenericDecimal50"));
		transactionDataSet.setGenericDate1(rs.getDate("GenericDate1"));
		transactionDataSet.setGenericDate2(rs.getDate("GenericDate2"));
		transactionDataSet.setGenericDate3(rs.getDate("GenericDate3"));
		transactionDataSet.setGenericDate4(rs.getDate("GenericDate4"));
		transactionDataSet.setGenericDate5(rs.getDate("GenericDate5"));
		transactionDataSet.setGenericDate6(rs.getDate("GenericDate6"));
		transactionDataSet.setGenericDate7(rs.getDate("GenericDate7"));
		transactionDataSet.setGenericDate8(rs.getDate("GenericDate8"));
		transactionDataSet.setGenericDate9(rs.getDate("GenericDate9"));
		transactionDataSet.setGenericDate10(rs.getDate("GenericDate10"));
		transactionDataSet.setGenericDate11(rs.getDate("GenericDate11"));
		transactionDataSet.setGenericDate12(rs.getDate("GenericDate12"));
		transactionDataSet.setGenericDate13(rs.getDate("GenericDate13"));
		transactionDataSet.setGenericDate14(rs.getDate("GenericDate14"));
		transactionDataSet.setGenericDate15(rs.getDate("GenericDate15"));
		transactionDataSet.setGenericDate16(rs.getDate("GenericDate16"));
		transactionDataSet.setGenericDate17(rs.getDate("GenericDate17"));
		transactionDataSet.setGenericDate18(rs.getDate("GenericDate18"));
		transactionDataSet.setGenericDate19(rs.getDate("GenericDate19"));
		transactionDataSet.setGenericDate20(rs.getDate("GenericDate20"));
		transactionDataSet.setGenericDate21(rs.getDate("GenericDate21"));
		transactionDataSet.setGenericDate22(rs.getDate("GenericDate22"));
		transactionDataSet.setGenericDate23(rs.getDate("GenericDate23"));
		transactionDataSet.setGenericDate24(rs.getDate("GenericDate24"));
		transactionDataSet.setGenericDate25(rs.getDate("GenericDate25"));
		transactionDataSet.setGenericDate26(rs.getDate("GenericDate26"));
		transactionDataSet.setGenericDate27(rs.getDate("GenericDate27"));
		transactionDataSet.setGenericDate28(rs.getDate("GenericDate28"));
		transactionDataSet.setGenericDate29(rs.getDate("GenericDate29"));
		transactionDataSet.setGenericDate30(rs.getDate("GenericDate30"));
		transactionDataSet.setGenericDate31(rs.getDate("GenericDate31"));
		transactionDataSet.setGenericDate32(rs.getDate("GenericDate32"));
		transactionDataSet.setGenericDate33(rs.getDate("GenericDate33"));
		transactionDataSet.setGenericDate34(rs.getDate("GenericDate34"));
		transactionDataSet.setGenericDate35(rs.getDate("GenericDate35"));
		transactionDataSet.setGenericDate36(rs.getDate("GenericDate36"));
		transactionDataSet.setGenericDate37(rs.getDate("GenericDate37"));
		transactionDataSet.setGenericDate38(rs.getDate("GenericDate38"));
		transactionDataSet.setGenericDate39(rs.getDate("GenericDate39"));
		transactionDataSet.setGenericDate40(rs.getDate("GenericDate40"));
		transactionDataSet.setGenericDate41(rs.getDate("GenericDate41"));
		transactionDataSet.setGenericDate42(rs.getDate("GenericDate42"));
		transactionDataSet.setGenericDate43(rs.getDate("GenericDate43"));
		transactionDataSet.setGenericDate44(rs.getDate("GenericDate44"));
		transactionDataSet.setGenericDate45(rs.getDate("GenericDate45"));
		transactionDataSet.setGenericDate46(rs.getDate("GenericDate46"));
		transactionDataSet.setGenericDate47(rs.getDate("GenericDate47"));
		transactionDataSet.setGenericDate48(rs.getDate("GenericDate48"));
		transactionDataSet.setGenericDate49(rs.getDate("GenericDate49"));
		transactionDataSet.setGenericDate50(rs.getDate("GenericDate50"));
		transactionDataSet.setActionType(rs.getString("ActionType"));
		transactionDataSet.setActionStatus(rs.getString("ActionStatus"));
		transactionDataSet.setScheduledDatetime(rs.getDate("ScheduledDatetime"));
		transactionDataSet.setGenericString34(rs.getString("GenericString34"));
		transactionDataSet.setStatus(rs.getString("Status"));
		transactionDataSet.setCreatedBy(rs.getString("CreatedBy"));
		transactionDataSet.setCreatedDateTime(rs.getDate("CreatedDateTime"));
		transactionDataSet.setUpdatedBy(rs.getString("UpdatedBy"));
		transactionDataSet.setUpdatedDateTime(rs.getDate("UpdatedDateTime"));
		transactionDataSet.setRemoveDateTime(rs.getDate("RemoveDateTime"));
		transactionDataSet.setValidFrom(rs.getDate("ValidFrom"));
		transactionDataSet.setValidTo (rs.getDate("ValidTo"));
		return transactionDataSet;
	}

}
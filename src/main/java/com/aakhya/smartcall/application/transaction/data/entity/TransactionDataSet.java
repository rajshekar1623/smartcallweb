package com.aakhya.smartcall.application.transaction.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.aakhya.smartcall.application.admin.entity.GenericClassifier;

@Entity
@Table(name = "sc_transactiondataset")
public class TransactionDataSet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8745725893175653491L;
	private Long dataSetId;
	private Long companyId;
	private Long dataSetType;
	private String dataSetDescription;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date dateOfBirth;
	private Long gender;
	private String genderStr;
	private GenericClassifier genderDesc;
	private Long religion;
	private String religionStr;
	private GenericClassifier religionDesc;
	private Long socialCategory;
	private String socialCategoryStr;
	private GenericClassifier socialCategoryDesc;
	private String aadhaarNumber;
	private String voterId;
	private String drivingLicenseNumber;
	private String panCardNumber;
	private Long grampanchayat;
	private String grampanchayatStr;
	private GenericClassifier grampanchayatDesc;
	private Long village;
	private String villageStr;
	private GenericClassifier villageDesc;
	private String branchCode;
	private String actionType;
	private String actionStatus;
	private Date scheduledDatetime;
	private String status;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private Date removeDateTime;
	private Date validFrom;
	private Date validTo;
	private boolean assign;
	private String genericString1;
	private String genericString2;
	private String genericString3;
	private String genericString4;
	private String genericString5;
	private String genericString6;
	private String genericString7;
	private String genericString8;
	private String genericString9;
	private String genericString10;
	private String genericString11;
	private String genericString12;
	private String genericString13;
	private String genericString14;
	private String genericString15;
	private String genericString16;
	private String genericString17;
	private String genericString18;
	private String genericString19;
	private String genericString20;
	private String genericString21;
	private String genericString22;
	private String genericString23;
	private String genericString24;
	private String genericString25;
	private String genericString26;
	private String genericString27;
	private String genericString28;
	private String genericString29;
	private String genericString30;
	private String genericString31;
	private String genericString32;
	private String genericString33;
	private String genericString34;
	private String genericString35;
	private String genericString36;
	private String genericString37;
	private String genericString38;
	private String genericString39;
	private String genericString40;
	private String genericString41;
	private String genericString42;
	private String genericString43;
	private String genericString44;
	private String genericString45;
	private String genericString46;
	private String genericString47;
	private String genericString48;
	private String genericString49;
	private String genericString50;
	private Long genericNumber1;
	private String mobileNumber;
	private Long genericNumber2;
	private String loanAccountNumber;
	private Long genericNumber3;
	private String pincode;
	private Long genericNumber4;
	private Long genericNumber5;
	private Long genericNumber6;
	private Long genericNumber7;
	private Long genericNumber8;
	private Long genericNumber9;
	private Long genericNumber10;
	private Long genericNumber11;
	private Long genericNumber12;
	private Long genericNumber13;
	private Long genericNumber14;
	private Long genericNumber15;
	private Long genericNumber16;
	private Long genericNumber17;
	private Long genericNumber18;
	private Long genericNumber19;
	private Long genericNumber20;
	private Long genericNumber21;
	private Long genericNumber22;
	private Long genericNumber23;
	private Long genericNumber24;
	private Long genericNumber25;
	private Long genericNumber26;
	private Long genericNumber27;
	private Long genericNumber28;
	private Long genericNumber29;
	private Long genericNumber30;
	private Long genericNumber31;
	private Long genericNumber32;
	private Long genericNumber33;
	private Long genericNumber34;
	private Long genericNumber35;
	private Long genericNumber36;
	private Long genericNumber37;
	private Long genericNumber38;
	private Long genericNumber39;
	private Long genericNumber40;
	private Long genericNumber41;
	private Long genericNumber42;
	private Long genericNumber43;
	private Long genericNumber44;
	private Long genericNumber45;
	private Long genericNumber46;
	private Long genericNumber47;
	private Long genericNumber48;
	private Long genericNumber49;
	private Long genericNumber50;
	private BigDecimal genericDecimal1;
	private BigDecimal genericDecimal2;
	private BigDecimal genericDecimal3;
	private BigDecimal genericDecimal4;
	private BigDecimal genericDecimal5;
	private BigDecimal genericDecimal6;
	private BigDecimal genericDecimal7;
	private BigDecimal genericDecimal8;
	private BigDecimal genericDecimal9;
	private BigDecimal genericDecimal10;
	private BigDecimal genericDecimal11;
	private BigDecimal genericDecimal12;
	private BigDecimal genericDecimal13;
	private BigDecimal genericDecimal14;
	private BigDecimal genericDecimal15;
	private BigDecimal genericDecimal16;
	private BigDecimal genericDecimal17;
	private BigDecimal genericDecimal18;
	private BigDecimal genericDecimal19;
	private BigDecimal genericDecimal20;
	private BigDecimal genericDecimal21;
	private BigDecimal genericDecimal22;
	private BigDecimal genericDecimal23;
	private BigDecimal genericDecimal24;
	private BigDecimal genericDecimal25;
	private BigDecimal genericDecimal26;
	private BigDecimal genericDecimal27;
	private BigDecimal genericDecimal28;
	private BigDecimal genericDecimal29;
	private BigDecimal genericDecimal30;
	private BigDecimal genericDecimal31;
	private BigDecimal genericDecimal32;
	private BigDecimal genericDecimal33;
	private BigDecimal genericDecimal34;
	private BigDecimal genericDecimal35;
	private BigDecimal genericDecimal36;
	private BigDecimal genericDecimal37;
	private BigDecimal genericDecimal38;
	private BigDecimal genericDecimal39;
	private BigDecimal genericDecimal40;
	private BigDecimal genericDecimal41;
	private BigDecimal genericDecimal42;
	private BigDecimal genericDecimal43;
	private BigDecimal genericDecimal44;
	private BigDecimal genericDecimal45;
	private BigDecimal genericDecimal46;
	private BigDecimal genericDecimal47;
	private BigDecimal genericDecimal48;
	private BigDecimal genericDecimal49;
	private BigDecimal genericDecimal50;
	private Date genericDate1;
	private Date genericDate2;
	private Date genericDate3;
	private Date genericDate4;
	private Date genericDate5;
	private Date genericDate6;
	private Date genericDate7;
	private Date genericDate8;
	private Date genericDate9;
	private Date genericDate10;
	private Date genericDate11;
	private Date genericDate12;
	private Date genericDate13;
	private Date genericDate14;
	private Date genericDate15;
	private Date genericDate16;
	private Date genericDate17;
	private Date genericDate18;
	private Date genericDate19;
	private Date genericDate20;
	private Date genericDate21;
	private Date genericDate22;
	private Date genericDate23;
	private Date genericDate24;
	private Date genericDate25;
	private Date genericDate26;
	private Date genericDate27;
	private Date genericDate28;
	private Date genericDate29;
	private Date genericDate30;
	private Date genericDate31;
	private Date genericDate32;
	private Date genericDate33;
	private Date genericDate34;
	private Date genericDate35;
	private Date genericDate36;
	private Date genericDate37;
	private Date genericDate38;
	private Date genericDate39;
	private Date genericDate40;
	private Date genericDate41;
	private Date genericDate42;
	private Date genericDate43;
	private Date genericDate44;
	private Date genericDate45;
	private Date genericDate46;
	private Date genericDate47;
	private Date genericDate48;
	private Date genericDate49;
	private Date genericDate50;
	private String assignedDate;
	private String assignedTo;

	@Id
	@Column(name = "datasetid")
	public Long getDataSetId() {
		return dataSetId;
	}

	public void setDataSetId(Long dataSetId) {
		this.dataSetId = dataSetId;
	}

	@Column(name = "companyid")
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Column(name = "datasettype")
	public Long getDataSetType() {
		return dataSetType;
	}

	public void setDataSetType(Long dataSetType) {
		this.dataSetType = dataSetType;
	}

	@Column(name = "datasetdescription")
	public String getDataSetDescription() {
		return dataSetDescription;
	}

	public void setDataSetDescription(String dataSetDescription) {
		this.dataSetDescription = dataSetDescription;
	}

	@Column(name = "firstname")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "middlename")
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "lastname")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "dateofbirth")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "gender")
	public Long getGender() {
		return gender;
	}

	public void setGender(Long gender) {
		if(null != gender && null != genderStr)
			this.genderDesc = new GenericClassifier(gender,genderStr);
		this.gender = gender;
	}

	@Formula(value = "dbo.fn_getGenClass(gender,companyId)")
	public String getGenderStr() {
		return genderStr;
	}

	public void setGenderStr(String genderStr) {
		if(null != gender && null != genderStr)
			this.genderDesc = new GenericClassifier(gender,genderStr);
		this.genderStr = genderStr;
	}

	@Transient
	public GenericClassifier getGenderDesc() {
		return genderDesc;
	}

	public void setGenderDesc(GenericClassifier genderDesc) {
		if(null != genderDesc) {
			this.gender = genderDesc.getGenericId();
			this.genderStr = genderDesc.getDescription();
		}
		this.genderDesc = genderDesc;
	}

	@Column(name = "religion")
	public Long getReligion() {
		return religion;
	}

	public void setReligion(Long religion) {
		if(null != religion && null !=religionStr)
			this.religionDesc = new GenericClassifier(religion,religionStr);
		this.religion = religion;
	}

	@Formula(value = "dbo.fn_getGenClass(religion,companyId)")
	public String getReligionStr() {
		return religionStr;
	}

	public void setReligionStr(String religionStr) {
		if(null != religion && null !=religionStr)
			this.religionDesc = new GenericClassifier(religion,religionStr);
		this.religionStr = religionStr;
	}

	@Transient
	public GenericClassifier getReligionDesc() {
		return religionDesc;
	}

	public void setReligionDesc(GenericClassifier religionDesc) {
		if(null != religionDesc) {
			this.religion = religionDesc.getGenericId();
			this.religionStr = religionDesc.getDescription();
		}
		this.religionDesc = religionDesc;
	}

	@Column(name = "socialcategory")
	public Long getSocialCategory() {
		return socialCategory;
	}

	public void setSocialCategory(Long socialCategory) {
		if(null != socialCategory && null != socialCategoryStr)
			this.socialCategoryDesc = new GenericClassifier(socialCategory,socialCategoryStr);
		this.socialCategory = socialCategory;
	}

	@Formula(value = "dbo.fn_getGenClass(socialCategory,companyId)")
	public String getSocialCategoryStr() {
		return socialCategoryStr;
	}

	public void setSocialCategoryStr(String socialCategoryStr) {
		if(null != socialCategory && null != socialCategoryStr)
			this.socialCategoryDesc = new GenericClassifier(socialCategory,socialCategoryStr);
		this.socialCategoryStr = socialCategoryStr;
	}

	@Transient
	public GenericClassifier getSocialCategoryDesc() {
		return socialCategoryDesc;
	}

	public void setSocialCategoryDesc(GenericClassifier socialCategoryDesc) {
		if(null != socialCategoryDesc) {
			this.socialCategory = socialCategoryDesc.getGenericId();
			this.socialCategoryStr = socialCategoryDesc.getDescription();
		}
		this.socialCategoryDesc = socialCategoryDesc;
	}

	@Column(name = "aadhaarnumber")
	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	@Column(name = "voterid")
	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	@Column(name = "drivinglicensenumber")
	public String getDrivingLicenseNumber() {
		return drivingLicenseNumber;
	}

	public void setDrivingLicenseNumber(String drivingLicenseNumber) {
		this.drivingLicenseNumber = drivingLicenseNumber;
	}

	@Column(name = "pancardnumber")
	public String getPanCardNumber() {
		return panCardNumber;
	}

	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}

	@Column(name = "grampanchayat")
	public Long getGrampanchayat() {
		return grampanchayat;
	}

	public void setGrampanchayat(Long grampanchayat) {
		if(null != grampanchayat && null != grampanchayatStr)
			this.grampanchayatDesc = new GenericClassifier(grampanchayat,grampanchayatStr);
		this.grampanchayat = grampanchayat;
	}

	@Formula(value = "dbo.fn_getGenClass(grampanchayat,companyId)")
	public String getGrampanchayatStr() {
		return grampanchayatStr;
	}

	public void setGrampanchayatStr(String grampanchayatStr) {
		if(null != grampanchayat && null != grampanchayatStr)
			this.grampanchayatDesc = new GenericClassifier(grampanchayat,grampanchayatStr);
		this.grampanchayatStr = grampanchayatStr;
	}

	@Transient
	public GenericClassifier getGrampanchayatDesc() {
		return grampanchayatDesc;
	}

	@Transient
	public void setGrampanchayatDesc(GenericClassifier grampanchayatDesc) {
		if(null != grampanchayatDesc) {
			this.grampanchayat = grampanchayatDesc.getGenericId();
			this.grampanchayatStr = grampanchayatDesc.getDescription();
		}
		this.grampanchayatDesc = grampanchayatDesc;
	}

	@Column(name = "village")
	public Long getVillage() {
		return village;
	}

	public void setVillage(Long village) {
		this.village = village;
	}

	@Formula(value = "dbo.fn_getGenClass(village,companyId)")
	public String getVillageStr() {
		return villageStr;
	}

	public void setVillageStr(String villageStr) {
		if(null != village && null != villageStr)
			this.villageDesc = new GenericClassifier(village,villageStr);
		this.villageStr = villageStr;
	}

	@Transient
	public GenericClassifier getVillageDesc() {
		return villageDesc;
	}

	public void setVillageDesc(GenericClassifier villageDesc) {
		if(null != villageDesc) {
			this.village = villageDesc.getGenericId();
			this.villageStr = villageDesc.getDescription();
		}
		this.villageDesc = villageDesc;
	}

	@Column(name = "branchcode")
	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	@Column(name = "actiontype")
	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	@Column(name = "actionstatus")
	public String getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	@Column(name = "scheduleddatetime")
	public Date getScheduledDatetime() {
		return scheduledDatetime;
	}

	public void setScheduledDatetime(Date scheduledDatetime) {
		this.scheduledDatetime = scheduledDatetime;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "createdby")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "createddatetime")
	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	@Column(name = "updatedby")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "updateddatetime")
	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	@Column(name = "removedatetime")
	public Date getRemoveDateTime() {
		return removeDateTime;
	}

	public void setRemoveDateTime(Date removeDateTime) {
		this.removeDateTime = removeDateTime;
	}

	@Column(name = "validfrom")
	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	@Column(name = "validto")
	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	@Transient
	public boolean isAssign() {
		return assign;
	}

	public void setAssign(boolean assign) {
		this.assign = assign;
	}

	@Column(name = "genericstring1")
	public String getGenericString1() {
		return genericString1;
	}

	public void setGenericString1(String genericString1) {
		this.genericString1 = genericString1;
	}

	@Column(name = "genericstring2")
	public String getGenericString2() {
		return genericString2;
	}

	public void setGenericString2(String genericString2) {
		this.genericString2 = genericString2;
	}

	@Column(name = "genericstring3")
	public String getGenericString3() {
		return genericString3;
	}

	public void setGenericString3(String genericString3) {
		this.genericString3 = genericString3;
	}

	@Column(name = "genericstring4")
	public String getGenericString4() {
		return genericString4;
	}

	public void setGenericString4(String genericString4) {
		this.genericString4 = genericString4;
	}

	@Column(name = "genericstring5")
	public String getGenericString5() {
		return genericString5;
	}

	public void setGenericString5(String genericString5) {
		this.genericString5 = genericString5;
	}

	@Column(name = "genericstring6")
	public String getGenericString6() {
		return genericString6;
	}

	public void setGenericString6(String genericString6) {
		this.genericString6 = genericString6;
	}

	@Column(name = "genericstring7")
	public String getGenericString7() {
		return genericString7;
	}

	public void setGenericString7(String genericString7) {
		this.genericString7 = genericString7;
	}

	@Column(name = "genericstring8")
	public String getGenericString8() {
		return genericString8;
	}

	public void setGenericString8(String genericString8) {
		this.genericString8 = genericString8;
	}

	@Column(name = "genericstring9")
	public String getGenericString9() {
		return genericString9;
	}

	public void setGenericString9(String genericString9) {
		this.genericString9 = genericString9;
	}

	@Column(name = "genericstring10")
	public String getGenericString10() {
		return genericString10;
	}

	public void setGenericString10(String genericString10) {
		this.genericString10 = genericString10;
	}

	@Column(name = "genericstring11")
	public String getGenericString11() {
		return genericString11;
	}

	public void setGenericString11(String genericString11) {
		this.genericString11 = genericString11;
	}

	@Column(name = "genericstring12")
	public String getGenericString12() {
		return genericString12;
	}

	public void setGenericString12(String genericString12) {
		this.genericString12 = genericString12;
	}

	@Column(name = "genericstring13")
	public String getGenericString13() {
		return genericString13;
	}

	public void setGenericString13(String genericString13) {
		this.genericString13 = genericString13;
	}

	@Column(name = "genericstring14")
	public String getGenericString14() {
		return genericString14;
	}

	public void setGenericString14(String genericString14) {
		this.genericString14 = genericString14;
	}

	@Column(name = "genericstring15")
	public String getGenericString15() {
		return genericString15;
	}

	public void setGenericString15(String genericString15) {
		this.genericString15 = genericString15;
	}

	@Column(name = "genericstring16")
	public String getGenericString16() {
		return genericString16;
	}

	public void setGenericString16(String genericString16) {
		this.genericString16 = genericString16;
	}

	@Column(name = "genericstring17")
	public String getGenericString17() {
		return genericString17;
	}

	public void setGenericString17(String genericString17) {
		this.genericString17 = genericString17;
	}

	@Column(name = "genericstring18")
	public String getGenericString18() {
		return genericString18;
	}

	public void setGenericString18(String genericString18) {
		this.genericString18 = genericString18;
	}

	@Column(name = "genericstring19")
	public String getGenericString19() {
		return genericString19;
	}

	public void setGenericString19(String genericString19) {
		this.genericString19 = genericString19;
	}

	@Column(name = "genericstring20")
	public String getGenericString20() {
		return genericString20;
	}

	public void setGenericString20(String genericString20) {
		this.genericString20 = genericString20;
	}

	@Column(name = "genericstring21")
	public String getGenericString21() {
		return genericString21;
	}

	public void setGenericString21(String genericString21) {
		this.genericString21 = genericString21;
	}

	@Column(name = "genericstring22")
	public String getGenericString22() {
		return genericString22;
	}

	public void setGenericString22(String genericString22) {
		this.genericString22 = genericString22;
	}

	@Column(name = "genericstring23")
	public String getGenericString23() {
		return genericString23;
	}

	public void setGenericString23(String genericString23) {
		this.genericString23 = genericString23;
	}

	@Column(name = "genericstring24")
	public String getGenericString24() {
		return genericString24;
	}

	public void setGenericString24(String genericString24) {
		this.genericString24 = genericString24;
	}

	@Column(name = "genericstring25")
	public String getGenericString25() {
		return genericString25;
	}

	public void setGenericString25(String genericString25) {
		this.genericString25 = genericString25;
	}

	@Column(name = "genericstring26")
	public String getGenericString26() {
		return genericString26;
	}

	public void setGenericString26(String genericString26) {
		this.genericString26 = genericString26;
	}

	@Column(name = "genericstring27")
	public String getGenericString27() {
		return genericString27;
	}

	public void setGenericString27(String genericString27) {
		this.genericString27 = genericString27;
	}

	@Column(name = "genericstring28")
	public String getGenericString28() {
		return genericString28;
	}

	public void setGenericString28(String genericString28) {
		this.genericString28 = genericString28;
	}

	@Column(name = "genericstring29")
	public String getGenericString29() {
		return genericString29;
	}

	public void setGenericString29(String genericString29) {
		this.genericString29 = genericString29;
	}

	@Column(name = "genericstring30")
	public String getGenericString30() {
		return genericString30;
	}

	public void setGenericString30(String genericString30) {
		this.genericString30 = genericString30;
	}

	@Column(name = "genericstring31")
	public String getGenericString31() {
		return genericString31;
	}

	public void setGenericString31(String genericString31) {
		this.genericString31 = genericString31;
	}

	@Column(name = "genericstring32")
	public String getGenericString32() {
		return genericString32;
	}

	public void setGenericString32(String genericString32) {
		this.genericString32 = genericString32;
	}

	@Column(name = "genericstring33")
	public String getGenericString33() {
		return genericString33;
	}

	public void setGenericString33(String genericString33) {
		this.genericString33 = genericString33;
	}

	@Column(name = "genericstring34")
	public String getGenericString34() {
		return genericString34;
	}

	public void setGenericString34(String genericString34) {
		this.genericString34 = genericString34;
	}

	@Column(name = "genericstring35")
	public String getGenericString35() {
		return genericString35;
	}

	public void setGenericString35(String genericString35) {
		this.genericString35 = genericString35;
	}

	@Column(name = "genericstring36")
	public String getGenericString36() {
		return genericString36;
	}

	public void setGenericString36(String genericString36) {
		this.genericString36 = genericString36;
	}

	@Column(name = "genericstring37")
	public String getGenericString37() {
		return genericString37;
	}

	public void setGenericString37(String genericString37) {
		this.genericString37 = genericString37;
	}

	@Column(name = "genericstring38")
	public String getGenericString38() {
		return genericString38;
	}

	public void setGenericString38(String genericString38) {
		this.genericString38 = genericString38;
	}

	@Column(name = "genericstring39")
	public String getGenericString39() {
		return genericString39;
	}

	public void setGenericString39(String genericString39) {
		this.genericString39 = genericString39;
	}

	@Column(name = "genericstring40")
	public String getGenericString40() {
		return genericString40;
	}

	public void setGenericString40(String genericString40) {
		this.genericString40 = genericString40;
	}

	@Column(name = "genericstring41")
	public String getGenericString41() {
		return genericString41;
	}

	public void setGenericString41(String genericString41) {
		this.genericString41 = genericString41;
	}

	@Column(name = "genericstring42")
	public String getGenericString42() {
		return genericString42;
	}

	public void setGenericString42(String genericString42) {
		this.genericString42 = genericString42;
	}

	@Column(name = "genericstring43")
	public String getGenericString43() {
		return genericString43;
	}

	public void setGenericString43(String genericString43) {
		this.genericString43 = genericString43;
	}

	@Column(name = "genericstring44")
	public String getGenericString44() {
		return genericString44;
	}

	public void setGenericString44(String genericString44) {
		this.genericString44 = genericString44;
	}

	@Column(name = "genericstring45")
	public String getGenericString45() {
		return genericString45;
	}

	public void setGenericString45(String genericString45) {
		this.genericString45 = genericString45;
	}

	@Column(name = "genericstring46")
	public String getGenericString46() {
		return genericString46;
	}

	public void setGenericString46(String genericString46) {
		this.genericString46 = genericString46;
	}

	@Column(name = "genericstring47")
	public String getGenericString47() {
		return genericString47;
	}

	public void setGenericString47(String genericString47) {
		this.genericString47 = genericString47;
	}

	@Column(name = "genericstring48")
	public String getGenericString48() {
		return genericString48;
	}

	public void setGenericString48(String genericString48) {
		this.genericString48 = genericString48;
	}

	@Column(name = "genericstring49")
	public String getGenericString49() {
		return genericString49;
	}

	public void setGenericString49(String genericString49) {
		this.genericString49 = genericString49;
	}

	@Column(name = "genericstring50")
	public String getGenericString50() {
		return genericString50;
	}

	public void setGenericString50(String genericString50) {
		this.genericString50 = genericString50;
	}

	@Column(name = "genericnumber1")
	public Long getGenericNumber1() {
		return genericNumber1;
	}

	public void setGenericNumber1(Long genericNumber1) {
		if(null != genericNumber1)
			this.mobileNumber = String.valueOf(genericNumber1);
		this.genericNumber1 = genericNumber1;
	}

	@Transient
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		if(null != mobileNumber) {
			try {
				Long mobileNumberLongVal = Long.valueOf(mobileNumber);
				this.genericNumber1 = mobileNumberLongVal;
			}catch(NumberFormatException e) {
				this.genericNumber1 = null;
			}
		}
		this.mobileNumber = mobileNumber;
	}

	@Column(name = "genericnumber2")
	public Long getGenericNumber2() {
		return genericNumber2;
	}

	public void setGenericNumber2(Long genericNumber2) {
		if(null != genericNumber2)
			this.loanAccountNumber = String.valueOf(genericNumber2);
		this.genericNumber2 = genericNumber2;
	}

	@Transient
	public String getLoanAccountNumber() {
		return loanAccountNumber;
	}

	public void setLoanAccountNumber(String loanAccountNumber) {
		if(null != loanAccountNumber) {
			try {
				Long loanAcNumber = Long.valueOf(loanAccountNumber);
				this.genericNumber2 = loanAcNumber;
			}catch(NumberFormatException e) {
				this.genericNumber2 = null;
			}
		}
		this.loanAccountNumber = loanAccountNumber;
	}

	@Column(name = "genericnumber3")
	public Long getGenericNumber3() {
		return genericNumber3;
	}

	public void setGenericNumber3(Long genericNumber3) {
		this.genericNumber3 = genericNumber3;
	}

	@Transient
	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		if(null != pincode) {
			try {
				Long pincodeLongVal = Long.valueOf(pincode);
				this.genericNumber4 = pincodeLongVal;
			}catch(NumberFormatException e) {
				this.genericNumber4 = null;
			}
		}
		this.pincode = pincode;
	}

	@Column(name = "genericnumber4")
	public Long getGenericNumber4() {
		return genericNumber4;
	}

	public void setGenericNumber4(Long genericNumber4) {
		if(null != genericNumber4)
			this.pincode = String.valueOf(genericNumber4);
		this.genericNumber4 = genericNumber4;
	}

	@Column(name = "genericnumber5")
	public Long getGenericNumber5() {
		return genericNumber5;
	}

	public void setGenericNumber5(Long genericNumber5) {
		this.genericNumber5 = genericNumber5;
	}

	@Column(name = "genericnumber6")
	public Long getGenericNumber6() {
		return genericNumber6;
	}

	public void setGenericNumber6(Long genericNumber6) {
		this.genericNumber6 = genericNumber6;
	}

	@Column(name = "genericnumber7")
	public Long getGenericNumber7() {
		return genericNumber7;
	}

	public void setGenericNumber7(Long genericNumber7) {
		this.genericNumber7 = genericNumber7;
	}

	@Column(name = "genericnumber8")
	public Long getGenericNumber8() {
		return genericNumber8;
	}

	public void setGenericNumber8(Long genericNumber8) {
		this.genericNumber8 = genericNumber8;
	}

	@Column(name = "genericnumber9")
	public Long getGenericNumber9() {
		return genericNumber9;
	}

	public void setGenericNumber9(Long genericNumber9) {
		this.genericNumber9 = genericNumber9;
	}

	@Column(name = "genericnumber10")
	public Long getGenericNumber10() {
		return genericNumber10;
	}

	public void setGenericNumber10(Long genericNumber10) {
		this.genericNumber10 = genericNumber10;
	}

	@Column(name = "genericnumber11")
	public Long getGenericNumber11() {
		return genericNumber11;
	}

	public void setGenericNumber11(Long genericNumber11) {
		this.genericNumber11 = genericNumber11;
	}

	@Column(name = "genericnumber12")
	public Long getGenericNumber12() {
		return genericNumber12;
	}

	public void setGenericNumber12(Long genericNumber12) {
		this.genericNumber12 = genericNumber12;
	}

	@Column(name = "genericnumber13")
	public Long getGenericNumber13() {
		return genericNumber13;
	}

	public void setGenericNumber13(Long genericNumber13) {
		this.genericNumber13 = genericNumber13;
	}

	@Column(name = "genericnumber14")
	public Long getGenericNumber14() {
		return genericNumber14;
	}

	public void setGenericNumber14(Long genericNumber14) {
		this.genericNumber14 = genericNumber14;
	}

	@Column(name = "genericnumber15")
	public Long getGenericNumber15() {
		return genericNumber15;
	}

	public void setGenericNumber15(Long genericNumber15) {
		this.genericNumber15 = genericNumber15;
	}

	@Column(name = "genericnumber16")
	public Long getGenericNumber16() {
		return genericNumber16;
	}

	public void setGenericNumber16(Long genericNumber16) {
		this.genericNumber16 = genericNumber16;
	}

	@Column(name = "genericnumber17")
	public Long getGenericNumber17() {
		return genericNumber17;
	}

	public void setGenericNumber17(Long genericNumber17) {
		this.genericNumber17 = genericNumber17;
	}

	@Column(name = "genericnumber18")
	public Long getGenericNumber18() {
		return genericNumber18;
	}

	public void setGenericNumber18(Long genericNumber18) {
		this.genericNumber18 = genericNumber18;
	}

	@Column(name = "genericnumber19")
	public Long getGenericNumber19() {
		return genericNumber19;
	}

	public void setGenericNumber19(Long genericNumber19) {
		this.genericNumber19 = genericNumber19;
	}

	@Column(name = "genericnumber20")
	public Long getGenericNumber20() {
		return genericNumber20;
	}

	public void setGenericNumber20(Long genericNumber20) {
		this.genericNumber20 = genericNumber20;
	}

	@Column(name = "genericnumber21")
	public Long getGenericNumber21() {
		return genericNumber21;
	}

	public void setGenericNumber21(Long genericNumber21) {
		this.genericNumber21 = genericNumber21;
	}

	@Column(name = "genericnumber22")
	public Long getGenericNumber22() {
		return genericNumber22;
	}

	public void setGenericNumber22(Long genericNumber22) {
		this.genericNumber22 = genericNumber22;
	}

	@Column(name = "genericnumber23")
	public Long getGenericNumber23() {
		return genericNumber23;
	}

	public void setGenericNumber23(Long genericNumber23) {
		this.genericNumber23 = genericNumber23;
	}

	@Column(name = "genericnumber24")
	public Long getGenericNumber24() {
		return genericNumber24;
	}

	public void setGenericNumber24(Long genericNumber24) {
		this.genericNumber24 = genericNumber24;
	}

	@Column(name = "genericnumber25")
	public Long getGenericNumber25() {
		return genericNumber25;
	}

	public void setGenericNumber25(Long genericNumber25) {
		this.genericNumber25 = genericNumber25;
	}

	@Column(name = "genericnumber26")
	public Long getGenericNumber26() {
		return genericNumber26;
	}

	public void setGenericNumber26(Long genericNumber26) {
		this.genericNumber26 = genericNumber26;
	}

	@Column(name = "genericnumber27")
	public Long getGenericNumber27() {
		return genericNumber27;
	}

	public void setGenericNumber27(Long genericNumber27) {
		this.genericNumber27 = genericNumber27;
	}

	@Column(name = "genericnumber28")
	public Long getGenericNumber28() {
		return genericNumber28;
	}

	public void setGenericNumber28(Long genericNumber28) {
		this.genericNumber28 = genericNumber28;
	}

	@Column(name = "genericnumber29")
	public Long getGenericNumber29() {
		return genericNumber29;
	}

	public void setGenericNumber29(Long genericNumber29) {
		this.genericNumber29 = genericNumber29;
	}

	@Column(name = "genericnumber30")
	public Long getGenericNumber30() {
		return genericNumber30;
	}

	public void setGenericNumber30(Long genericNumber30) {
		this.genericNumber30 = genericNumber30;
	}

	@Column(name = "genericnumber31")
	public Long getGenericNumber31() {
		return genericNumber31;
	}

	public void setGenericNumber31(Long genericNumber31) {
		this.genericNumber31 = genericNumber31;
	}

	@Column(name = "genericnumber32")
	public Long getGenericNumber32() {
		return genericNumber32;
	}

	public void setGenericNumber32(Long genericNumber32) {
		this.genericNumber32 = genericNumber32;
	}

	@Column(name = "genericnumber33")
	public Long getGenericNumber33() {
		return genericNumber33;
	}

	public void setGenericNumber33(Long genericNumber33) {
		this.genericNumber33 = genericNumber33;
	}

	@Column(name = "genericnumber34")
	public Long getGenericNumber34() {
		return genericNumber34;
	}

	public void setGenericNumber34(Long genericNumber34) {
		this.genericNumber34 = genericNumber34;
	}

	@Column(name = "genericnumber35")
	public Long getGenericNumber35() {
		return genericNumber35;
	}

	public void setGenericNumber35(Long genericNumber35) {
		this.genericNumber35 = genericNumber35;
	}

	@Column(name = "genericnumber36")
	public Long getGenericNumber36() {
		return genericNumber36;
	}

	public void setGenericNumber36(Long genericNumber36) {
		this.genericNumber36 = genericNumber36;
	}

	@Column(name = "genericnumber37")
	public Long getGenericNumber37() {
		return genericNumber37;
	}

	public void setGenericNumber37(Long genericNumber37) {
		this.genericNumber37 = genericNumber37;
	}

	@Column(name = "genericnumber38")
	public Long getGenericNumber38() {
		return genericNumber38;
	}

	public void setGenericNumber38(Long genericNumber38) {
		this.genericNumber38 = genericNumber38;
	}

	@Column(name = "genericnumber39")
	public Long getGenericNumber39() {
		return genericNumber39;
	}

	public void setGenericNumber39(Long genericNumber39) {
		this.genericNumber39 = genericNumber39;
	}

	@Column(name = "genericnumber40")
	public Long getGenericNumber40() {
		return genericNumber40;
	}

	public void setGenericNumber40(Long genericNumber40) {
		this.genericNumber40 = genericNumber40;
	}

	@Column(name = "genericnumber41")
	public Long getGenericNumber41() {
		return genericNumber41;
	}

	public void setGenericNumber41(Long genericNumber41) {
		this.genericNumber41 = genericNumber41;
	}

	@Column(name = "genericnumber42")
	public Long getGenericNumber42() {
		return genericNumber42;
	}

	public void setGenericNumber42(Long genericNumber42) {
		this.genericNumber42 = genericNumber42;
	}

	@Column(name = "genericnumber43")
	public Long getGenericNumber43() {
		return genericNumber43;
	}

	public void setGenericNumber43(Long genericNumber43) {
		this.genericNumber43 = genericNumber43;
	}

	@Column(name = "genericnumber44")
	public Long getGenericNumber44() {
		return genericNumber44;
	}

	public void setGenericNumber44(Long genericNumber44) {
		this.genericNumber44 = genericNumber44;
	}

	@Column(name = "genericnumber45")
	public Long getGenericNumber45() {
		return genericNumber45;
	}

	public void setGenericNumber45(Long genericNumber45) {
		this.genericNumber45 = genericNumber45;
	}

	@Column(name = "genericnumber46")
	public Long getGenericNumber46() {
		return genericNumber46;
	}

	public void setGenericNumber46(Long genericNumber46) {
		this.genericNumber46 = genericNumber46;
	}

	@Column(name = "genericnumber47")
	public Long getGenericNumber47() {
		return genericNumber47;
	}

	public void setGenericNumber47(Long genericNumber47) {
		this.genericNumber47 = genericNumber47;
	}

	@Column(name = "genericnumber48")
	public Long getGenericNumber48() {
		return genericNumber48;
	}

	public void setGenericNumber48(Long genericNumber48) {
		this.genericNumber48 = genericNumber48;
	}

	@Column(name = "genericnumber49")
	public Long getGenericNumber49() {
		return genericNumber49;
	}

	public void setGenericNumber49(Long genericNumber49) {
		this.genericNumber49 = genericNumber49;
	}

	@Column(name = "genericnumber50")
	public Long getGenericNumber50() {
		return genericNumber50;
	}

	public void setGenericNumber50(Long genericNumber50) {
		this.genericNumber50 = genericNumber50;
	}

	@Column(name = "genericdecimal1")
	public BigDecimal getGenericDecimal1() {
		return genericDecimal1;
	}

	public void setGenericDecimal1(BigDecimal genericDecimal1) {
		this.genericDecimal1 = genericDecimal1;
	}

	@Column(name = "genericdecimal2")
	public BigDecimal getGenericDecimal2() {
		return genericDecimal2;
	}

	public void setGenericDecimal2(BigDecimal genericDecimal2) {
		this.genericDecimal2 = genericDecimal2;
	}

	@Column(name = "genericdecimal3")
	public BigDecimal getGenericDecimal3() {
		return genericDecimal3;
	}

	public void setGenericDecimal3(BigDecimal genericDecimal3) {
		this.genericDecimal3 = genericDecimal3;
	}

	@Column(name = "genericdecimal4")
	public BigDecimal getGenericDecimal4() {
		return genericDecimal4;
	}

	public void setGenericDecimal4(BigDecimal genericDecimal4) {
		this.genericDecimal4 = genericDecimal4;
	}

	@Column(name = "genericdecimal5")
	public BigDecimal getGenericDecimal5() {
		return genericDecimal5;
	}

	public void setGenericDecimal5(BigDecimal genericDecimal5) {
		this.genericDecimal5 = genericDecimal5;
	}

	@Column(name = "genericdecimal6")
	public BigDecimal getGenericDecimal6() {
		return genericDecimal6;
	}

	public void setGenericDecimal6(BigDecimal genericDecimal6) {
		this.genericDecimal6 = genericDecimal6;
	}

	@Column(name = "genericdecimal7")
	public BigDecimal getGenericDecimal7() {
		return genericDecimal7;
	}

	public void setGenericDecimal7(BigDecimal genericDecimal7) {
		this.genericDecimal7 = genericDecimal7;
	}

	@Column(name = "genericdecimal8")
	public BigDecimal getGenericDecimal8() {
		return genericDecimal8;
	}

	public void setGenericDecimal8(BigDecimal genericDecimal8) {
		this.genericDecimal8 = genericDecimal8;
	}

	@Column(name = "genericdecimal9")
	public BigDecimal getGenericDecimal9() {
		return genericDecimal9;
	}

	public void setGenericDecimal9(BigDecimal genericDecimal9) {
		this.genericDecimal9 = genericDecimal9;
	}

	@Column(name = "genericdecimal10")
	public BigDecimal getGenericDecimal10() {
		return genericDecimal10;
	}

	public void setGenericDecimal10(BigDecimal genericDecimal10) {
		this.genericDecimal10 = genericDecimal10;
	}

	@Column(name = "genericdecimal11")
	public BigDecimal getGenericDecimal11() {
		return genericDecimal11;
	}

	public void setGenericDecimal11(BigDecimal genericDecimal11) {
		this.genericDecimal11 = genericDecimal11;
	}

	@Column(name = "genericdecimal12")
	public BigDecimal getGenericDecimal12() {
		return genericDecimal12;
	}

	public void setGenericDecimal12(BigDecimal genericDecimal12) {
		this.genericDecimal12 = genericDecimal12;
	}

	@Column(name = "genericdecimal13")
	public BigDecimal getGenericDecimal13() {
		return genericDecimal13;
	}

	public void setGenericDecimal13(BigDecimal genericDecimal13) {
		this.genericDecimal13 = genericDecimal13;
	}

	@Column(name = "genericdecimal14")
	public BigDecimal getGenericDecimal14() {
		return genericDecimal14;
	}

	public void setGenericDecimal14(BigDecimal genericDecimal14) {
		this.genericDecimal14 = genericDecimal14;
	}

	@Column(name = "genericdecimal15")
	public BigDecimal getGenericDecimal15() {
		return genericDecimal15;
	}

	public void setGenericDecimal15(BigDecimal genericDecimal15) {
		this.genericDecimal15 = genericDecimal15;
	}

	@Column(name = "genericdecimal16")
	public BigDecimal getGenericDecimal16() {
		return genericDecimal16;
	}

	public void setGenericDecimal16(BigDecimal genericDecimal16) {
		this.genericDecimal16 = genericDecimal16;
	}

	@Column(name = "genericdecimal17")
	public BigDecimal getGenericDecimal17() {
		return genericDecimal17;
	}

	public void setGenericDecimal17(BigDecimal genericDecimal17) {
		this.genericDecimal17 = genericDecimal17;
	}

	@Column(name = "genericdecimal18")
	public BigDecimal getGenericDecimal18() {
		return genericDecimal18;
	}

	public void setGenericDecimal18(BigDecimal genericDecimal18) {
		this.genericDecimal18 = genericDecimal18;
	}

	@Column(name = "genericdecimal19")
	public BigDecimal getGenericDecimal19() {
		return genericDecimal19;
	}

	public void setGenericDecimal19(BigDecimal genericDecimal19) {
		this.genericDecimal19 = genericDecimal19;
	}

	@Column(name = "genericdecimal20")
	public BigDecimal getGenericDecimal20() {
		return genericDecimal20;
	}

	public void setGenericDecimal20(BigDecimal genericDecimal20) {
		this.genericDecimal20 = genericDecimal20;
	}

	@Column(name = "genericdecimal21")
	public BigDecimal getGenericDecimal21() {
		return genericDecimal21;
	}

	public void setGenericDecimal21(BigDecimal genericDecimal21) {
		this.genericDecimal21 = genericDecimal21;
	}

	@Column(name = "genericdecimal22")
	public BigDecimal getGenericDecimal22() {
		return genericDecimal22;
	}

	public void setGenericDecimal22(BigDecimal genericDecimal22) {
		this.genericDecimal22 = genericDecimal22;
	}

	@Column(name = "genericdecimal23")
	public BigDecimal getGenericDecimal23() {
		return genericDecimal23;
	}

	public void setGenericDecimal23(BigDecimal genericDecimal23) {
		this.genericDecimal23 = genericDecimal23;
	}

	@Column(name = "genericdecimal24")
	public BigDecimal getGenericDecimal24() {
		return genericDecimal24;
	}

	public void setGenericDecimal24(BigDecimal genericDecimal24) {
		this.genericDecimal24 = genericDecimal24;
	}

	@Column(name = "genericdecimal25")
	public BigDecimal getGenericDecimal25() {
		return genericDecimal25;
	}

	public void setGenericDecimal25(BigDecimal genericDecimal25) {
		this.genericDecimal25 = genericDecimal25;
	}

	@Column(name = "genericdecimal26")
	public BigDecimal getGenericDecimal26() {
		return genericDecimal26;
	}

	public void setGenericDecimal26(BigDecimal genericDecimal26) {
		this.genericDecimal26 = genericDecimal26;
	}

	@Column(name = "genericdecimal27")
	public BigDecimal getGenericDecimal27() {
		return genericDecimal27;
	}

	public void setGenericDecimal27(BigDecimal genericDecimal27) {
		this.genericDecimal27 = genericDecimal27;
	}

	@Column(name = "genericdecimal28")
	public BigDecimal getGenericDecimal28() {
		return genericDecimal28;
	}

	public void setGenericDecimal28(BigDecimal genericDecimal28) {
		this.genericDecimal28 = genericDecimal28;
	}

	@Column(name = "genericdecimal29")
	public BigDecimal getGenericDecimal29() {
		return genericDecimal29;
	}

	public void setGenericDecimal29(BigDecimal genericDecimal29) {
		this.genericDecimal29 = genericDecimal29;
	}

	@Column(name = "genericdecimal30")
	public BigDecimal getGenericDecimal30() {
		return genericDecimal30;
	}

	public void setGenericDecimal30(BigDecimal genericDecimal30) {
		this.genericDecimal30 = genericDecimal30;
	}

	@Column(name = "genericdecimal31")
	public BigDecimal getGenericDecimal31() {
		return genericDecimal31;
	}

	public void setGenericDecimal31(BigDecimal genericDecimal31) {
		this.genericDecimal31 = genericDecimal31;
	}

	@Column(name = "genericdecimal32")
	public BigDecimal getGenericDecimal32() {
		return genericDecimal32;
	}

	public void setGenericDecimal32(BigDecimal genericDecimal32) {
		this.genericDecimal32 = genericDecimal32;
	}

	@Column(name = "genericdecimal33")
	public BigDecimal getGenericDecimal33() {
		return genericDecimal33;
	}

	public void setGenericDecimal33(BigDecimal genericDecimal33) {
		this.genericDecimal33 = genericDecimal33;
	}

	@Column(name = "genericdecimal34")
	public BigDecimal getGenericDecimal34() {
		return genericDecimal34;
	}

	public void setGenericDecimal34(BigDecimal genericDecimal34) {
		this.genericDecimal34 = genericDecimal34;
	}

	@Column(name = "genericdecimal35")
	public BigDecimal getGenericDecimal35() {
		return genericDecimal35;
	}

	public void setGenericDecimal35(BigDecimal genericDecimal35) {
		this.genericDecimal35 = genericDecimal35;
	}

	@Column(name = "genericdecimal36")
	public BigDecimal getGenericDecimal36() {
		return genericDecimal36;
	}

	public void setGenericDecimal36(BigDecimal genericDecimal36) {
		this.genericDecimal36 = genericDecimal36;
	}

	@Column(name = "genericdecimal37")
	public BigDecimal getGenericDecimal37() {
		return genericDecimal37;
	}

	public void setGenericDecimal37(BigDecimal genericDecimal37) {
		this.genericDecimal37 = genericDecimal37;
	}

	@Column(name = "genericdecimal38")
	public BigDecimal getGenericDecimal38() {
		return genericDecimal38;
	}

	public void setGenericDecimal38(BigDecimal genericDecimal38) {
		this.genericDecimal38 = genericDecimal38;
	}

	@Column(name = "genericdecimal39")
	public BigDecimal getGenericDecimal39() {
		return genericDecimal39;
	}

	public void setGenericDecimal39(BigDecimal genericDecimal39) {
		this.genericDecimal39 = genericDecimal39;
	}

	@Column(name = "genericdecimal40")
	public BigDecimal getGenericDecimal40() {
		return genericDecimal40;
	}

	public void setGenericDecimal40(BigDecimal genericDecimal40) {
		this.genericDecimal40 = genericDecimal40;
	}

	@Column(name = "genericdecimal41")
	public BigDecimal getGenericDecimal41() {
		return genericDecimal41;
	}

	public void setGenericDecimal41(BigDecimal genericDecimal41) {
		this.genericDecimal41 = genericDecimal41;
	}

	@Column(name = "genericdecimal42")
	public BigDecimal getGenericDecimal42() {
		return genericDecimal42;
	}

	public void setGenericDecimal42(BigDecimal genericDecimal42) {
		this.genericDecimal42 = genericDecimal42;
	}

	@Column(name = "genericdecimal43")
	public BigDecimal getGenericDecimal43() {
		return genericDecimal43;
	}

	public void setGenericDecimal43(BigDecimal genericDecimal43) {
		this.genericDecimal43 = genericDecimal43;
	}

	@Column(name = "genericdecimal44")
	public BigDecimal getGenericDecimal44() {
		return genericDecimal44;
	}

	public void setGenericDecimal44(BigDecimal genericDecimal44) {
		this.genericDecimal44 = genericDecimal44;
	}

	@Column(name = "genericdecimal45")
	public BigDecimal getGenericDecimal45() {
		return genericDecimal45;
	}

	public void setGenericDecimal45(BigDecimal genericDecimal45) {
		this.genericDecimal45 = genericDecimal45;
	}

	@Column(name = "genericdecimal46")
	public BigDecimal getGenericDecimal46() {
		return genericDecimal46;
	}

	public void setGenericDecimal46(BigDecimal genericDecimal46) {
		this.genericDecimal46 = genericDecimal46;
	}

	@Column(name = "genericdecimal47")
	public BigDecimal getGenericDecimal47() {
		return genericDecimal47;
	}

	public void setGenericDecimal47(BigDecimal genericDecimal47) {
		this.genericDecimal47 = genericDecimal47;
	}

	@Column(name = "genericdecimal48")
	public BigDecimal getGenericDecimal48() {
		return genericDecimal48;
	}

	public void setGenericDecimal48(BigDecimal genericDecimal48) {
		this.genericDecimal48 = genericDecimal48;
	}

	@Column(name = "genericdecimal49")
	public BigDecimal getGenericDecimal49() {
		return genericDecimal49;
	}

	public void setGenericDecimal49(BigDecimal genericDecimal49) {
		this.genericDecimal49 = genericDecimal49;
	}

	@Column(name = "genericdecimal50")
	public BigDecimal getGenericDecimal50() {
		return genericDecimal50;
	}

	public void setGenericDecimal50(BigDecimal genericDecimal50) {
		this.genericDecimal50 = genericDecimal50;
	}

	@Column(name = "genericdate1")
	public Date getGenericDate1() {
		return genericDate1;
	}

	public void setGenericDate1(Date genericDate1) {
		this.genericDate1 = genericDate1;
	}

	@Column(name = "genericdate2")
	public Date getGenericDate2() {
		return genericDate2;
	}

	public void setGenericDate2(Date genericDate2) {
		this.genericDate2 = genericDate2;
	}

	@Column(name = "genericdate3")
	public Date getGenericDate3() {
		return genericDate3;
	}

	public void setGenericDate3(Date genericDate3) {
		this.genericDate3 = genericDate3;
	}

	@Column(name = "genericdate4")
	public Date getGenericDate4() {
		return genericDate4;
	}

	public void setGenericDate4(Date genericDate4) {
		this.genericDate4 = genericDate4;
	}

	@Column(name = "genericdate5")
	public Date getGenericDate5() {
		return genericDate5;
	}

	public void setGenericDate5(Date genericDate5) {
		this.genericDate5 = genericDate5;
	}

	@Column(name = "genericdate6")
	public Date getGenericDate6() {
		return genericDate6;
	}

	public void setGenericDate6(Date genericDate6) {
		this.genericDate6 = genericDate6;
	}

	@Column(name = "genericdate7")
	public Date getGenericDate7() {
		return genericDate7;
	}

	public void setGenericDate7(Date genericDate7) {
		this.genericDate7 = genericDate7;
	}

	@Column(name = "genericdate8")
	public Date getGenericDate8() {
		return genericDate8;
	}

	public void setGenericDate8(Date genericDate8) {
		this.genericDate8 = genericDate8;
	}

	@Column(name = "genericdate9")
	public Date getGenericDate9() {
		return genericDate9;
	}

	public void setGenericDate9(Date genericDate9) {
		this.genericDate9 = genericDate9;
	}

	@Column(name = "genericdate10")
	public Date getGenericDate10() {
		return genericDate10;
	}

	public void setGenericDate10(Date genericDate10) {
		this.genericDate10 = genericDate10;
	}

	@Column(name = "genericdate11")
	public Date getGenericDate11() {
		return genericDate11;
	}

	public void setGenericDate11(Date genericDate11) {
		this.genericDate11 = genericDate11;
	}

	@Column(name = "genericdate12")
	public Date getGenericDate12() {
		return genericDate12;
	}

	public void setGenericDate12(Date genericDate12) {
		this.genericDate12 = genericDate12;
	}

	@Column(name = "genericdate13")
	public Date getGenericDate13() {
		return genericDate13;
	}

	public void setGenericDate13(Date genericDate13) {
		this.genericDate13 = genericDate13;
	}

	@Column(name = "genericdate14")
	public Date getGenericDate14() {
		return genericDate14;
	}

	public void setGenericDate14(Date genericDate14) {
		this.genericDate14 = genericDate14;
	}

	@Column(name = "genericdate15")
	public Date getGenericDate15() {
		return genericDate15;
	}

	public void setGenericDate15(Date genericDate15) {
		this.genericDate15 = genericDate15;
	}

	@Column(name = "genericdate16")
	public Date getGenericDate16() {
		return genericDate16;
	}

	public void setGenericDate16(Date genericDate16) {
		this.genericDate16 = genericDate16;
	}

	@Column(name = "genericdate17")
	public Date getGenericDate17() {
		return genericDate17;
	}

	public void setGenericDate17(Date genericDate17) {
		this.genericDate17 = genericDate17;
	}

	@Column(name = "genericdate18")
	public Date getGenericDate18() {
		return genericDate18;
	}

	public void setGenericDate18(Date genericDate18) {
		this.genericDate18 = genericDate18;
	}

	@Column(name = "genericdate19")
	public Date getGenericDate19() {
		return genericDate19;
	}

	public void setGenericDate19(Date genericDate19) {
		this.genericDate19 = genericDate19;
	}

	@Column(name = "genericdate20")
	public Date getGenericDate20() {
		return genericDate20;
	}

	public void setGenericDate20(Date genericDate20) {
		this.genericDate20 = genericDate20;
	}

	@Column(name = "genericdate21")
	public Date getGenericDate21() {
		return genericDate21;
	}

	public void setGenericDate21(Date genericDate21) {
		this.genericDate21 = genericDate21;
	}

	@Column(name = "genericdate22")
	public Date getGenericDate22() {
		return genericDate22;
	}

	public void setGenericDate22(Date genericDate22) {
		this.genericDate22 = genericDate22;
	}

	@Column(name = "genericdate23")
	public Date getGenericDate23() {
		return genericDate23;
	}

	public void setGenericDate23(Date genericDate23) {
		this.genericDate23 = genericDate23;
	}

	@Column(name = "genericdate24")
	public Date getGenericDate24() {
		return genericDate24;
	}

	public void setGenericDate24(Date genericDate24) {
		this.genericDate24 = genericDate24;
	}

	@Column(name = "genericdate25")
	public Date getGenericDate25() {
		return genericDate25;
	}

	public void setGenericDate25(Date genericDate25) {
		this.genericDate25 = genericDate25;
	}

	@Column(name = "genericdate26")
	public Date getGenericDate26() {
		return genericDate26;
	}

	public void setGenericDate26(Date genericDate26) {
		this.genericDate26 = genericDate26;
	}

	@Column(name = "genericdate27")
	public Date getGenericDate27() {
		return genericDate27;
	}

	public void setGenericDate27(Date genericDate27) {
		this.genericDate27 = genericDate27;
	}

	@Column(name = "genericdate28")
	public Date getGenericDate28() {
		return genericDate28;
	}

	public void setGenericDate28(Date genericDate28) {
		this.genericDate28 = genericDate28;
	}

	@Column(name = "genericdate29")
	public Date getGenericDate29() {
		return genericDate29;
	}

	public void setGenericDate29(Date genericDate29) {
		this.genericDate29 = genericDate29;
	}

	@Column(name = "genericdate30")
	public Date getGenericDate30() {
		return genericDate30;
	}

	public void setGenericDate30(Date genericDate30) {
		this.genericDate30 = genericDate30;
	}

	@Column(name = "genericdate31")
	public Date getGenericDate31() {
		return genericDate31;
	}

	public void setGenericDate31(Date genericDate31) {
		this.genericDate31 = genericDate31;
	}

	@Column(name = "genericdate32")
	public Date getGenericDate32() {
		return genericDate32;
	}

	public void setGenericDate32(Date genericDate32) {
		this.genericDate32 = genericDate32;
	}

	@Column(name = "genericdate33")
	public Date getGenericDate33() {
		return genericDate33;
	}

	public void setGenericDate33(Date genericDate33) {
		this.genericDate33 = genericDate33;
	}

	@Column(name = "genericdate34")
	public Date getGenericDate34() {
		return genericDate34;
	}

	public void setGenericDate34(Date genericDate34) {
		this.genericDate34 = genericDate34;
	}

	@Column(name = "genericdate35")
	public Date getGenericDate35() {
		return genericDate35;
	}

	public void setGenericDate35(Date genericDate35) {
		this.genericDate35 = genericDate35;
	}

	@Column(name = "genericdate36")
	public Date getGenericDate36() {
		return genericDate36;
	}

	public void setGenericDate36(Date genericDate36) {
		this.genericDate36 = genericDate36;
	}

	@Column(name = "genericdate37")
	public Date getGenericDate37() {
		return genericDate37;
	}

	public void setGenericDate37(Date genericDate37) {
		this.genericDate37 = genericDate37;
	}

	@Column(name = "genericdate38")
	public Date getGenericDate38() {
		return genericDate38;
	}

	public void setGenericDate38(Date genericDate38) {
		this.genericDate38 = genericDate38;
	}

	@Column(name = "genericdate39")
	public Date getGenericDate39() {
		return genericDate39;
	}

	public void setGenericDate39(Date genericDate39) {
		this.genericDate39 = genericDate39;
	}

	@Column(name = "genericdate40")
	public Date getGenericDate40() {
		return genericDate40;
	}

	public void setGenericDate40(Date genericDate40) {
		this.genericDate40 = genericDate40;
	}

	@Column(name = "genericdate41")
	public Date getGenericDate41() {
		return genericDate41;
	}

	public void setGenericDate41(Date genericDate41) {
		this.genericDate41 = genericDate41;
	}

	@Column(name = "genericdate42")
	public Date getGenericDate42() {
		return genericDate42;
	}

	public void setGenericDate42(Date genericDate42) {
		this.genericDate42 = genericDate42;
	}

	@Column(name = "genericdate43")
	public Date getGenericDate43() {
		return genericDate43;
	}

	public void setGenericDate43(Date genericDate43) {
		this.genericDate43 = genericDate43;
	}

	@Column(name = "genericdate44")
	public Date getGenericDate44() {
		return genericDate44;
	}

	public void setGenericDate44(Date genericDate44) {
		this.genericDate44 = genericDate44;
	}

	@Column(name = "genericdate45")
	public Date getGenericDate45() {
		return genericDate45;
	}

	public void setGenericDate45(Date genericDate45) {
		this.genericDate45 = genericDate45;
	}

	@Column(name = "genericdate46")
	public Date getGenericDate46() {
		return genericDate46;
	}

	public void setGenericDate46(Date genericDate46) {
		this.genericDate46 = genericDate46;
	}

	@Column(name = "genericdate47")
	public Date getGenericDate47() {
		return genericDate47;
	}

	public void setGenericDate47(Date genericDate47) {
		this.genericDate47 = genericDate47;
	}

	@Column(name = "genericdate48")
	public Date getGenericDate48() {
		return genericDate48;
	}

	public void setGenericDate48(Date genericDate48) {
		this.genericDate48 = genericDate48;
	}

	@Column(name = "genericdate49")
	public Date getGenericDate49() {
		return genericDate49;
	}

	public void setGenericDate49(Date genericDate49) {
		this.genericDate49 = genericDate49;
	}

	@Column(name = "genericdate50")
	public Date getGenericDate50() {
		return genericDate50;
	}

	public void setGenericDate50(Date genericDate50) {
		this.genericDate50 = genericDate50;
	}

	@Transient
	public String getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}

	@Transient
	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

}

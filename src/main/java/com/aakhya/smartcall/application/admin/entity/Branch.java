package com.aakhya.smartcall.application.admin.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;

@Entity
@Table(name="sc_branch")
@IdClass(BranchPk.class)
public class Branch implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6460045373967385068L;
	@NotEmpty
	@NotNull
	private String branchCode;
	@NotEmpty
	@NotNull
	private String branchName;
	@NotNull
	private Long branchType;
	private String branchTypeStr;
	@NotNull
	private GenericClassifier branchTypeDesc;
	private String parentBranch;
	private String parentBranchName;
	private Branch parentBranchObject;
	@Email
	@NotEmpty
	private String branchEmailId;
	private String branchAddress;
	private String branchPincode;
	private Long branchCategory;
	private String branchCategoryStr;
	private GenericClassifier branchCategoryDesc;
	private Long country;
	private Long state;
	private Long city;
	private Date dateOfIncorporation;
	private Date validFrom;
	private Date validTo;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private Date removeDatetime;
	private Long companyId;
	private String status;
	private String generic1;
	private String generic2;
	private String generic3;
	private String generic4;
	private String generic5;
	private String generic6;
	private String generic7;
	private String generic8;
	private String generic9;
	private String generic10;
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
	private BigDecimal genericNumber1;
	private BigDecimal genericNumber2;
	private BigDecimal genericNumber3;
	private BigDecimal genericNumber4;
	private BigDecimal genericNumber5;
	private BigDecimal genericNumber6;
	private BigDecimal genericNumber7;
	private BigDecimal genericNumber8;
	private BigDecimal genericNumber9;
	private BigDecimal genericNumber10;
	private List<Branch> childBranches;
	public Branch() {
		super();
	}
	public Branch(String branchCode, String branchName) {
		super();
		this.branchCode = branchCode;
		this.branchName = branchName;
	}
	@Id
	@Column(name="branchcode")
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	@Column(name="branchname")
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	@Column(name="branchtype")
	public Long getBranchType() {
		return branchType;
	}
	public void setBranchType(Long branchType) {
		if(null != branchType && null != branchTypeStr)
			this.branchTypeDesc = new GenericClassifier(branchType,branchTypeStr);
		this.branchType = branchType;
	}
	@Formula(value = "dbo.fn_getGenClass(branchType,companyId)")
	public String getBranchTypeStr() {
		return branchTypeStr;
	}
	public void setBranchTypeStr(String branchTypeStr) {
		if(null != branchType && null != branchTypeStr)
			this.branchTypeDesc = new GenericClassifier(branchType,branchTypeStr);
		this.branchTypeStr = branchTypeStr;
	}
	@Transient
	public GenericClassifier getBranchTypeDesc() {
		return branchTypeDesc;
	}
	public void setBranchTypeDesc(GenericClassifier branchTypeDesc) {
		if(null != branchTypeDesc) {
			this.branchType = branchTypeDesc.getGenericId();
			this.branchTypeStr = branchTypeDesc.getDescription();
		}
		this.branchTypeDesc = branchTypeDesc;
	}
	@Column(name="parentbranch")
	public String getParentBranch() {
		return parentBranch;
	}
	public void setParentBranch(String parentBranch) {
		if(null != parentBranch && null != this.parentBranchName)
			this.parentBranchObject = new Branch(parentBranch,this.parentBranchName);
		this.parentBranch = parentBranch;
	}
	@Formula(value = "dbo.fn_getBranchName(parentbranch,companyId)")
	public String getParentBranchName() {
		return parentBranchName;
	}
	public void setParentBranchName(String parentBranchName) {
		if(null != this.parentBranch && null != parentBranchName)
			this.parentBranchObject = new Branch(this.parentBranch,parentBranchName);
		this.parentBranchName = parentBranchName;
	}
	@Transient
	public Branch getParentBranchObject() {
		return parentBranchObject;
	}
	public void setParentBranchObject(Branch parentBranchObject) {
		if(null != parentBranchObject) {
			this.parentBranch = parentBranchObject.getBranchCode();
			this.parentBranchName = parentBranchObject.getBranchName();
		}
		this.parentBranchObject = parentBranchObject;
	}
	@Column(name="branchemailid")
	public String getBranchEmailId() {
		return branchEmailId;
	}
	public void setBranchEmailId(String branchEmailId) {
		this.branchEmailId = branchEmailId;
	}
	@Column(name="branchaddress")
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	@Column(name="branchpincode")
	public String getBranchPincode() {
		return branchPincode;
	}
	public void setBranchPincode(String branchPincode) {
		this.branchPincode = branchPincode;
	}
	@Column(name="branchcategory")
	public Long getBranchCategory() {
		return branchCategory;
	}
	public void setBranchCategory(Long branchCategory) {
		if(null != branchCategory && null != branchCategoryStr)
			this.branchCategoryDesc = new GenericClassifier(branchCategory,branchCategoryStr);
		this.branchCategory = branchCategory;
	}
	@Formula(value = "dbo.fn_getGenClass(branchCategory,companyId)")
	public String getBranchCategoryStr() {
		return branchCategoryStr;
	}
	public void setBranchCategoryStr(String branchCategoryStr) {
		if(null != branchCategory && null != branchCategoryStr)
			this.branchCategoryDesc = new GenericClassifier(branchCategory,branchCategoryStr);
		this.branchCategoryStr = branchCategoryStr;
	}
	@Transient
	public GenericClassifier getBranchCategoryDesc() {
		return branchCategoryDesc;
	}
	public void setBranchCategoryDesc(GenericClassifier branchCategoryDesc) {
		if(null != branchCategoryDesc) {
			this.branchCategory = branchCategoryDesc.getGenericId();
			this.branchCategoryStr = branchCategoryDesc.getDescription();
		}
		this.branchCategoryDesc = branchCategoryDesc;
	}
	@Column(name="country")
	public Long getCountry() {
		return country;
	}
	public void setCountry(Long country) {
		this.country = country;
	}
	@Column(name="state")
	public Long getState() {
		return state;
	}
	public void setState(Long state) {
		this.state = state;
	}
	@Column(name="city")
	public Long getCity() {
		return city;
	}
	public void setCity(Long city) {
		this.city = city;
	}
	@Column(name="dateofincorporation")
	public Date getDateOfIncorporation() {
		return dateOfIncorporation;
	}
	public void setDateOfIncorporation(Date dateOfIncorporation) {
		this.dateOfIncorporation = dateOfIncorporation;
	}
	@Column(name="validfrom")
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	@Column(name="validto")
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	@Column(name="createdby")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name="createddatetime")
	public Date getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	@Column(name="updatedby")
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name="updateddatetime")
	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}
	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	@Column(name="removedatetime")
	public Date getRemoveDatetime() {
		return removeDatetime;
	}
	public void setRemoveDatetime(Date removeDatetime) {
		this.removeDatetime = removeDatetime;
	}
	@Id
	@Column(name="companyid")
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="generic1")
	public String getGeneric1() {
		return generic1;
	}
	public void setGeneric1(String generic1) {
		this.generic1 = generic1;
	}
	@Column(name="generic2")
	public String getGeneric2() {
		return generic2;
	}
	public void setGeneric2(String generic2) {
		this.generic2 = generic2;
	}
	@Column(name="generic3")
	public String getGeneric3() {
		return generic3;
	}
	public void setGeneric3(String generic3) {
		this.generic3 = generic3;
	}
	@Column(name="generic4")
	public String getGeneric4() {
		return generic4;
	}
	public void setGeneric4(String generic4) {
		this.generic4 = generic4;
	}
	@Column(name="generic5")
	public String getGeneric5() {
		return generic5;
	}
	public void setGeneric5(String generic5) {
		this.generic5 = generic5;
	}
	@Column(name="generic6")
	public String getGeneric6() {
		return generic6;
	}
	public void setGeneric6(String generic6) {
		this.generic6 = generic6;
	}
	@Column(name="generic7")
	public String getGeneric7() {
		return generic7;
	}
	public void setGeneric7(String generic7) {
		this.generic7 = generic7;
	}
	@Column(name="generic8")
	public String getGeneric8() {
		return generic8;
	}
	public void setGeneric8(String generic8) {
		this.generic8 = generic8;
	}
	@Column(name="generic9")
	public String getGeneric9() {
		return generic9;
	}
	public void setGeneric9(String generic9) {
		this.generic9 = generic9;
	}
	@Column(name="generic10")
	public String getGeneric10() {
		return generic10;
	}
	public void setGeneric10(String generic10) {
		this.generic10 = generic10;
	}
	@Column(name="genericdate1")
	public Date getGenericDate1() {
		return genericDate1;
	}
	public void setGenericDate1(Date genericDate1) {
		this.genericDate1 = genericDate1;
	}
	@Column(name="genericdate2")
	public Date getGenericDate2() {
		return genericDate2;
	}
	public void setGenericDate2(Date genericDate2) {
		this.genericDate2 = genericDate2;
	}
	@Column(name="genericdate3")
	public Date getGenericDate3() {
		return genericDate3;
	}
	public void setGenericDate3(Date genericDate3) {
		this.genericDate3 = genericDate3;
	}
	@Column(name="genericdate4")
	public Date getGenericDate4() {
		return genericDate4;
	}
	public void setGenericDate4(Date genericDate4) {
		this.genericDate4 = genericDate4;
	}
	@Column(name="genericdate5")
	public Date getGenericDate5() {
		return genericDate5;
	}
	public void setGenericDate5(Date genericDate5) {
		this.genericDate5 = genericDate5;
	}
	@Column(name="genericdate6")
	public Date getGenericDate6() {
		return genericDate6;
	}
	public void setGenericDate6(Date genericDate6) {
		this.genericDate6 = genericDate6;
	}
	@Column(name="genericdate7")
	public Date getGenericDate7() {
		return genericDate7;
	}
	public void setGenericDate7(Date genericDate7) {
		this.genericDate7 = genericDate7;
	}
	@Column(name="genericdate8")
	public Date getGenericDate8() {
		return genericDate8;
	}
	public void setGenericDate8(Date genericDate8) {
		this.genericDate8 = genericDate8;
	}
	@Column(name="genericdate9")
	public Date getGenericDate9() {
		return genericDate9;
	}
	public void setGenericDate9(Date genericDate9) {
		this.genericDate9 = genericDate9;
	}
	@Column(name="genericdate10")
	public Date getGenericDate10() {
		return genericDate10;
	}
	public void setGenericDate10(Date genericDate10) {
		this.genericDate10 = genericDate10;
	}
	@Column(name="genericnumber1")
	public BigDecimal getGenericNumber1() {
		return genericNumber1;
	}
	public void setGenericNumber1(BigDecimal genericNumber1) {
		this.genericNumber1 = genericNumber1;
	}
	@Column(name="genericnumber2")
	public BigDecimal getGenericNumber2() {
		return genericNumber2;
	}
	public void setGenericNumber2(BigDecimal genericNumber2) {
		this.genericNumber2 = genericNumber2;
	}
	@Column(name="genericnumber3")
	public BigDecimal getGenericNumber3() {
		return genericNumber3;
	}
	public void setGenericNumber3(BigDecimal genericNumber3) {
		this.genericNumber3 = genericNumber3;
	}
	@Column(name="genericnumber4")
	public BigDecimal getGenericNumber4() {
		return genericNumber4;
	}
	public void setGenericNumber4(BigDecimal genericNumber4) {
		this.genericNumber4 = genericNumber4;
	}
	@Column(name="genericnumber5")
	public BigDecimal getGenericNumber5() {
		return genericNumber5;
	}
	public void setGenericNumber5(BigDecimal genericNumber5) {
		this.genericNumber5 = genericNumber5;
	}
	@Column(name="genericnumber6")
	public BigDecimal getGenericNumber6() {
		return genericNumber6;
	}
	public void setGenericNumber6(BigDecimal genericNumber6) {
		this.genericNumber6 = genericNumber6;
	}
	@Column(name="genericnumber7")
	public BigDecimal getGenericNumber7() {
		return genericNumber7;
	}
	public void setGenericNumber7(BigDecimal genericNumber7) {
		this.genericNumber7 = genericNumber7;
	}
	@Column(name="genericnumber8")
	public BigDecimal getGenericNumber8() {
		return genericNumber8;
	}
	public void setGenericNumber8(BigDecimal genericNumber8) {
		this.genericNumber8 = genericNumber8;
	}
	@Column(name="genericnumber9")
	public BigDecimal getGenericNumber9() {
		return genericNumber9;
	}
	public void setGenericNumber9(BigDecimal genericNumber9) {
		this.genericNumber9 = genericNumber9;
	}
	@Column(name="genericnumber10")
	public BigDecimal getGenericNumber10() {
		return genericNumber10;
	}
	public void setGenericNumber10(BigDecimal genericNumber10) {
		this.genericNumber10 = genericNumber10;
	}
	@Transient
	public List<Branch> getChildBranches() {
		return childBranches;
	}
	public void setChildBranches(List<Branch> childBranches) {
		this.childBranches = childBranches;
	}
}

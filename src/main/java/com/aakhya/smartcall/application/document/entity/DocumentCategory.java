package com.aakhya.smartcall.application.document.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aakhya.smartcall.application.admin.entity.GenericClassifier;



@Entity
@Table(name = "ga_documentcategory")
@IdClass(DocumentCategoryPK.class)
public class DocumentCategory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 14L;
	private Long documentChecklistId;
	private Long documentChecklistCategory;
	private String documentChecklistCategoryStr;
	private GenericClassifier documentChecklistCategoryDesc;
	private Long validationRule;
	private String validationRuleStr;
	private GenericClassifier validationRuleDesc;
	private Integer collateralDocument;
	private boolean collateralDocumentCategory;
	private Long documentCategoryLevel;
	private String documentCategoryLevelStr;
	private GenericClassifier documentCategoryLevelDesc;
	private Long applicantType;
	private String applicantTypeStr;
	private GenericClassifier applicantTypeDesc;
	private Integer sequence;
	private String status;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private Date removeDateTime;
	private Date effectiveStartDate;
	private Date effectiveEndDate;
	private Long companyId;
	private String genericAttribute1;
	private String genericAttribute2;
	private String genericAttribute3;
	private String genericAttribute4;
	private String genericAttribute5;
	private String genericAttribute6;
	private String genericAttribute7;
	private String genericAttribute8;
	private Date genericDate1;
	private Date genericDate2;
	private Date genericDate3;
	private Date genericDate4;
	private Date genericDate5;
	private Date genericDate6;
	private Double genericNumber1;
	private Double genericNumber2;
	private Double genericNumber3;
	private Double genericNumber4;
	private Double genericNumber5;
	private Long genericClassifier1;
	private String genericClassifier1Str;
	private GenericClassifier genericClassifier1Desc;
	private Long genericClassifier2;
	private String genericClassifier2Str;
	private GenericClassifier genericClassifier2Desc;
	private Long genericClassifier3;
	private String genericClassifier3Str;
	private GenericClassifier genericClassifier3Desc;
	private Long genericClassifier4;
	private String genericClassifier4Str;
	private GenericClassifier genericClassifier4Desc;
	private Long genericClassifier5;
	private String genericClassifier5Str;
	private GenericClassifier genericClassifier5Desc;
	private Long genericClassifier6;
	private String genericClassifier6Str;
	private GenericClassifier genericClassifier6Desc;
	private Long genericNumber1Type;
	private String genericNumber1TypeStr;
	private GenericClassifier genericNumber1TypeDesc;
	private Long genericNumber2Type;
	private String genericNumber2TypeStr;
	private GenericClassifier genericNumber2TypeDesc;
	private Long genericNumber3Type;
	private String genericNumber3TypeStr;
	private GenericClassifier genericNumber3TypeDesc;
	private Long genericNumber4Type;
	private String genericNumber4TypeStr;
	private GenericClassifier genericNumber4TypeDesc;
	private Long genericNumber5Type;
	private String genericNumber5TypeStr;
	private GenericClassifier genericNumber5TypeDesc;
	
	private boolean delete;

	private DocumentChecklist documentChecklist;
	private Set<Document> documents;

	@Id
	@Column(name = "documentchecklistid")
	public Long getDocumentChecklistId() {
		return this.documentChecklistId;
	}

	public void setDocumentChecklistId(Long documentChecklistId) {
		this.documentChecklistId = documentChecklistId;
	}

	@Id
	@Column(name = "documentchecklistcategory")
	public Long getDocumentChecklistCategory() {
		return this.documentChecklistCategory;
	}

	public void setDocumentChecklistCategory(Long documentChecklistCategory) {
		this.documentChecklistCategory = documentChecklistCategory;
	}

	@Transient
	public String getDocumentChecklistCategoryStr() {
		return this.documentChecklistCategoryStr;
	}

	public void setDocumentChecklistCategoryStr(
			String documentChecklistCategoryStr) {
		this.documentChecklistCategoryStr = documentChecklistCategoryStr;
	}

	@Transient
	public GenericClassifier getDocumentChecklistCategoryDesc() {
		return this.documentChecklistCategoryDesc;
	}

	public void setDocumentChecklistCategoryDesc(
			GenericClassifier documentChecklistCategoryDesc) {
		if (null != documentChecklistCategoryDesc) {
			documentChecklistCategory = documentChecklistCategoryDesc
					.getGenericId();
		}
		this.documentChecklistCategoryDesc = documentChecklistCategoryDesc;
	}

	@Column(name = "validationrule")
	public Long getValidationRule() {
		return this.validationRule;
	}

	public void setValidationRule(Long validationRule) {
		this.validationRule = validationRule;
	}

	@Transient
	public String getValidationRuleStr() {
		return this.validationRuleStr;
	}

	public void setValidationRuleStr(String validationRuleStr) {
		this.validationRuleStr = validationRuleStr;
	}

	@Transient
	public GenericClassifier getValidationRuleDesc() {
		return this.validationRuleDesc;
	}

	public void setValidationRuleDesc(GenericClassifier validationRuleDesc) {
		if (null != validationRuleDesc) {
			this.validationRule = validationRuleDesc.getGenericId();
		}
		this.validationRuleDesc = validationRuleDesc;
	}

	@Column(name = "collateraldocumentcategory")
	public Integer getCollateralDocument() {
		return collateralDocument;
	}

	public void setCollateralDocument(Integer collateralDocument) {
		this.collateralDocument = collateralDocument;
	}

	@Transient
	public boolean isCollateralDocumentCategory() {
		return collateralDocumentCategory;
	}

	public void setCollateralDocumentCategory(boolean collateralDocumentCategory) {
		if (collateralDocumentCategory)
			this.collateralDocument = 1;
		else
			this.collateralDocument = 0;
		this.collateralDocumentCategory = collateralDocumentCategory;
	}

	@Id
	@Column(name = "documentcategorylevel")
	public Long getDocumentCategoryLevel() {
		return documentCategoryLevel;
	}

	public void setDocumentCategoryLevel(Long documentCategoryLevel) {
		this.documentCategoryLevel = documentCategoryLevel;
	}

	@Transient
	public String getDocumentCategoryLevelStr() {
		return documentCategoryLevelStr;
	}

	public void setDocumentCategoryLevelStr(String documentCategoryLevelStr) {
		this.documentCategoryLevelStr = documentCategoryLevelStr;
	}

	@Transient
	public GenericClassifier getDocumentCategoryLevelDesc() {
		return documentCategoryLevelDesc;
	}

	public void setDocumentCategoryLevelDesc(
			GenericClassifier documentCategoryLevelDesc) {
		if (null != documentCategoryLevelDesc) {
			this.documentCategoryLevel = documentCategoryLevelDesc
					.getGenericId();
			this.documentCategoryLevelStr = documentCategoryLevelDesc
					.getDescription();
		}
		this.documentCategoryLevelDesc = documentCategoryLevelDesc;
	}

	@Column(name = "applicanttype")
	public Long getApplicantType() {
		return applicantType;
	}

	public void setApplicantType(Long applicantType) {
		this.applicantType = applicantType;
	}

	@Transient
	public String getApplicantTypeStr() {
		return applicantTypeStr;
	}

	public void setApplicantTypeStr(String applicantTypeStr) {
		this.applicantTypeStr = applicantTypeStr;
	}

	@Transient
	public GenericClassifier getApplicantTypeDesc() {
		return applicantTypeDesc;
	}

	public void setApplicantTypeDesc(GenericClassifier applicantTypeDesc) {
		if (null != applicantTypeDesc) {
			this.applicantType = applicantTypeDesc.getGenericId();
			this.applicantTypeStr = applicantTypeDesc.getDescription();
		}
		this.applicantTypeDesc = applicantTypeDesc;
	}

	@Column(name = "sequence")
	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
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
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "createddatetime")
	public Date getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	@Column(name = "updatedby")
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "updateddatetime")
	public Date getUpdatedDateTime() {
		return this.updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	@Column(name = "removedatetime")
	public Date getRemoveDateTime() {
		return this.removeDateTime;
	}

	public void setRemoveDateTime(Date removeDateTime) {
		this.removeDateTime = removeDateTime;
	}

	@Column(name = "effectivestartdate")
	public Date getEffectiveStartDate() {
		return this.effectiveStartDate;
	}

	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	@Column(name = "effectiveenddate")
	public Date getEffectiveEndDate() {
		return this.effectiveEndDate;
	}

	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	@Id
	@Column(name = "companyid")
	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Column(name = "genericattribute1")
	public String getGenericAttribute1() {
		return this.genericAttribute1;
	}

	public void setGenericAttribute1(String genericAttribute1) {
		this.genericAttribute1 = genericAttribute1;
	}

	@Column(name = "genericattribute2")
	public String getGenericAttribute2() {
		return this.genericAttribute2;
	}

	public void setGenericAttribute2(String genericAttribute2) {
		this.genericAttribute2 = genericAttribute2;
	}

	@Column(name = "genericattribute3")
	public String getGenericAttribute3() {
		return this.genericAttribute3;
	}

	public void setGenericAttribute3(String genericAttribute3) {
		this.genericAttribute3 = genericAttribute3;
	}

	@Column(name = "genericattribute4")
	public String getGenericAttribute4() {
		return this.genericAttribute4;
	}

	public void setGenericAttribute4(String genericAttribute4) {
		this.genericAttribute4 = genericAttribute4;
	}

	@Column(name = "genericattribute5")
	public String getGenericAttribute5() {
		return this.genericAttribute5;
	}

	public void setGenericAttribute5(String genericAttribute5) {
		this.genericAttribute5 = genericAttribute5;
	}

	@Column(name = "genericattribute6")
	public String getGenericAttribute6() {
		return this.genericAttribute6;
	}

	public void setGenericAttribute6(String genericAttribute6) {
		this.genericAttribute6 = genericAttribute6;
	}

	@Column(name = "genericattribute7")
	public String getGenericAttribute7() {
		return this.genericAttribute7;
	}

	public void setGenericAttribute7(String genericAttribute7) {
		this.genericAttribute7 = genericAttribute7;
	}

	@Column(name = "genericattribute8")
	public String getGenericAttribute8() {
		return this.genericAttribute8;
	}

	public void setGenericAttribute8(String genericAttribute8) {
		this.genericAttribute8 = genericAttribute8;
	}

	@Column(name = "genericdate1")
	public Date getGenericDate1() {
		return this.genericDate1;
	}

	public void setGenericDate1(Date genericDate1) {
		this.genericDate1 = genericDate1;
	}

	@Column(name = "genericdate2")
	public Date getGenericDate2() {
		return this.genericDate2;
	}

	public void setGenericDate2(Date genericDate2) {
		this.genericDate2 = genericDate2;
	}

	@Column(name = "genericdate3")
	public Date getGenericDate3() {
		return this.genericDate3;
	}

	public void setGenericDate3(Date genericDate3) {
		this.genericDate3 = genericDate3;
	}

	@Column(name = "genericdate4")
	public Date getGenericDate4() {
		return this.genericDate4;
	}

	public void setGenericDate4(Date genericDate4) {
		this.genericDate4 = genericDate4;
	}

	@Column(name = "genericdate5")
	public Date getGenericDate5() {
		return this.genericDate5;
	}

	public void setGenericDate5(Date genericDate5) {
		this.genericDate5 = genericDate5;
	}

	@Column(name = "genericdate6")
	public Date getGenericDate6() {
		return this.genericDate6;
	}

	public void setGenericDate6(Date genericDate6) {
		this.genericDate6 = genericDate6;
	}

	@Column(name = "genericnumber1")
	public Double getGenericNumber1() {
		return this.genericNumber1;
	}

	public void setGenericNumber1(Double genericNumber1) {
		this.genericNumber1 = genericNumber1;
	}

	@Column(name = "genericnumber2")
	public Double getGenericNumber2() {
		return this.genericNumber2;
	}

	public void setGenericNumber2(Double genericNumber2) {
		this.genericNumber2 = genericNumber2;
	}

	@Column(name = "genericnumber3")
	public Double getGenericNumber3() {
		return this.genericNumber3;
	}

	public void setGenericNumber3(Double genericNumber3) {
		this.genericNumber3 = genericNumber3;
	}

	@Column(name = "genericnumber4")
	public Double getGenericNumber4() {
		return this.genericNumber4;
	}

	public void setGenericNumber4(Double genericNumber4) {
		this.genericNumber4 = genericNumber4;
	}

	@Column(name = "genericnumber5")
	public Double getGenericNumber5() {
		return this.genericNumber5;
	}

	public void setGenericNumber5(Double genericNumber5) {
		this.genericNumber5 = genericNumber5;
	}

	@Column(name = "genericclassifier1")
	public Long getGenericClassifier1() {
		return this.genericClassifier1;
	}

	public void setGenericClassifier1(Long genericClassifier1) {
		this.genericClassifier1 = genericClassifier1;
	}

	@Transient
	public String getGenericClassifier1Str() {
		return this.genericClassifier1Str;
	}

	public void setGenericClassifier1Str(String genericClassifier1Str) {
		this.genericClassifier1Str = genericClassifier1Str;
	}

	@Transient
	public GenericClassifier getGenericClassifier1Desc() {
		return this.genericClassifier1Desc;
	}

	public void setGenericClassifier1Desc(
			GenericClassifier genericClassifier1Desc) {
		if (null != genericClassifier1Desc) {
			this.genericClassifier1 = genericClassifier1Desc.getGenericId();
			this.genericClassifier1Str = genericClassifier1Desc
					.getDescription();
		}
		this.genericClassifier1Desc = genericClassifier1Desc;
	}

	@Column(name = "genericclassifier2")
	public Long getGenericClassifier2() {
		return this.genericClassifier2;
	}

	public void setGenericClassifier2(Long genericClassifier2) {
		this.genericClassifier2 = genericClassifier2;
	}

	@Transient
	public String getGenericClassifier2Str() {
		return this.genericClassifier2Str;
	}

	public void setGenericClassifier2Str(String genericClassifier2Str) {
		this.genericClassifier2Str = genericClassifier2Str;
	}

	@Transient
	public GenericClassifier getGenericClassifier2Desc() {
		return this.genericClassifier2Desc;
	}

	public void setGenericClassifier2Desc(
			GenericClassifier genericClassifier2Desc) {
		if (null != genericClassifier2Desc) {
			this.genericClassifier2 = genericClassifier2Desc.getGenericId();
			this.genericClassifier2Str = genericClassifier2Desc
					.getDescription();
		}
		this.genericClassifier2Desc = genericClassifier2Desc;
	}

	@Column(name = "genericclassifier3")
	public Long getGenericClassifier3() {
		return this.genericClassifier3;
	}

	public void setGenericClassifier3(Long genericClassifier3) {
		this.genericClassifier3 = genericClassifier3;
	}

	@Transient
	public String getGenericClassifier3Str() {
		return this.genericClassifier3Str;
	}

	public void setGenericClassifier3Str(String genericClassifier3Str) {
		this.genericClassifier3Str = genericClassifier3Str;
	}

	@Transient
	public GenericClassifier getGenericClassifier3Desc() {
		return this.genericClassifier3Desc;
	}

	public void setGenericClassifier3Desc(
			GenericClassifier genericClassifier3Desc) {
		if (null != genericClassifier3Desc) {
			this.genericClassifier3 = genericClassifier3Desc.getGenericId();
			this.genericClassifier3Str = genericClassifier3Desc
					.getDescription();
		}
		this.genericClassifier3Desc = genericClassifier3Desc;
	}

	@Column(name = "genericclassifier4")
	public Long getGenericClassifier4() {
		return this.genericClassifier4;
	}

	public void setGenericClassifier4(Long genericClassifier4) {
		this.genericClassifier4 = genericClassifier4;
	}

	@Transient
	public String getGenericClassifier4Str() {
		return this.genericClassifier4Str;
	}

	public void setGenericClassifier4Str(String genericClassifier4Str) {
		this.genericClassifier4Str = genericClassifier4Str;
	}

	@Transient
	public GenericClassifier getGenericClassifier4Desc() {
		return this.genericClassifier4Desc;
	}

	public void setGenericClassifier4Desc(
			GenericClassifier genericClassifier4Desc) {
		if (null != genericClassifier4Desc) {
			this.genericClassifier4 = genericClassifier4Desc.getGenericId();
			this.genericClassifier4Str = genericClassifier4Desc
					.getDescription();
		}
		this.genericClassifier4Desc = genericClassifier4Desc;
	}

	@Column(name = "genericclassifier5")
	public Long getGenericClassifier5() {
		return this.genericClassifier5;
	}

	public void setGenericClassifier5(Long genericClassifier5) {
		this.genericClassifier5 = genericClassifier5;
	}

	@Transient
	public String getGenericClassifier5Str() {
		return this.genericClassifier5Str;
	}

	public void setGenericClassifier5Str(String genericClassifier5Str) {
		this.genericClassifier5Str = genericClassifier5Str;
	}

	@Transient
	public GenericClassifier getGenericClassifier5Desc() {
		return this.genericClassifier5Desc;
	}

	public void setGenericClassifier5Desc(
			GenericClassifier genericClassifier5Desc) {
		if (null != genericClassifier5Desc) {
			this.genericClassifier5 = genericClassifier5Desc.getGenericId();
			this.genericClassifier5Str = genericClassifier5Desc
					.getDescription();
		}
		this.genericClassifier5Desc = genericClassifier5Desc;
	}

	@Column(name = "genericclassifier6")
	public Long getGenericClassifier6() {
		return this.genericClassifier6;
	}

	public void setGenericClassifier6(Long genericClassifier6) {
		this.genericClassifier6 = genericClassifier6;
	}

	@Transient
	public String getGenericClassifier6Str() {
		return this.genericClassifier6Str;
	}

	public void setGenericClassifier6Str(String genericClassifier6Str) {
		this.genericClassifier6Str = genericClassifier6Str;
	}

	@Transient
	public GenericClassifier getGenericClassifier6Desc() {
		return this.genericClassifier6Desc;
	}

	public void setGenericClassifier6Desc(
			GenericClassifier genericClassifier6Desc) {
		if (null != genericClassifier6Desc) {
			this.genericClassifier6 = genericClassifier6Desc.getGenericId();
			this.genericClassifier6Str = genericClassifier6Desc
					.getDescription();
		}
		this.genericClassifier6Desc = genericClassifier6Desc;
	}

	@Column(name = "genericnumber1type")
	public Long getGenericNumber1Type() {
		return this.genericNumber1Type;
	}

	public void setGenericNumber1Type(Long genericNumber1Type) {
		this.genericNumber1Type = genericNumber1Type;
	}

	@Transient
	public String getGenericNumber1TypeStr() {
		return this.genericNumber1TypeStr;
	}

	public void setGenericNumber1TypeStr(String genericNumber1TypeStr) {
		this.genericNumber1TypeStr = genericNumber1TypeStr;
	}

	@Transient
	public GenericClassifier getGenericNumber1TypeDesc() {
		return this.genericNumber1TypeDesc;
	}

	public void setGenericNumber1TypeDesc(
			GenericClassifier genericNumber1TypeDesc) {
		this.genericNumber1TypeDesc = genericNumber1TypeDesc;
	}

	@Column(name = "genericnumber2type")
	public Long getGenericNumber2Type() {
		return this.genericNumber2Type;
	}

	public void setGenericNumber2Type(Long genericNumber2Type) {
		this.genericNumber2Type = genericNumber2Type;
	}

	@Transient
	public String getGenericNumber2TypeStr() {
		return this.genericNumber2TypeStr;
	}

	public void setGenericNumber2TypeStr(String genericNumber2TypeStr) {
		this.genericNumber2TypeStr = genericNumber2TypeStr;
	}

	@Transient
	public GenericClassifier getGenericNumber2TypeDesc() {
		return this.genericNumber2TypeDesc;
	}

	public void setGenericNumber2TypeDesc(
			GenericClassifier genericNumber2TypeDesc) {
		this.genericNumber2TypeDesc = genericNumber2TypeDesc;
	}

	@Column(name = "genericnumber3type")
	public Long getGenericNumber3Type() {
		return this.genericNumber3Type;
	}

	public void setGenericNumber3Type(Long genericNumber3Type) {
		this.genericNumber3Type = genericNumber3Type;
	}

	@Transient
	public String getGenericNumber3TypeStr() {
		return this.genericNumber3TypeStr;
	}

	public void setGenericNumber3TypeStr(String genericNumber3TypeStr) {
		this.genericNumber3TypeStr = genericNumber3TypeStr;
	}

	@Transient
	public GenericClassifier getGenericNumber3TypeDesc() {
		return this.genericNumber3TypeDesc;
	}

	public void setGenericNumber3TypeDesc(
			GenericClassifier genericNumber3TypeDesc) {
		this.genericNumber3TypeDesc = genericNumber3TypeDesc;
	}

	@Column(name = "genericnumber4type")
	public Long getGenericNumber4Type() {
		return this.genericNumber4Type;
	}

	public void setGenericNumber4Type(Long genericNumber4Type) {
		this.genericNumber4Type = genericNumber4Type;
	}

	@Transient
	public String getGenericNumber4TypeStr() {
		return this.genericNumber4TypeStr;
	}

	public void setGenericNumber4TypeStr(String genericNumber4TypeStr) {
		this.genericNumber4TypeStr = genericNumber4TypeStr;
	}

	@Transient
	public GenericClassifier getGenericNumber4TypeDesc() {
		return this.genericNumber4TypeDesc;
	}

	public void setGenericNumber4TypeDesc(
			GenericClassifier genericNumber4TypeDesc) {
		this.genericNumber4TypeDesc = genericNumber4TypeDesc;
	}

	@Column(name = "genericnumber5type")
	public Long getGenericNumber5Type() {
		return this.genericNumber5Type;
	}

	public void setGenericNumber5Type(Long genericNumber5Type) {
		this.genericNumber5Type = genericNumber5Type;
	}

	@Transient
	public String getGenericNumber5TypeStr() {
		return this.genericNumber5TypeStr;
	}

	public void setGenericNumber5TypeStr(String genericNumber5TypeStr) {
		this.genericNumber5TypeStr = genericNumber5TypeStr;
	}

	@Transient
	public GenericClassifier getGenericNumber5TypeDesc() {
		return this.genericNumber5TypeDesc;
	}

	public void setGenericNumber5TypeDesc(
			GenericClassifier genericNumber5TypeDesc) {
		this.genericNumber5TypeDesc = genericNumber5TypeDesc;
	}

	

	@Transient
	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "documentchecklistid", referencedColumnName = "documentchecklistid", insertable = false, updatable = false),
			@JoinColumn(name = "companyid", referencedColumnName = "companyid", insertable = false, updatable = false) })
	public DocumentChecklist getDocumentChecklist() {
		return documentChecklist;
	}

	public void setDocumentChecklist(DocumentChecklist documentChecklist) {
		this.documentChecklist = documentChecklist;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "documentCategory", cascade = CascadeType.ALL, targetEntity = Document.class)
	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	public boolean equals(Object other) {
		if (other instanceof DocumentCategory) {
			DocumentCategory otherDocumentCategory = (DocumentCategory) other;
			try {
				final boolean areEqual = documentChecklistId
						.equals(otherDocumentCategory.documentChecklistId)
						&& documentChecklistCategory
								.equals(otherDocumentCategory.documentChecklistCategory)
						&& documentCategoryLevel
								.equals(otherDocumentCategory.documentCategoryLevel)
						&& companyId.equals(otherDocumentCategory.companyId);
				return areEqual;
			} catch (Exception e) {
				if (System.identityHashCode(this) == System
						.identityHashCode(other))
					return true;
				else
					return false;
			}
		}
		return false;
	}

	public int hashCode() {
		if (null != documentChecklistId && null != documentChecklistCategory
				&& null != documentCategoryLevel && null != companyId)
			return documentChecklistId.hashCode()
					+ documentChecklistCategory.hashCode()
					+ documentCategoryLevel.hashCode() + companyId.hashCode();
		else
			return 14;
	}
}
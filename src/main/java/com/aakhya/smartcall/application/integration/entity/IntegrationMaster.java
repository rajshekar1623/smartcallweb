package com.aakhya.smartcall.application.integration.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
@Entity
@Table(name = "sc_integrationMaster")
@IdClass(IntegrationMasterPk.class)
public class IntegrationMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6638565562246305929L;
	private Long integrationId;
	private Long companyId;
	private String integrationDescription;
	private Long protocol;
	private Long fileFormat;
	private String fileFormatType;
	private Long frequency;
	private Long dataSetType;
	private String delimiter;
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
	private Long genericNumber1;
	private Long genericNumber2;
	private Long genericNumber3;
	private Long genericNumber4;
	private Long genericNumber5;
	private Long genericNumber6;
	private Long genericNumber7;
	private Long genericNumber8;
	private Long genericNumber9;
	private Long genericNumber10;
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
	private List<IntegrationFileFormatDetail> integrationFileFormatDetails;

	@Id
	@Column(name = "integrationid")
	public Long getIntegrationId() {
		return integrationId;
	}

	public void setIntegrationId(Long integrationId) {
		this.integrationId = integrationId;
	}

	@Id
	@Column(name = "companyid")
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Column(name = "integrationdescription")
	public String getIntegrationDescription() {
		return integrationDescription;
	}

	public void setIntegrationDescription(String integrationDescription) {
		this.integrationDescription = integrationDescription;
	}

	@Column(name = "protocol")
	public Long getProtocol() {
		return protocol;
	}

	public void setProtocol(Long protocol) {
		this.protocol = protocol;
	}

	@Column(name = "fileformat")
	public Long getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(Long fileFormat) {
		this.fileFormat = fileFormat;
	}

	@Formula(value = "dbo.fn_getGenericClassifier(fileFormat,companyId)")
	public String getFileFormatType() {
		return fileFormatType;
	}

	public void setFileFormatType(String fileFormatType) {
		this.fileFormatType = fileFormatType;
	}

	@Column(name = "frequency")
	public Long getFrequency() {
		return frequency;
	}

	public void setFrequency(Long frequency) {
		this.frequency = frequency;
	}

	@Column(name = "datasettype")
	public Long getDataSetType() {
		return dataSetType;
	}

	public void setDataSetType(Long dataSetType) {
		this.dataSetType = dataSetType;
	}

	@Column(name = "delimiter")
	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	@Column(name = "genericString1")
	public String getGenericString1() {
		return genericString1;
	}

	public void setGenericString1(String genericString1) {
		this.genericString1 = genericString1;
	}

	@Column(name = "genericString2")
	public String getGenericString2() {
		return genericString2;
	}

	public void setGenericString2(String genericString2) {
		this.genericString2 = genericString2;
	}

	@Column(name = "genericString3")
	public String getGenericString3() {
		return genericString3;
	}

	public void setGenericString3(String genericString3) {
		this.genericString3 = genericString3;
	}

	@Column(name = "genericString4")
	public String getGenericString4() {
		return genericString4;
	}

	public void setGenericString4(String genericString4) {
		this.genericString4 = genericString4;
	}

	@Column(name = "genericString5")
	public String getGenericString5() {
		return genericString5;
	}

	public void setGenericString5(String genericString5) {
		this.genericString5 = genericString5;
	}

	@Column(name = "genericString6")
	public String getGenericString6() {
		return genericString6;
	}

	public void setGenericString6(String genericString6) {
		this.genericString6 = genericString6;
	}

	@Column(name = "genericString7")
	public String getGenericString7() {
		return genericString7;
	}

	public void setGenericString7(String genericString7) {
		this.genericString7 = genericString7;
	}

	@Column(name = "genericString8")
	public String getGenericString8() {
		return genericString8;
	}

	public void setGenericString8(String genericString8) {
		this.genericString8 = genericString8;
	}

	@Column(name = "genericString9")
	public String getGenericString9() {
		return genericString9;
	}

	public void setGenericString9(String genericString9) {
		this.genericString9 = genericString9;
	}

	@Column(name = "genericString10")
	public String getGenericString10() {
		return genericString10;
	}

	public void setGenericString10(String genericString10) {
		this.genericString10 = genericString10;
	}

	@Column(name = "genericnumber1")
	public Long getGenericNumber1() {
		return genericNumber1;
	}

	public void setGenericNumber1(Long genericNumber1) {
		this.genericNumber1 = genericNumber1;
	}

	@Column(name = "genericnumber2")
	public Long getGenericNumber2() {
		return genericNumber2;
	}

	public void setGenericNumber2(Long genericNumber2) {
		this.genericNumber2 = genericNumber2;
	}

	@Column(name = "genericnumber3")
	public Long getGenericNumber3() {
		return genericNumber3;
	}

	public void setGenericNumber3(Long genericNumber3) {
		this.genericNumber3 = genericNumber3;
	}

	@Column(name = "genericnumber4")
	public Long getGenericNumber4() {
		return genericNumber4;
	}

	public void setGenericNumber4(Long genericNumber4) {
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

	@OneToMany(mappedBy = "integrationMaster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public List<IntegrationFileFormatDetail> getIntegrationFileFormatDetails() {
		return integrationFileFormatDetails;
	}

	public void setIntegrationFileFormatDetails(List<IntegrationFileFormatDetail> integrationFileFormatDetails) {
		this.integrationFileFormatDetails = integrationFileFormatDetails;
	}
	
	
}

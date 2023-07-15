package com.aakhya.smartcall.application.security.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sc_role")
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -510099259734103639L;
	private Long roleId;
	private String description;
	private String status;
	private Long companyId;
	private Date validFrom;
	private Date validTo;
	private String createdBy;
	private Date createdDateTime;
	private Date updatedDateTime;
	private Date removeDateTime;
	private String generic1;
	private String generic2;
	private String generic3;
	private String generic4;
	private String generic5;
	private Date genericDate1;
	private Date genericDate2;
	private Date genericDate3;
	private Date genericDate4;
	private Date genericDate5;
	private BigDecimal genericNumber1;
	private BigDecimal genericNumber2;
	private BigDecimal genericNumber3;
	private BigDecimal genericNumber4;
	private BigDecimal genericNumber5;
	public Role() {
		super();
	}
	public Role(Long roleId, String description) {
		super();
		this.roleId = roleId;
		this.description = description;
	}
	@Id
	@Column(name = "roleid")
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "companyid")
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
	@Column(name = "generic1")
	public String getGeneric1() {
		return generic1;
	}
	public void setGeneric1(String generic1) {
		this.generic1 = generic1;
	}
	@Column(name = "generic2")
	public String getGeneric2() {
		return generic2;
	}
	public void setGeneric2(String generic2) {
		this.generic2 = generic2;
	}
	@Column(name = "generic3")
	public String getGeneric3() {
		return generic3;
	}
	public void setGeneric3(String generic3) {
		this.generic3 = generic3;
	}
	@Column(name = "generic4")
	public String getGeneric4() {
		return generic4;
	}
	public void setGeneric4(String generic4) {
		this.generic4 = generic4;
	}
	@Column(name = "generic5")
	public String getGeneric5() {
		return generic5;
	}
	public void setGeneric5(String generic5) {
		this.generic5 = generic5;
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
	@Column(name = "genericnumber1")
	public BigDecimal getGenericNumber1() {
		return genericNumber1;
	}
	public void setGenericNumber1(BigDecimal genericNumber1) {
		this.genericNumber1 = genericNumber1;
	}
	@Column(name = "genericnumber2")
	public BigDecimal getGenericNumber2() {
		return genericNumber2;
	}
	public void setGenericNumber2(BigDecimal genericNumber2) {
		this.genericNumber2 = genericNumber2;
	}
	@Column(name = "genericnumber3")
	public BigDecimal getGenericNumber3() {
		return genericNumber3;
	}
	public void setGenericNumber3(BigDecimal genericNumber3) {
		this.genericNumber3 = genericNumber3;
	}
	@Column(name = "genericnumber4")
	public BigDecimal getGenericNumber4() {
		return genericNumber4;
	}
	public void setGenericNumber4(BigDecimal genericNumber4) {
		this.genericNumber4 = genericNumber4;
	}
	@Column(name = "genericnumber5")
	public BigDecimal getGenericNumber5() {
		return genericNumber5;
	}
	public void setGenericNumber5(BigDecimal genericNumber5) {
		this.genericNumber5 = genericNumber5;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Role) {
			Role otherRole = (Role)obj;
			try {
				final boolean areEqual = roleId.equals(otherRole.roleId);
				return areEqual;
			} catch (Exception e) {
				if(System.identityHashCode(this) == System.identityHashCode(obj))
					return true;
				else
					return false;
			}
		}
		return false;
	}
	@Override
	public int hashCode() {
		if(null != roleId)
			return roleId.hashCode();
		else return 3377;
	}
}

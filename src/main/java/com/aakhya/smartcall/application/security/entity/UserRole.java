package com.aakhya.smartcall.application.security.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "sc_userrole")
@IdClass(UserRolePk.class)
public class UserRole implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -421979781384982188L;
	private String userId;
	private Long roleId;
	private String roleDescription;
	private User user;
	private Long companyId;
	private Date validFrom;
	private Date validTo;
	private String status;
	@Id
	@Column(name = "userid")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Id
	@Column(name = "roleid")
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	@Formula(value = "dbo.fn_getRoleDescription(roleId,companyId)")
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
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
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		if(null != roleDescription) {
			return roleDescription;
		}else
			return "";
	}
}

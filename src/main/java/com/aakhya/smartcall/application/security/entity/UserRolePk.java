package com.aakhya.smartcall.application.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class UserRolePk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4751561744094614927L;
	private String userId;
	private Long roleId;
	public UserRolePk() {
		super();
	}
	public UserRolePk(String userId, Long roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}
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
	@Override
	public boolean equals(Object other) {
		if(other instanceof UserRolePk) {
			UserRolePk otherPk = (UserRolePk)other;
			try {
				final boolean areEqual = userId.equals(otherPk.userId)
						&& roleId.equals(otherPk.roleId);
				return areEqual;
			} catch (Exception e) {
				if(System.identityHashCode(this) == System.identityHashCode(other))
					return true;
				else
					return false;
			}
		}
		return false;
	}
	@Override
	public int hashCode() {
		if(null != userId && null != roleId)
			return userId.hashCode()+roleId.hashCode();
		else
			return 387;
	}
}

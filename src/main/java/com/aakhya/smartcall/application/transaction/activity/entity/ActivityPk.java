package com.aakhya.smartcall.application.transaction.activity.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class ActivityPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7815751309867873939L;
	private Long activityId;
	private Long companyId;
	public ActivityPk() {
		super();
	}
	public ActivityPk(Long activityId, Long companyId) {
		super();
		this.activityId = activityId;
		this.companyId = companyId;
	}
	@Id
	@Column(name = "activityid")
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	@Id
	@Column(name = "companyid")
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	@Override
	public boolean equals(Object other) {
		if(other instanceof ActivityPk) {
			ActivityPk otherPk = (ActivityPk) other;
			try {
				final boolean areEqual = activityId.equals(otherPk.activityId)
						&& companyId.equals(otherPk.companyId);
				return areEqual;
			}catch(Exception e) {
				if(System.identityHashCode(this) == System.identityHashCode(other))
					return true;
				else
					return false;
			}
		}else
			return false;
	}
	
	@Override
	public int hashCode() {
		if(null != activityId && null != companyId)
			return activityId.hashCode()+companyId.hashCode();
		else
			return 1337;
	}
}

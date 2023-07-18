package com.aakhya.smartcall.application.transaction.activity.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class ActivityDetailPk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8907801278804355704L;
	private Long activityId;
	private Long companyId;
	private Long attemptSequence;
	
	
	public ActivityDetailPk() {
		super();
	}

	public ActivityDetailPk(Long activityId, Long companyId, Long attemptSequence) {
		super();
		this.activityId = activityId;
		this.companyId = companyId;
		this.attemptSequence = attemptSequence;
	}

	@Id
	@Column(name = "activityid")
	public Long getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	@Id
	@Column(name = "companyid")
	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	@Id
	@Column(name = "attemptsequence")
	public Long getAttemptSequence() {
		return this.attemptSequence;
	}

	public void setAttemptSequence(Long attemptSequence) {
		this.attemptSequence = attemptSequence;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof ActivityDetailPk) {
			ActivityDetailPk otherPk = (ActivityDetailPk)other;
			try {
				final boolean areEqual = activityId.equals(otherPk.activityId)
						&& companyId.equals(otherPk.companyId)
						&& attemptSequence.equals(otherPk.attemptSequence);
				return areEqual;
			} catch (Exception e) {
				if(System.identityHashCode(other) == System.identityHashCode(this))
					return true;
				else
					return false;
			}
		}else
			return false;
	}
	
}

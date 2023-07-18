package com.aakhya.smartcall.application.document.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class DocumentChecklistPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2015L;
	private Long documentChecklistId;
	private Long companyId;

	public DocumentChecklistPK() {
		super();
	}

	public DocumentChecklistPK(Long documentChecklistId, Long companyId) {
		super();
		this.documentChecklistId = documentChecklistId;
		this.companyId = companyId;
	}

	@Id
	@Column(name = "documentchecklistid")
	public Long getDocumentChecklistId() {
		return documentChecklistId;
	}

	public void setDocumentChecklistId(Long documentChecklistId) {
		this.documentChecklistId = documentChecklistId;
	}

	@Id
	@Column(name = "companyid")
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public boolean equals(Object other) {
		if (other instanceof DocumentChecklistPK) {
			DocumentChecklistPK otherPK = (DocumentChecklistPK) other;
			try {
				final boolean areEqual = documentChecklistId
						.equals(otherPK.documentChecklistId)
						&& companyId.equals(otherPK.companyId);
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
		if (null != documentChecklistId && null != companyId) {
			return documentChecklistId.hashCode() + companyId.hashCode();
		} else {
			return 2015;
		}
	}
}

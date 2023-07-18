package com.aakhya.smartcall.application.document.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class DocumentPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2013L;
	private Long documentId;
	private Long documentChecklistId;
	private Long documentChecklistCategory;
	private Long documentCategoryLevel;
	private Long companyId;

	public DocumentPK() {
		super();
	}

	public DocumentPK(Long documentId, Long documentChecklistId,
			Long documentChecklistCategory, Long documentCategoryLevel,Long companyId) {
		super();
		this.documentId = documentId;
		this.documentChecklistId = documentChecklistId;
		this.documentChecklistCategory = documentChecklistCategory;
		this.documentCategoryLevel = documentCategoryLevel;
		this.companyId = companyId;
	}

	public boolean equals(Object other) {
		if (other instanceof DocumentPK) {
			final DocumentPK otherPK = (DocumentPK) other;
			try {
				final boolean areEqual = (otherPK.documentId.equals(documentId)
						&& otherPK.documentChecklistId.equals(documentChecklistId)
						&& otherPK.documentChecklistCategory
								.equals(documentChecklistCategory) && otherPK.documentCategoryLevel
						.equals(documentCategoryLevel) && companyId.equals(otherPK.companyId));
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
		if (null != documentId && null != documentChecklistId
				&& null != documentChecklistCategory && null != companyId)
			return documentId.hashCode() + documentChecklistId.hashCode()
					+ documentChecklistCategory.hashCode()+companyId.hashCode();
		else
			return 2013;
	}
	
	@Id
	@Column(name = "documentid")
	public Long getDocumentId() {
		return this.documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

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

	@Id
	@Column(name = "documentcategorylevel")
	public Long getDocumentCategoryLevel() {
		return documentCategoryLevel;
	}

	public void setDocumentCategoryLevel(Long documentCategoryLevel) {
		this.documentCategoryLevel = documentCategoryLevel;
	}

	@Id
	@Column(name = "companyid")
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}
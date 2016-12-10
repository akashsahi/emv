package com.akash.evm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.akash.evm.enums.DocumentStatusEnum;

@Entity
@Table(name = "DOCUMENTS")
public class Documents extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3348880387008279842L;
	@SequenceGenerator(sequenceName = "DOCUMENTS_SEQ", name = "DOCUMENTS_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "DOCUMENTS_SEQ", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "DOCUMENT_ID")
	private Long documentId;
	@Column(name = "DOCUMENT_NAME", unique = false, nullable = false)
	private String documentName;
	@Column(name = "DOCUMENT_DESCRIPTION")
	private String documentDescription;
	@Column(name = "START_DATE", nullable = false)
	private Date startDate;
	@Column(name = "END_DATE", nullable = false)
	private Date endDate;
	@Column(name = "STATUS")
	@Enumerated(EnumType.ORDINAL)
	private DocumentStatusEnum status;
	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	@ManyToOne
	private Category category;

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentDescription() {
		return documentDescription;
	}

	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public DocumentStatusEnum getStatus() {
		return status;
	}

	public void setStatus(DocumentStatusEnum status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Documents [");
		if (documentId != null)
			builder.append("documentId=").append(documentId).append(", ");
		if (documentName != null)
			builder.append("documentName=").append(documentName).append(", ");
		if (documentDescription != null)
			builder.append("documentDescription=").append(documentDescription).append(", ");
		if (startDate != null)
			builder.append("startDate=").append(startDate).append(", ");
		if (endDate != null)
			builder.append("endDate=").append(endDate).append(", ");
		if (status != null)
			builder.append("status=").append(status).append(", ");
		if (category != null)
			builder.append("category=").append(category);
		builder.append("]");
		return builder.toString();
	}

}

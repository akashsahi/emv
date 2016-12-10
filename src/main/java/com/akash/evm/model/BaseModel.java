package com.akash.evm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseModel implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 416480999204811261L;

	@Version
	@Column(name = "VERSION", nullable = false)
	private Integer version;
	@Column(name = "CREATED_BY", nullable = false)
	private Long createdBy;
	@Column(name = "UPDATED_BY")
	private Long updatedBy;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", length = 19)
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE", length = 19)
	private Date updatedDate;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@PrePersist
	public void beforeCreate() {
		// TODO Delete this line
		createdBy = 1L;
		Date date = new Date();
		this.createdDate = date;
		this.updatedDate = null;
		this.version = 1;
	}

	@PreUpdate
	public void beforeUpdate() {
		updatedBy = 1L;
		this.updatedDate = new Date();
		this.version = null != this.version ? this.version + 1 : 1;
	}
}

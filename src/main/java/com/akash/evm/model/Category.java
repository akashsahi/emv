package com.akash.evm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.akash.evm.enums.CategorytStatusEnum;

@Entity
@Table(name = "CATEGORY")
public class Category extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3410452714101725506L;
	@SequenceGenerator(sequenceName = "CATEGORY_SEQ", name = "CATEGORY_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "CATEGORY_SEQ", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "CATEGORY_ID")
	private Long categoryId;
	@Column(name = "CATEGORY_NAME", unique = true, nullable = false)
	private String categoryName;
	@Column(name = "CATEGORY_DESCRIPTION")
	private String categoryDescription;
	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private CategorytStatusEnum status;

	public Long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getCategoryDescription() {
		return categoryDescription;
	}


	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}


	public CategorytStatusEnum getStatus() {
		return status;
	}


	public void setStatus(CategorytStatusEnum status) {
		this.status = status;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Category [");
		if (categoryId != null) {
			builder.append("categoryId=");
			builder.append(categoryId);
			builder.append(", ");
		}
		if (categoryName != null) {
			builder.append("categoryName=");
			builder.append(categoryName);
			builder.append(", ");
		}
		if (categoryDescription != null) {
			builder.append("categoryDescription=");
			builder.append(categoryDescription);
			builder.append(", ");
		}
		if (status != null) {
			builder.append("status=");
			builder.append(status);
			builder.append(", ");
		}
		builder.append("]");
		return builder.toString();
	}


}

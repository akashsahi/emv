package com.akash.evm.model;

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

import com.akash.evm.enums.BrandtStatusEnum;

@Entity
@Table(name = "BRAND")
public class Brand extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3348880387008279842L;
	@SequenceGenerator(sequenceName = "BRAND_SEQ", name = "BRAND_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "BRAND_SEQ", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "BRAND_ID")
	private Long brandId;
	@Column(name = "BRAND_NAME", unique = true, nullable = false)
	private String brandName;
	@Column(name = "BRAND_DESCRIPTION")
	private String brandDescription;
	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private BrandtStatusEnum status;
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandDescription() {
		return brandDescription;
	}

	public void setBrandDescription(String brandDescription) {
		this.brandDescription = brandDescription;
	}

	public BrandtStatusEnum getStatus() {
		return status;
	}

	public void setStatus(BrandtStatusEnum status) {
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
		builder.append("Brand [");
		if (brandId != null)
			builder.append("brandId=").append(brandId).append(", ");
		if (brandName != null)
			builder.append("brandName=").append(brandName).append(", ");
		if (brandDescription != null)
			builder.append("brandDescription=").append(brandDescription).append(", ");
		if (status != null)
			builder.append("status=").append(status).append(", ");
		if (category != null)
			builder.append("category=").append(category);
		builder.append("]");
		return builder.toString();
	}

}

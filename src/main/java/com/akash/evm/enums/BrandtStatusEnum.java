package com.akash.evm.enums;

public enum BrandtStatusEnum {

	ACTIVE("Active"), INACTIVE("Inactive"), DELETED("Deleted");

	private String status;

	private BrandtStatusEnum(String userStatus) {
		this.status = userStatus;
	}

	public String getStatus() {
		return status;
	}

	public static BrandtStatusEnum fromValue(String value) {
		for (BrandtStatusEnum enumerationValue : BrandtStatusEnum.values()) {
			if (enumerationValue.getStatus().equals(value)) {
				return enumerationValue;
			}
		}
		throw new IllegalArgumentException(value);
	}
}

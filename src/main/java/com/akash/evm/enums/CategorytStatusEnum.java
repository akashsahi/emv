package com.akash.evm.enums;

public enum CategorytStatusEnum {

	ACTIVE("Active"), INACTIVE("Inactive"), DELETED("Deleted");

	private String status;

	private CategorytStatusEnum(String userStatus) {
		this.status = userStatus;
	}

	public String getStatus() {
		return status;
	}

	public static CategorytStatusEnum fromValue(String value) {
		for (CategorytStatusEnum enumerationValue : CategorytStatusEnum.values()) {
			if (enumerationValue.getStatus().equals(value)) {
				return enumerationValue;
			}
		}
		throw new IllegalArgumentException(value);
	}
}

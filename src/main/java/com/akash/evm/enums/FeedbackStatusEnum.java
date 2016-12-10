package com.akash.evm.enums;

public enum FeedbackStatusEnum {

	ACTIVE("Active"), INACTIVE("Inactive"), DELETED("Deleted");

	private String status;

	private FeedbackStatusEnum(String userStatus) {
		this.status = userStatus;
	}

	public String getStatus() {
		return status;
	}

	public static FeedbackStatusEnum fromValue(String value) {
		for (FeedbackStatusEnum enumerationValue : FeedbackStatusEnum.values()) {
			if (enumerationValue.getStatus().equals(value)) {
				return enumerationValue;
			}
		}
		throw new IllegalArgumentException(value);
	}
}

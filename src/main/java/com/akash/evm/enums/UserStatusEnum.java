package com.akash.evm.enums;

public enum UserStatusEnum {

	ACTIVE("Active"), INACTIVE("Inactive"), SUSPENDED("Suspended"), LOCKED("Locked"),DELETED("Deleted");

	private String status;

	private UserStatusEnum(String userStatus) {
		this.status = userStatus;
	}

	public String getStatus() {
		return status;
	}

	public static UserStatusEnum fromValue(String value) {
		for (UserStatusEnum enumerationValue : UserStatusEnum.values()) {
			if (enumerationValue.getStatus().equals(value)) {
				return enumerationValue;
			}
		}
		throw new IllegalArgumentException(value);
	}
}

package com.akash.evm.enums;

public enum DocumentStatusEnum {

	ACTIVE("Active"), INACTIVE("Inactive"), DELETED("Deleted");

	private String status;

	private DocumentStatusEnum(String userStatus) {
		this.status = userStatus;
	}

	public String getStatus() {
		return status;
	}

	public static DocumentStatusEnum fromValue(String value) {
		for (DocumentStatusEnum enumerationValue : DocumentStatusEnum.values()) {
			if (enumerationValue.getStatus().equals(value)) {
				return enumerationValue;
			}
		}
		throw new IllegalArgumentException(value);
	}
}

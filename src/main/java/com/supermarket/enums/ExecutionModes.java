package com.supermarket.enums;

public enum ExecutionModes {

	INTERACTIVE("interactive"),
	FILE("file");

	private String mode;

	ExecutionModes(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public static ExecutionModes getValueOf(final String mode) {
		if (mode != null) {
			for (ExecutionModes value : values()) {
				if (mode.equals(value.getMode())) {
					return value;
				}
			}
		}
		return null;
	}
}
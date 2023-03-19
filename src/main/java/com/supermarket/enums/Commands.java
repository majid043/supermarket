package com.supermarket.enums;

public enum Commands {

	CHECKOUT("checkout"),
	ADD("add"),
	OFFER("offer"),
	BILL("bill");

	private String command;

	Commands(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

	public static Commands getValueOf(final String command) {
		if (command != null) {
			for (Commands value : values()) {
				if (command.equals(value.getCommand())) {
					return value;
				}
			}
		}
		return null;
	}
}
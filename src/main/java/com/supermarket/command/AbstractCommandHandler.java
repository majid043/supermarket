package com.supermarket.command;

import java.util.List;

import com.supermarket.enums.Commands;

public abstract class AbstractCommandHandler {

	public static AbstractCommandHandler getInstance(String command) {
		AbstractCommandHandler commandHandler = null;
		Commands checkValue = Commands.getValueOf(command);
		if (null == checkValue) {
			return commandHandler;
		}
		switch (checkValue) {
		case ADD:
			commandHandler = new AddItemInCartImpl();
			break;
		case OFFER:
			commandHandler = new ApplyOfferImpl();
			break;
		}
		return commandHandler;
	}

	public abstract String executeCommand(List<String> inputList);

}
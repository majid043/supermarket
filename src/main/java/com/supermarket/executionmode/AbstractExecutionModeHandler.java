package com.supermarket.executionmode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.supermarket.command.AbstractCommandHandler;
import com.supermarket.enums.ExecutionModes;
import com.supermarket.util.CommonUtils;
import com.supermarket.util.Constants;

public abstract class AbstractExecutionModeHandler {

	public static AbstractExecutionModeHandler getInstance(ExecutionModes mode) {
		AbstractExecutionModeHandler executionModeHandler = null;
		switch (mode) {
		case FILE:
			executionModeHandler = new FileModeImpl();
			break;
		case INTERACTIVE:
			executionModeHandler = new InteractiveModeImpl();
			break;
		}
		return executionModeHandler;
	}

	public abstract void execute(String fileName) throws IOException;

	protected void executeCommand(List<String> inputList) {
		List<String> modifiableList = new ArrayList<>(inputList);
		AbstractCommandHandler commandHandler = AbstractCommandHandler.getInstance(modifiableList.get(0));
		if (null == commandHandler) {
			System.out.println(CommonUtils.concatValues(Constants.INVALID_INPUT, Constants.SPACE, Constants.ERROR_CODE_10));
			return;
		}
		modifiableList.remove(0);
		System.out.println(commandHandler.executeCommand(modifiableList));
	}

}
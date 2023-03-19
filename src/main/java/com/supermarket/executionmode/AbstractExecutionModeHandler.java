package com.supermarket.executionmode;

import java.io.IOException;
import java.util.List;

import com.supermarket.enums.ExecutionModes;

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

	protected void executeCommand(List<String> inputList) {}

}
package com.supermarket.executionmode;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.supermarket.util.CommonUtils;
import com.supermarket.util.Constants;

class InteractiveModeImpl extends AbstractExecutionModeHandler {

	@Override
	public void execute(String fileName) {
		String userInput = null;

		try (Scanner in = new Scanner(System.in)) {
			while(true) {
				userInput = in.nextLine();
				if (CommonUtils.isNullOrEmptyString(userInput)) {
					continue;
				}
				String[] tokens = CommonUtils.tokenizeString(userInput, Constants.SPACE, false, -1);
				List<String> inputList = Arrays.asList(tokens);
				executeCommand(inputList);
			}
		}
	}

}
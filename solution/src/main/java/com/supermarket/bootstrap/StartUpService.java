package com.supermarket.bootstrap;

import com.supermarket.enums.ExecutionModes;
import com.supermarket.executionmode.AbstractExecutionModeHandler;
import com.supermarket.inventory.ManageInventory;
import com.supermarket.offers.ManageOffer;
import com.supermarket.util.CommonUtils;
import com.supermarket.util.Constants;

public class StartUpService {

	public static void main(String[] args) {
		ExecutionModes mode;
		try {
			mode = evaluateExecutionMode(args);
			if (null == mode) {
				System.out.println(CommonUtils.concatValues(Constants.INVALID_INPUT, Constants.SPACE, Constants.ERROR_CODE_09));
				return;
			}
			ManageInventory.getInstance().loadInventoryFromFile(args[0]);
			ManageOffer.getInstance().loadOffers();
			AbstractExecutionModeHandler executionModeHandler = AbstractExecutionModeHandler.getInstance(mode);
			if (ExecutionModes.FILE == mode) {
				executionModeHandler.execute(args[1]);
			} else {
				executionModeHandler.execute(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	private static ExecutionModes evaluateExecutionMode(String[] args) {
		if (args.length == 1) {
			return ExecutionModes.INTERACTIVE;
		} 
		if (args.length == 2) {
			return ExecutionModes.FILE;
		}
		return null;
	}

}
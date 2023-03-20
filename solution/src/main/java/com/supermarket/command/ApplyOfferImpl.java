package com.supermarket.command;

import java.util.List;

import com.supermarket.cart.ManageCart;
import com.supermarket.util.CommonUtils;
import com.supermarket.util.Constants;

class ApplyOfferImpl extends AbstractCommandHandler {

	@Override
	public String executeCommand(List<String> inputList) {
		if (CommonUtils.isNullOrEmptyCollection(inputList) || inputList.size() != 2) {
			return CommonUtils.concatValues(Constants.INVALID_INPUT, Constants.SPACE, Constants.ERROR_CODE_13);
		}
		return ManageCart.getInstance().applyOfferOnItem(inputList.get(1), inputList.get(0));
	}

}
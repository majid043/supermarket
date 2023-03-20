package com.supermarket.command;

import java.util.List;

import com.supermarket.cart.ManageCart;

class CheckoutImpl extends AbstractCommandHandler {

	@Override
	public String executeCommand(List<String> inputList) {
		return ManageCart.getInstance().doCheckout();
	}

}
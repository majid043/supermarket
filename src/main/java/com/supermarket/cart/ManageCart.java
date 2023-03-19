package com.supermarket.cart;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.supermarket.bean.Cart;

public class ManageCart {
	private Map<String, Cart> cart;

	private ManageCart() {
		cart = new ConcurrentHashMap<>();
	}

	public static ManageCart getInstance() {
		return Singleton.INSTANCE;
	}

	public Map<String, Cart> getCart() {
		return cart;
	}

	public static final class Singleton {
		private static final ManageCart INSTANCE = new ManageCart();
	}

	public String validateAndAddItemInCart(String productName, Integer quantity) {
		return null;
	}

	public String applyOfferOnItem(String productName, String offer) {
		return null;
	}

	public String calculateBill() {
		return null;
	}

	public String doCheckout() {
		return null;
	}

}
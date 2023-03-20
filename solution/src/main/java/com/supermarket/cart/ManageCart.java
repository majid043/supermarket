package com.supermarket.cart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.supermarket.bean.Cart;
import com.supermarket.bean.Inventory;
import com.supermarket.inventory.ManageInventory;
import com.supermarket.offers.ManageOffer;
import com.supermarket.util.CommonUtils;
import com.supermarket.util.Constants;

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
		String result = Constants.INVALID_INPUT;

		if (CommonUtils.isNullOrEmptyString(productName) || CommonUtils.isNullOrZeroOrNegativeInt(quantity)) {
			return CommonUtils.concatValues(result, Constants.SPACE, Constants.ERROR_CODE_01);
		}

		Map<String, Inventory> inventory = ManageInventory.getInstance().getInventory();

		if (CommonUtils.isNullOrEmptyMap(inventory)) {
			return CommonUtils.concatValues(result, Constants.SPACE, Constants.ERROR_CODE_02);
		}

		Inventory record = inventory.get(productName);
		if (null == record) {
			return CommonUtils.concatValues(result, Constants.SPACE, Constants.ERROR_CODE_03);
		}

		if (quantity > record.getQuantity()) {
			return CommonUtils.concatValues(result, Constants.SPACE, Constants.ERROR_CODE_04);
		}

		Cart cart = getCart().get(productName);

		if (null == cart) {
			cart = new Cart(productName, record.getAmount(), quantity);
			CommonUtils.putValueInMap(getCart(), productName, cart);
			record.setQuantity(record.getQuantity() - quantity);
		} else {
			cart.setQuantity(cart.getQuantity() + quantity);
			record.setQuantity(record.getQuantity() - quantity);
		}

		return CommonUtils.concatValues(Constants.KEYWORD_ADDED, Constants.SPACE, productName, Constants.SPACE, quantity);
	}

	public String applyOfferOnItem(String productName, String offer) {
		String result = Constants.INVALID_INPUT;

		if (CommonUtils.isNullOrEmptyString(productName) || CommonUtils.isNullOrEmptyString(offer)) {
			return CommonUtils.concatValues(result, Constants.SPACE, Constants.ERROR_CODE_05);
		}

		List<String> offers = ManageOffer.getInstance().getOffers();

		if (CommonUtils.isNullOrEmptyCollection(offers)) {
			return CommonUtils.concatValues(result, Constants.SPACE, Constants.ERROR_CODE_06);
		}

		if (!offers.contains(offer)) {
			return CommonUtils.concatValues(result, Constants.SPACE, Constants.ERROR_CODE_07);
		}

		Cart cart = getCart().get(productName);
		if (null == cart) {			
			return CommonUtils.concatValues(result, Constants.SPACE, Constants.ERROR_CODE_08);
		} else {
			cart.setOffer(offer);
		}

		return CommonUtils.concatValues(Constants.KEYWORD_OFFER, Constants.SPACE, Constants.KEYWORD_ADDED);
	}

	public String calculateBill() {
		BigDecimal subTotal = BigDecimal.ZERO;
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal discount = BigDecimal.ZERO;

		String subTotalStr;
		String totalStr;
		String discountStr;

		for (Entry<String, Cart> entry : getCart().entrySet()) {
			Cart cart = entry.getValue();
			calculateItemBill(cart);
			subTotal = cart.getSubTotal().add(subTotal);
			discount = cart.getDiscount().add(discount);
			total = cart.getTotal().add(total);
		}

		subTotalStr = CommonUtils.formatDecimalValue(subTotal, Constants.ROUND_PATTERN);
		totalStr = CommonUtils.formatDecimalValue(total, Constants.ROUND_PATTERN);
		discountStr = CommonUtils.formatDecimalValue(discount, Constants.ROUND_PATTERN);

		return CommonUtils.concatValues(Constants.KEYWORD_SUBTOTAL, Constants.COLON, subTotalStr, Constants.COMMA, Constants.SPACE,
				Constants.KEYWORD_DISCOUNT, Constants.COLON, discountStr, Constants.COMMA, Constants.SPACE,
				Constants.KEYWORD_TOTAL, Constants.COLON, totalStr);
	}

	private void calculateItemBill(Cart cart) {
		cart.setSubTotal(cart.getUnitPrice().multiply(BigDecimal.valueOf(cart.getQuantity())));
		if (CommonUtils.isNullOrEmptyString(cart.getOffer())) {
			cart.setTotal(cart.getSubTotal());
			cart.setDiscount(BigDecimal.ZERO);
			return;
		}
		if (Constants.BUY_2_GET_1_FREE.equalsIgnoreCase(cart.getOffer())) {
			buy2Get1FreeOffer(cart);
		}
		if (Constants.BUY_1_GET_HALF_OFF.equalsIgnoreCase(cart.getOffer())) {
			buy1GetHalfOffOffer(cart);
		}		
	}

	private void buy2Get1FreeOffer(Cart cart) {
		Integer discountedItemsCount = cart.getQuantity()/3;
		cart.setDiscount(cart.getUnitPrice().multiply(BigDecimal.valueOf(discountedItemsCount)));
		cart.setTotal(cart.getSubTotal().subtract(cart.getDiscount()));
	}

	private void buy1GetHalfOffOffer(Cart cart) {
		Integer discountedItemsCount = cart.getQuantity()/2;		
		cart.setDiscount(cart.getUnitPrice().divide(BigDecimal.valueOf(2), 2, RoundingMode.CEILING).multiply(BigDecimal.valueOf(discountedItemsCount)));
		cart.setTotal(cart.getSubTotal().subtract(cart.getDiscount()));
	}

	public String doCheckout() {
		if (CommonUtils.isNullOrEmptyMap(getCart())) {
			return Constants.KEYWORD_EMPTY_CART;
		}
		getCart().clear();
		return Constants.KEYWORD_DONE;
	}

}
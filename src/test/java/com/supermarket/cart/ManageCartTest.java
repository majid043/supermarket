package com.supermarket.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.supermarket.inventory.ManageInventory;
import com.supermarket.offers.ManageOffer;
import com.supermarket.util.Constants;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ManageCartTest {


	@BeforeEach
	public void setup() throws IOException {
		ManageInventory.getInstance().loadInventoryFromFile("inventory.csv");
		ManageOffer.getInstance().loadOffers();
	}

	@Test
	@Order(1)
	public void test_empty_cart_result() {
		String result = ManageCart.getInstance().doCheckout();
		assertEquals(Constants.KEYWORD_EMPTY_CART, result);
	}

	@Test
	@Order(2)
	public void test_add_cart_soap_quantity_5() {
		String result = ManageCart.getInstance().validateAndAddItemInCart("soap", 5);
		assertEquals("added soap 5", result);
	}

	@Test
	@Order(3)
	public void test_add_cart_bread_quantity_1() {
		String result = ManageCart.getInstance().validateAndAddItemInCart("bread", 1);
		assertEquals("added bread 1", result);
	}

	@Test
	@Order(4)
	public void test_generate_bill_without_offer() {
		String result = ManageCart.getInstance().calculateBill();
		assertEquals("subtotal:52.50, discount:0.00, total:52.50", result);
	}

	@Test
	@Order(5)
	public void test_apply_offer_soap() {
		String result = ManageCart.getInstance().applyOfferOnItem("soap", "buy_2_get_1_free");
		assertEquals("offer added", result);
	}

	@Test
	@Order(6)
	public void test_generate_bill_after_offer_applied() {
		String result = ManageCart.getInstance().calculateBill();
		assertEquals("subtotal:52.50, discount:10.00, total:42.50", result);
	}

	@Test
	@Order(7)
	public void test_add_cart_soap_quantity_1() {
		String result = ManageCart.getInstance().validateAndAddItemInCart("soap", 1);
		assertEquals("added soap 1", result);
	}

	@Test
	@Order(8)
	public void test_generate_bill_after_add_more_item_in_cart() {
		String result = ManageCart.getInstance().calculateBill();
		assertEquals("subtotal:62.50, discount:20.00, total:42.50", result);
	}

	@Test
	@Order(9)
	public void test_apply_offer_bread() {
		String result = ManageCart.getInstance().applyOfferOnItem("bread", "buy_1_get_half_off");
		assertEquals("offer added", result);
	}

	@Test
	@Order(10)
	public void test_add_more_item_cart_bread_quantity_1() {
		String result = ManageCart.getInstance().validateAndAddItemInCart("bread", 1);
		assertEquals("added bread 1", result);
	}

	@Test
	@Order(11)
	public void test_generate_bill_after_add_more_items_in_cart() {
		String result = ManageCart.getInstance().calculateBill();
		assertEquals("subtotal:65.00, discount:21.25, total:43.75", result);
	}

	@Test
	@Order(12)
	public void test_checkout() {
		String result = ManageCart.getInstance().doCheckout();
		assertEquals(Constants.KEYWORD_DONE, result);
	}

	@Test
	@Order(13)
	public void test_clear_cart_after_checkout() {
		String result = ManageCart.getInstance().doCheckout();
		assertEquals(Constants.KEYWORD_EMPTY_CART, result);
	}

}
package com.supermarket.offers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.supermarket.util.Constants;


@ExtendWith(MockitoExtension.class)
class ManageOfferTest {

	@Test
	public void test_offers_count() {
		List<String> offers = new ArrayList<>();
		offers.add(Constants.BUY_2_GET_1_FREE);
		offers.add(Constants.BUY_1_GET_HALF_OFF);

		ManageOffer mock = Mockito.mock(ManageOffer.class);
		when(mock.getOffers()).thenReturn(offers);

		assertEquals(2, mock.getOffers().size());
	}

	@Test
	public void test_buy_2_get_1_free_offer() {
		List<String> offers = new ArrayList<>();
		offers.add(Constants.BUY_2_GET_1_FREE);

		ManageOffer mock = Mockito.mock(ManageOffer.class);
		when(mock.getOffers()).thenReturn(offers);

		assertTrue(mock.getOffers().contains(Constants.BUY_2_GET_1_FREE));
	}

	@Test
	public void test_buy_1_get_half_off_offer() {
		List<String> offers = new ArrayList<>();
		offers.add(Constants.BUY_1_GET_HALF_OFF);

		ManageOffer mock = Mockito.mock(ManageOffer.class);
		when(mock.getOffers()).thenReturn(offers);

		assertTrue(mock.getOffers().contains(Constants.BUY_1_GET_HALF_OFF));
	}

	@Test
	public void test_invalid_offer() {
		List<String> offers = new ArrayList<>();
		offers.add(Constants.KEYWORD_OFFER);
		ManageOffer mock = Mockito.mock(ManageOffer.class);
		when(mock.getOffers()).thenReturn(offers);

		assertFalse(mock.getOffers().contains(Constants.BUY_1_GET_HALF_OFF));
	}

}
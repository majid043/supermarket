package com.supermarket.offers;

import java.util.ArrayList;
import java.util.List;

import com.supermarket.util.Constants;

public class ManageOffer {

	private List<String> offers;

	private ManageOffer() {
		offers = new ArrayList<>();
	}

	public static ManageOffer getInstance() {
		return Singleton.INSTANCE;
	}

	public List<String> getOffers() {
		return offers;
	}

	public static final class Singleton {
		private static final ManageOffer INSTANCE = new ManageOffer();
	}

	public synchronized void loadOffers() {
		getOffers().add(Constants.BUY_2_GET_1_FREE);
		getOffers().add(Constants.BUY_1_GET_HALF_OFF);
	}
}
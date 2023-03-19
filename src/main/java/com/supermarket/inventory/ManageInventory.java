package com.supermarket.inventory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.supermarket.bean.Inventory;

public class ManageInventory {

	private Map<String, Inventory> inventory;

	private ManageInventory() {
		inventory = new ConcurrentHashMap<>();
	}

	public static ManageInventory getInstance() {
		return Singleton.INSTANCE;
	}

	public Map<String, Inventory> getInventory() {
		return inventory;
	}

	public Inventory getInventoryByProductName(String productName) {
		if (null == productName) {
			return null;
		}
		return inventory.get(productName);
	}

	public static final class Singleton {
		private static final ManageInventory INSTANCE = new ManageInventory();
	}

	
}
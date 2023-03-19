package com.supermarket.inventory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.supermarket.bean.Inventory;
import com.supermarket.fileloader.LoadFileData;
import com.supermarket.util.CommonUtils;
import com.supermarket.util.Constants;

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
		if (CommonUtils.isNullOrEmptyString(productName)) {
			return null;
		}
		return inventory.get(productName);
	}

	public static final class Singleton {
		private static final ManageInventory INSTANCE = new ManageInventory();
	}

	public synchronized void loadInventoryFromFile(String fileName) throws IOException {
		List<List<String>> values = LoadFileData.readFile(fileName, Constants.COMMA);
		values.forEach(value -> buildInventory(value));
	}

	public void buildInventory(List<String> inventoryRecord) {
		if (CommonUtils.isNullOrEmptyCollection(inventoryRecord)) {
			return;
		}
		Inventory record = new Inventory(inventoryRecord.get(0), new BigDecimal(inventoryRecord.get(1)), Integer.valueOf(inventoryRecord.get(2)));
		CommonUtils.putValueInMap(getInventory(), record.getProductName(), record);
	}
}
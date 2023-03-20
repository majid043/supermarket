package com.supermarket.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.supermarket.bean.Inventory;

@ExtendWith(MockitoExtension.class)
class ManageInventoryTest {

	@Test
	public void test_inventory_count() {
		Map<String, Inventory> inventory = new ConcurrentHashMap<>();
		inventory.put("soap", new Inventory("soap", new BigDecimal(10.00), 100));
		inventory.put("bread", new Inventory("bread", new BigDecimal(2.50), 10));

		ManageInventory mock = Mockito.mock(ManageInventory.class);
		when(mock.getInventory()).thenReturn(inventory);

		assertEquals(2, mock.getInventory().size());
	}

	@Test
	public void test_inventory_soap_quantity() {
		Map<String, Inventory> inventory = new ConcurrentHashMap<>();
		inventory.put("soap", new Inventory("soap", new BigDecimal(10.00), 100));

		ManageInventory mock = Mockito.mock(ManageInventory.class);
		when(mock.getInventory()).thenReturn(inventory);

		assertEquals(100, mock.getInventory().get("soap").getQuantity());
	}

	@Test
	public void test_inventory_bread_quantity() {
		Map<String, Inventory> inventory = new ConcurrentHashMap<>();
		inventory.put("bread", new Inventory("bread", new BigDecimal(2.50), 10));

		ManageInventory mock = Mockito.mock(ManageInventory.class);
		when(mock.getInventory()).thenReturn(inventory);

		assertEquals(10, mock.getInventory().get("bread").getQuantity());
	}

	@Test
	public void test_invalid_inventory() {
		Map<String, Inventory> inventory = new ConcurrentHashMap<>();
		inventory.put("eggs", new Inventory("eggs", new BigDecimal(15), 24));

		ManageInventory mock = Mockito.mock(ManageInventory.class);
		when(mock.getInventory()).thenReturn(inventory);

		assertFalse(null != mock.getInventory().get("soap"));
	}

}
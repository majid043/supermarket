package com.supermarket.bean;

import java.math.BigDecimal;

public class Inventory {

	private String productName;
	private BigDecimal amount;
	private Integer quantity;

	public Inventory(String productName, BigDecimal amount, Integer quantity) {
		this.productName = productName;
		this.amount = amount;
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Inventory [productName=");
		builder.append(productName);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append("]");
		return builder.toString();
	}

}
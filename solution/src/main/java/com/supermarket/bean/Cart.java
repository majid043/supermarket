package com.supermarket.bean;

import java.math.BigDecimal;

public class Cart {

	private String productName;
	private BigDecimal unitPrice;
	private Integer quantity;
	private String offer;
	private BigDecimal subTotal;
	private BigDecimal total;
	private BigDecimal discount;

	public Cart(String productName, BigDecimal unitPrice, Integer quantity) {
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getOffer() {
		return offer;
	}
	public void setOffer(String offer) {
		this.offer = offer;
	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cart [productName=");
		builder.append(productName);
		builder.append(", unitPrice=");
		builder.append(unitPrice);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", offer=");
		builder.append(offer);
		builder.append(", subTotal=");
		builder.append(subTotal);
		builder.append(", total=");
		builder.append(total);
		builder.append(", discount=");
		builder.append(discount);
		builder.append("]");
		return builder.toString();
	}
}
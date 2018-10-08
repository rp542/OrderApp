package com.capgemini.order.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ordertable")
public class Order {
	
	@Id
	private int orderId;
	private int customerId;
	private int productId;
	private LocalDate date;
	public Order(int orderId, int customerId, int products, LocalDate date) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.productId = products;
		this.date = date;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getProducts() {
		return productId;
	}
	public void setProducts(int products) {
		this.productId = products;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", productId=" + productId + ", date=" + date
				+ "]";
	}
	
	

}
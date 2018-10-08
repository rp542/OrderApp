package com.capgemini.order.service;

import java.util.List;

import com.capgemini.order.entity.Order;
import com.capgemini.order.exceptions.OrderAllReadyPresentException;
import com.capgemini.order.exceptions.OrderNotFoundException;
import com.capgemini.order.exceptions.OrdersNotFoundException;

public interface OrderService {
	public Order addOrder(Order order) throws OrderAllReadyPresentException;

	public void cancelOrder(Order order);

	public Order getOrderById(int orderId) throws OrderAllReadyPresentException, OrderNotFoundException;

	public List<Order> getOrders() throws OrdersNotFoundException;

	public List<Order> getOrdersById(int orderId);
}
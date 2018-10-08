package com.capgemini.order.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.order.entity.Order;
import com.capgemini.order.exceptions.OrderAllReadyPresentException;
import com.capgemini.order.exceptions.OrderNotFoundException;
import com.capgemini.order.exceptions.OrdersNotFoundException;
import com.capgemini.order.repository.OrderRepository;
import com.capgemini.order.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public Order addOrder(Order order) throws OrderAllReadyPresentException{
		Optional<Order> orderOptional=orderRepository.findById(order.getOrderId());
		if(!orderOptional.isPresent())
		return orderRepository.save(order);
		throw new OrderAllReadyPresentException("the order has already present in the list");
	}

	@Override
	public Order getOrderById(int orderId) throws OrderAllReadyPresentException, OrderNotFoundException{
		Optional<Order> orderOptional=orderRepository.findById(orderId);
		if(orderOptional.isPresent())
		{
			return orderOptional.get();
		}
		throw new OrderNotFoundException("order does not found");
	}

	@Override
	public List<Order> getOrders() throws OrdersNotFoundException{
		List<Order> order=orderRepository.findAll();
		if(!order.isEmpty())
		{
		return order;
		}
		throw new OrdersNotFoundException("Orders does not found");
	}

	@Override
	public List<Order> getOrdersById(int orderId) {
		Optional<Order> order= orderRepository.findById(orderId);
		List<Order> order1=(List<Order>) order.get();
		return order1;
	}
	@Override
	public void cancelOrder(Order order) {
	
		orderRepository.delete(order);
	}

}
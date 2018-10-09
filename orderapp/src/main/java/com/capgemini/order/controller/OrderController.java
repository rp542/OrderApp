package com.capgemini.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.order.entity.Order;
import com.capgemini.order.exceptions.OrderAllReadyPresentException;
import com.capgemini.order.exceptions.OrderNotFoundException;
import com.capgemini.order.exceptions.OrdersNotFoundException;
import com.capgemini.order.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping("/order")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) throws OrderAllReadyPresentException {
		ResponseEntity<Order> responseEntity = new ResponseEntity<Order>(orderService.addOrder(order), HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/orders/{orderId}")
	public ResponseEntity<Order> findOrderById(@PathVariable int orderId) throws OrderAllReadyPresentException {
		try {
			Order orderdb = orderService.getOrderById(orderId);
			return new ResponseEntity<Order>(orderdb, HttpStatus.OK);
		} catch (OrderNotFoundException exception) {
			// logged the exception
		}
		return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/order")
	public @ResponseBody ResponseEntity<List<Order>> getAllCustomer() throws OrdersNotFoundException {
		return new ResponseEntity<List<Order>>(orderService.getOrders(), HttpStatus.OK);
	}

	@GetMapping("/order1/{orderId}")
	public @ResponseBody ResponseEntity<List<Order>> getAllOrdersById(@PathVariable int orderId) {
		Order order1 = (Order) orderService.getOrdersById(orderId);
		return new ResponseEntity<List<Order>>((List<Order>) order1, HttpStatus.OK);
	}

	@DeleteMapping("/order/{orderId}")
	public ResponseEntity<Order> deleteOrderById(@PathVariable int orderId)
			throws OrderAllReadyPresentException, OrderNotFoundException {

		Order order = orderService.getOrderById(orderId);

		orderService.cancelOrder(order);
		return new ResponseEntity<Order>(HttpStatus.OK);
	}

}
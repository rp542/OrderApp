package com.capgemini.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {

}
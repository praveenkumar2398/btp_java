package com.howtodoinjava.app.services.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howtodoinjava.app.model.order.Order;
import com.howtodoinjava.app.repositories.order.OrderRepository;
import com.howtodoinjava.app.services.order.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order createOrder(Order order) {
		System.out.println("Creating order");
		return orderRepository.save(order);
	}

}

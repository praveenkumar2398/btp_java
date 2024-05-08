package com.howtodoinjava.app.controller.order;

import java.rmi.ServerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.howtodoinjava.app.model.order.Order;
import com.howtodoinjava.app.services.order.OrderService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "api/order", produces = MediaType.APPLICATION_JSON_VALUE)

public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> createUser(@RequestBody Order order, HttpServletRequest request)
			throws ServerException {
		if (order != null) {
			Order orderr = orderService.createOrder(order);
			return ResponseEntity.ok(orderr);
		} else {
			throw new ServerException("Error in creating the User resource. Try Again.");
		}
	}

}

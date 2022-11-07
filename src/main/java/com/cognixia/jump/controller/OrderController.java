package com.cognixia.jump.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Orders;
import com.cognixia.jump.model.Products;
import com.cognixia.jump.repository.OrderRepository;

import com.cognixia.jump.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService service;

	// get all products
	@GetMapping("/all")
	public List<Orders> getAllOrder() {
		return service.getAllOrder();

	}

	@PostMapping("/new")
	public ResponseEntity<?> createOrder(@RequestBody Orders orders) {

		return service.createOrder(orders);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOrder(@PathVariable Long id) throws ResourceNotFoundException {

		return service.getOrder(id);
	}

	@DeleteMapping("/delete/{id}")
	public boolean deleteOrder(@PathVariable Long id) {

		return service.deleteOrderById(id);
	}
	
	//update
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateOrder(@RequestBody Orders order, @PathVariable Long id) throws ResourceNotFoundException{
		return service.updateOrder(order, id);

}
}

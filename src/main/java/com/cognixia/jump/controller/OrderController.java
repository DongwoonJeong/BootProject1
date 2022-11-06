package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Orders;

import com.cognixia.jump.repository.OrderRepository;

import com.cognixia.jump.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService service;
	@Autowired
	OrderRepository repo;

	// get all products
	@GetMapping("/all")
	public List<Orders> getAllOrder() {
		return repo.findAll();

	}

	@PostMapping("/new")
	public ResponseEntity<?> createOrder(@RequestBody Orders orders) {

		orders.setOrder_id(null);

		Orders create = repo.save(orders);
		return ResponseEntity.status(201).body(create);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<?> getOrder(@PathVariable Long id) throws ResourceNotFoundException {

		Optional<Orders> found = repo.findById(id);

		if (found.isEmpty()) {
			throw new ResourceNotFoundException("Product with id = " + id + " was not found");
		}

		return ResponseEntity.status(200).body(found.get());
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean deleteOrder(@PathVariable Long id) {

		return service.deleteOrderById(id);
	}

}

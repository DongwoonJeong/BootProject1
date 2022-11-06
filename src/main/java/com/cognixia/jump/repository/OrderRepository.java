package com.cognixia.jump.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognixia.jump.model.Orders;
import com.cognixia.jump.model.Products;


public interface OrderRepository extends JpaRepository<Orders, Long>{
	
	 Orders findByUserId(int userId);
	 public Optional<Orders> findById(Long id); 
}

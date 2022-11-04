package com.cognixia.jump.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognixia.jump.model.Orders;


public interface OrderRepository extends JpaRepository<Orders, Long>{
	
	 Orders findByUserId(int userId);
	
}

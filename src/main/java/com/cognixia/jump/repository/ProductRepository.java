package com.cognixia.jump.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Products;


@Repository
public interface ProductRepository extends JpaRepository<Products, Long>{

	
	boolean existsByName(String name);
	
	
}

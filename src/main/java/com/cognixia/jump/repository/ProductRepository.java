package com.cognixia.jump.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Products;


@Repository
public interface ProductRepository extends JpaRepository<Products, Long>{

	
	boolean existsByName(String name);
	public Optional<Products> findById(Long id); 
	@Query("SELECT u FROM Products u WHERE u.price >= 2000.0")
	public List<Products> findProductPriceOver();
	
}

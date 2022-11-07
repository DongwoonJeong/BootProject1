package com.cognixia.jump.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cognixia.jump.model.Products;
import com.cognixia.jump.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findByUsername(String username);
	
	boolean existsByEmail(String email);
	public Optional<User> findById(Long id); 
	//@Query("select sum(price), orders.id from orders join store_user on orders.id = store_user.id join products on orders.product_id = products.product_id group by orders.id;")
	
}
	
	

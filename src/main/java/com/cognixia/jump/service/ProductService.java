package com.cognixia.jump.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repo;
	
	
	public boolean checkNameDuplicate(String name) {
		return repo.existsByName(name);		
	}
}

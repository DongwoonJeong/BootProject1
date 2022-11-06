package com.cognixia.jump.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Products;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repo;

	public boolean checkNameDuplicate(String name) {
		return repo.existsByName(name);
	}

	public boolean deleteProductById(Long id) {

		Optional<Products> found = repo.findById(id);

		if (!found.isEmpty()) {
			repo.delete(found.get());
			return true;
		}

		return false;

	}

	// update
	public Products get(Long id) {
		return repo.findById(id).get();

	}

	public Products save(Products product) {
		return repo.save(product);

	}
}
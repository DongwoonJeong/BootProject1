package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.DuplicateProductException;
import com.cognixia.jump.exception.ResourceNotFoundException;
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

	public List<Products> getAllproduct() {

		return repo.findAll();
	}

	public ResponseEntity<Products> getProductById(Long id) throws ResourceNotFoundException {
		Optional<Products> found = repo.findById(id);

		if (found.isEmpty()) {
			throw new ResourceNotFoundException("Product with id = " + id + " was not found");
		}

		return ResponseEntity.status(200).body(found.get());
	}

	public ResponseEntity<?> updateProduct(Products product, Long id) throws ResourceNotFoundException {
		Optional<Products> productOptional = repo.findById(id);
		if (productOptional.isPresent()) {
			Products update = get(id);
			save(product);
			return ResponseEntity.status(201).body(update);
		} else
			throw new ResourceNotFoundException("Product id does not exist.");
	}

	public ResponseEntity<?> createProduct(Products products) throws DuplicateProductException {
		if (checkNameDuplicate(products.getName()) != false) {
			throw new DuplicateProductException("you are trying to register duplicate item in the system.");
		} else {
			products.setProduct_id(null);

			Products create = repo.save(products);

			return ResponseEntity.status(201).body(create);
		}
	}
	
	public List<Products> getExpensiveProduct() {

		//return students.stream().filter(s -> s.getGpa() == gpa).collect(Collectors.toList());
		return repo.findProductPriceOver();
	}

}


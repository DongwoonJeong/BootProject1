package com.cognixia.jump.controller;

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

import com.cognixia.jump.exception.DuplicateProductException;
import com.cognixia.jump.exception.DuplicateUserException;
import com.cognixia.jump.exception.ResourceNotFoundException;

import com.cognixia.jump.model.Products;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.ProductRepository;
import com.cognixia.jump.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService service;
	@Autowired
	ProductRepository repo;
	
	
	//get all products
	@GetMapping("/all")
	public List<Products> getAllproducts() {
		return repo.findAll();
		
	}
	@PostMapping("/new")
	public ResponseEntity<?> createProduct(@RequestBody Products products) throws DuplicateProductException{
		if(service.checkNameDuplicate(products.getName()) != false) {
		throw new DuplicateProductException("you are trying to register duplicate item in the system.");
		}else {
			products.setProduct_id(null);
		
		Products create = repo.save(products);
		
		return ResponseEntity.status(201).body(create);
		}
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id) throws ResourceNotFoundException {
		
		Optional<Products> found = repo.findById(id);
		
		if(found.isEmpty()) {
			throw new ResourceNotFoundException("Product with id = " + id + " was not found");
		}
		
		return ResponseEntity.status(200).body(found.get());
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean deleteProduct(@PathVariable Long id) {

		return service.deleteProductById(id);
	}
	//update
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProduct(@RequestBody Products product, @PathVariable Long id) throws ResourceNotFoundException{
		 Optional<Products> productOptional = repo.findById(id);
			if(productOptional.isPresent()) {
		Products update = service.get(id);
	        service.save(product);
	        return ResponseEntity.status(201).body(update);
	    }else
	    	throw new ResourceNotFoundException("Product id does not exist.");
	}
	
}

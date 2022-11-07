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
		return service.getAllproduct();
		
	}
	@PostMapping("/new")
	public ResponseEntity<?> createProduct(@RequestBody Products products) throws DuplicateProductException {
		return service.createProduct(products);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id) throws ResourceNotFoundException{
		
		return service.getProductById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean deleteProduct(@PathVariable Long id) {

		return service.deleteProductById(id);
	}
	//update
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProduct(@RequestBody Products product, @PathVariable Long id)
			throws ResourceNotFoundException {
		return service.updateProduct(product, id);
	}
	
	@GetMapping("/product/expensive")
	public List<Products> getExpensiveProduct(){
		return service.getExpensiveProduct();
	}
}

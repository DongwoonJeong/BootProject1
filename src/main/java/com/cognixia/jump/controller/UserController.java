package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.DuplicateUserException;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository repo;
	
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	UserService service;
	
	
	//read
	//Get all the user exist in the database.
	@GetMapping("/all")
	public List<User> getAllUsers() {
		
		return service.getAllUsers();
	}
	
	//create
	//Create new user
	@PostMapping("/new")
	public ResponseEntity<?> createUser(@RequestBody User user) throws DuplicateUserException{
		if(service.checkEmailDuplicate(user.getEmail()) != false) {
		throw new DuplicateUserException("email already exist in the system!");
		}else {
		user.setId(null);
		//each password for a new user gets encoded
		user.setPassword(encoder.encode(user.getPassword()));
		
		User create = repo.save(user);
		
		return ResponseEntity.status(201).body(create);
		}
	}
	//delete
	//delete user by id
	@DeleteMapping("/delete/{id}")
	public boolean deleteUsers(@PathVariable Long id) {
		return service.deleteUserById(id);
	}
	
	//update
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id) throws ResourceNotFoundException{
	    Optional<User> userOptional = repo.findById(id);
		if(userOptional.isPresent()) {
		User update = service.get(id);
	        service.save(user);
	        return ResponseEntity.status(201).body(update);
	    }else
	    	throw new ResourceNotFoundException("User id does not exist.");
	}
	}
	
	


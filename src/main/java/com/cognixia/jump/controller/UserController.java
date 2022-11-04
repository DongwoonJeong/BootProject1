package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.DuplicateUserException;
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
	
	//Get all the user exist in the database.
	@GetMapping("/get")
	public List<User> getUsers() {
		return repo.findAll();
	}
	
	
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
	
	
}

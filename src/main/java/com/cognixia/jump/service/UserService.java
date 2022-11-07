package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.DuplicateUserException;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Products;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repo;
	@Autowired
	PasswordEncoder encoder;

	// check email address if its registered or not
	public boolean checkEmailDuplicate(String email) {
		return repo.existsByEmail(email);
	}

	// get list of the user
	public List<User> getAllUsers() {

		return repo.findAll();
	}

	public boolean deleteUserById(Long id) {
		Optional<User> found = repo.findById(id);

		if (!found.isEmpty()) {
			repo.delete(found.get());
			return true;
		}

		return false;
	}

	// update
	public User get(Long id) {
		return repo.findById(id).get();

	}

	public User save(User user) {
		return repo.save(user);
		
	}

	public ResponseEntity<?> createUser(User user) throws DuplicateUserException{
		if(checkEmailDuplicate(user.getEmail()) != false) {
			throw new DuplicateUserException("email already exist in the system!");
			}else {
			user.setId(null);
			//each password for a new user gets encoded
			user.setPassword(encoder.encode(user.getPassword()));
			
			User create = repo.save(user);
			
			return ResponseEntity.status(201).body(create);
			}

	}
	public ResponseEntity<?> updateUser(User user, Long id)throws ResourceNotFoundException{
	    Optional<User> userOptional = repo.findById(id);
		if(userOptional.isPresent()) {
		User update = get(id);
	        save(user);
	        return ResponseEntity.status(201).body(update);
	    }else
	    	throw new ResourceNotFoundException("User id does not exist.");
	}
	}
	
	


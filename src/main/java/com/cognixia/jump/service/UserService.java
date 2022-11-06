package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Products;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repo;

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

	
}

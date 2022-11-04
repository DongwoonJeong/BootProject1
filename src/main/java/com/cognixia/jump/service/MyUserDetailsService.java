package com.cognixia.jump.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	
	@Autowired
	UserRepository repo;
		/*
		 * method is going to be called by spring security when a new http request comes in
		 * 1. extract the username passed in the request
		 * 2. call the method below and find the user associated with request
		 * 3.check if the password in the db matches the password in the request
		 * 4.then check if enabled, their role allows them to access this endpoint, if credentials expired, etc.
		 * 5. as long as all of that passes, the request can be fulfilled.
		 */ 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user =repo.findByUsername(username);	
		//Exception only if user name is not found.
		if(user.isEmpty()) {
			throw new UsernameNotFoundException("User with username " + username + " not found");
		}
		//as long as user is found, we return the object as a userdetails one,
		//userdatils will have only the necessary info for spring security.
		return new MyUserDetails(user.get());
	}

}

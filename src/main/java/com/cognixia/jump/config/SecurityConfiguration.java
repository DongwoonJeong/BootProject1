package com.cognixia.jump.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cognixia.jump.filter.JwtRequestFilter;

@Configuration
public class SecurityConfiguration{

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"

    };
	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	// Authentication
	@Bean
	protected UserDetailsService userDetailsService() {
		return userDetailsService;

	}

	// Authorization
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST,"/user/new").permitAll()
		.antMatchers(AUTH_WHITELIST).permitAll()
		.antMatchers(HttpMethod.POST,"/products/new").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/user/delete/**").hasRole("ADMIN")
		.antMatchers("/authenticate").permitAll() // anyone can create a JWT without needing to have a JWT first.
		//.anyRequest().authenticated() // need some login in order to access any of the APIs.
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// tell spring
		
		// security to NOT
																 									// create
																									// sessions.dont
																									// remember
		//request will go through many filers, but typically the first filter checks the one for username n pw.
		//but, we will set it up, that our JWT filter gets checked first, or else the authentication will fail, since spring 
		//security wont know where to find the username n pw.
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();

	}

	// Encoder -> method help with encoding/decoding all the user password
	// manage password encoding
	@Bean
	protected PasswordEncoder encoder() {

		// plain text encoder ->
		//return NoOpPasswordEncoder.getInstance();

		// encrypt the password with the bcrypt algorithm
		 return new BCryptPasswordEncoder();
	}
	// load in the encoder & user details service that are needed for security to do
	// authentication and authorization

	@Bean
	protected DaoAuthenticationProvider authenticationprovider() {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());

		return authProvider;

	}

	// can autowire and access the authentication manager (manages authentication
	// (login) of our project)
	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

}
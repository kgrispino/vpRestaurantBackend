package com.javainuse.springbootsecurity;

import com.javainuse.springbootsecurity.config.CustomUserDetailsService;
import com.javainuse.springbootsecurity.model.AuthenticationRequest;
import com.javainuse.springbootsecurity.model.AuthenticationResponse;
import com.javainuse.springbootsecurity.model.Product;
import com.javainuse.springbootsecurity.model.UserDTO;
import com.javainuse.springbootsecurity.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import com.javainuse.springbootsecurity.config.JwtUtil;

@SpringBootApplication
public class SpringBootSecurityApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(SpringBootSecurityApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private ProductRepository productRepo;

	private AuthenticationRequest authRequest;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public void run(String... args) throws Exception {

		//Testing account creation
		UserDTO user1 = UserDTO.builder()
				.password("1234")
				.username("Lmao")
				.role("Admin")
				.build();
		userDetailsService.save(user1);

		Product product1 = Product.builder()
				.name("Fries")
				.calories(100)
				.desc("Its good")
				.price(2.50).build();

		Product product2 = Product.builder()
				.name("Burger")
				.calories(500)
				.desc("Its just ok")
				.price(5.50).build();

		Product product3 = Product.builder()
				.name("Soda")
				.calories(250)
				.desc("Its soda")
				.price(1).build();

		productRepo.save(product1);
		productRepo.save(product2);
		productRepo.save(product3);
	}
}

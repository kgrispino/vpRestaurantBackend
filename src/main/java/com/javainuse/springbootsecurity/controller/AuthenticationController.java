package com.javainuse.springbootsecurity.controller;

import com.javainuse.springbootsecurity.model.*;
import com.javainuse.springbootsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javainuse.springbootsecurity.config.CustomUserDetailsService;
import com.javainuse.springbootsecurity.config.JwtUtil;
import com.javainuse.springbootsecurity.model.DAOUser;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController

@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthenticationController {
	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	/*
	Create JWT token based on username and password
	@params payload with username and password
	 */
	@PostMapping("/accounts/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		}
		catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtUtil.generateToken(userdetails);
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}

	//Was register
	//Make it so everyone is a user
	@PostMapping("accounts/create")
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		user.setRole("USER");
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	//Check if account name is available
	//@params user payload
	@PostMapping("/accounts/avail")
	public boolean checkAvailability(@RequestBody UserDTO user){
		return userDetailsService.AccountExists(user.getUsername());
	}
	/*
    Get list of accounts
    */
	@GetMapping("/accounts")
	public List<DAOUser> getAccounts(){
		return userDetailsService.getAccounts();
	}


	//Validate JWT token
	//@params request: username and token payload
	@PostMapping("/accounts/validate")
	public boolean validateAccount(@RequestBody ValidationRequest request){
		if (jwtUtil.validateToken(request.getToken())){
			return Objects.equals(jwtUtil.getUsernameFromToken(request.getToken()), request.getUsername());
		}
		return false;
	}
}

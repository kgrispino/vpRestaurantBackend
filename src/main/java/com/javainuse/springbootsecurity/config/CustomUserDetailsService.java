package com.javainuse.springbootsecurity.config;

import java.util.Arrays;
import java.util.List;

import com.javainuse.springbootsecurity.controller.AuthenticationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javainuse.springbootsecurity.model.DAOUser;
import com.javainuse.springbootsecurity.model.UserDTO;
import com.javainuse.springbootsecurity.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
//This is where I make custom functions to do specific things like save or get all accounts
	private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private UserRepository DaoRepo;
	//Repos are only for simple JPA commands like finalBy...

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = null;

		DAOUser user = DaoRepo.findByUsername(username);
		if (user != null) {
			roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
			return new User(user.getUsername(), user.getPassword(), roles);
		}
		throw new UsernameNotFoundException("User not found with the name " + username);	}
	
	public DAOUser save(UserDTO user) {
		DAOUser newUser = new DAOUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
		return DaoRepo.save(newUser);
	}

	public List<DAOUser> getAccounts(){
		return DaoRepo.findAll();
	}

	public boolean AccountExists(String username){
		DAOUser user = DaoRepo.findByUsername(username);
		return user != null;
	}

}

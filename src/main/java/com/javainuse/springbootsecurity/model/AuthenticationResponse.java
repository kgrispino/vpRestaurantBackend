package com.javainuse.springbootsecurity.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/*
 * Class for receiving token in payload
 */
public class AuthenticationResponse {
	private String token;

}

package com.javainuse.springbootsecurity.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
/*
 * Class for receiving username and password in payload
 */
public class AuthenticationRequest {
	private String username;
	private String password;

}

package com.javainuse.springbootsecurity.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
/*
 * Class for receiving token and token in payload
 */
public class ValidationRequest {
    private String token;
    private String username;

}

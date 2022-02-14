package com.javainuse.springbootsecurity.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ValidationRequest {
    private String token;
    private String username;

}

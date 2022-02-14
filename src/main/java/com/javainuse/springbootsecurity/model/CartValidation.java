package com.javainuse.springbootsecurity.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

/*
 * Class for receiving cart and token in payload
 */
public class CartValidation {
    private String token;
    private String cart;
}

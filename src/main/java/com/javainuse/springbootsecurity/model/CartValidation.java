package com.javainuse.springbootsecurity.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartValidation {
    private String token;
    private String cart;
}

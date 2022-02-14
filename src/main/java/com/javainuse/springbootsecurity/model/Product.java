package com.javainuse.springbootsecurity.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "products")
public class Product {
    @Id
    private String name;
    private double price;
    private String desc;
    private int calories;
}

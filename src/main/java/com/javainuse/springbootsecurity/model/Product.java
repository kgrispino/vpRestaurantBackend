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

/*
	The attributes for product table:
	Primary key: name
	price
	desc
	calories
 */

//Post note would be better to make an id number the primary key and make instead of name
//in case of product name change
@Entity
@Table(name = "products")
public class Product {
    @Id
    private String name;
    private double price;
    private String desc;
    private int calories;
}

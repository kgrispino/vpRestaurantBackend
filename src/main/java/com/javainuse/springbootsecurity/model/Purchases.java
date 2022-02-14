package com.javainuse.springbootsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

/*
	The attributes for Purchases table:
	Primary key: order_id
	Username
	cart(string of all items in cart
	date(timestamp)


Post note: A better approach would be to make another table for contents of cart
    Table: cart
    Primary key: order_id
    Product_id
    Quantity
    Product cost
And this cart table could be joined with this purchase table to get date and user who ordered
*/
@Entity
@Table(name = "purchases")
public class Purchases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    private String username;
    private String cart;
    private Date date;


}

package com.javainuse.springbootsecurity.controller;

import com.javainuse.springbootsecurity.model.Product;
import com.javainuse.springbootsecurity.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * Class for exposing Product API
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    /*
    Get list of products
    */
    @GetMapping("/products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

}

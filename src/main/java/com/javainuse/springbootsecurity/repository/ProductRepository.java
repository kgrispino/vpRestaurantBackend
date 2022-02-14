package com.javainuse.springbootsecurity.repository;

import com.javainuse.springbootsecurity.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repo for H2 product table
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, String>  {
}

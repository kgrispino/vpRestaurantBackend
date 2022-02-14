package com.javainuse.springbootsecurity.repository;


import com.javainuse.springbootsecurity.model.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repo for H2 Purchases table
 */
@Repository
public interface PurchasesRepository extends JpaRepository<Purchases, Long> {
}

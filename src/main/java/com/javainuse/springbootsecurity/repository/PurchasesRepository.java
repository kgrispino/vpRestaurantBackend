package com.javainuse.springbootsecurity.repository;


import com.javainuse.springbootsecurity.model.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasesRepository extends JpaRepository<Purchases, Long> {
}

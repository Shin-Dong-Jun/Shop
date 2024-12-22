package com.example.bravobra.repository;

import com.example.bravobra.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Object productName(String productName);
}
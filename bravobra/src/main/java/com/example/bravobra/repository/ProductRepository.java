package com.example.bravobra.repository;

import com.example.bravobra.dto.ProductDto;
import com.example.bravobra.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

   List<Product> findByProductNameContaining(String name);

   List<Product> findByCategoryMediumCode(ProductDto productDto);

}
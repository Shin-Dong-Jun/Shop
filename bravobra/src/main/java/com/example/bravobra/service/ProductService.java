package com.example.bravobra.service;

import com.example.bravobra.dto.ProductDto;
import com.example.bravobra.entity.Product;
import com.example.bravobra.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ProductService {

    private final ProductRepository productRepository;

    //전체 상품을 가져오는 메서드
    public List<Product> findAll() {
        return  productRepository.findAll();
    }

    // 상품을 읽어오는 메서드 // Read
    public Product read(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("Product not found"));
    }

    // 상품 등록 메서드 // Create
    public void register(Product product) {
        log.info(" 인얼ㄹㅇ {}" , product);
        productRepository.save(product);
    }

    //  상품을 수정하는 메서드 // Update
    public void modify(ProductDto newProduct) {
        Product findProduct = productRepository.findById(newProduct.productId()).orElseThrow(() -> new NoSuchElementException("Product not found"));
        findProduct.updateProducts(newProduct);
//        findProduct.setProductName(newProduct.productName());
//        findProduct.setProductContent(newProduct.productContent());
//        findProduct.setHashTag(newProduct.hashTag());
        productRepository.save(findProduct);
    }

    // 상품을 삭제하는 메서드 // Delete
    public void delete(Long productId) {
        //        productRepository.deleteById(productId);
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null)
            productRepository.deleteById(productId);
    }
}
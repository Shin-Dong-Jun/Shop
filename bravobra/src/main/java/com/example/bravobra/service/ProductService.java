package com.example.bravobra.service;

import com.example.bravobra.dto.ProductDto;
import com.example.bravobra.entity.Product;
import com.example.bravobra.repository.OptionRepository;
import com.example.bravobra.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ProductService {

    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;

    // 이미지 수정용 상품아이디 가져오는 메서드

    //전체 상품을 가져오는 메서드
    public List<Product> findAll() {
        return  productRepository.findAll();
    }

    // 카테고리별로 상품을 가져오는 메서드
    public List<Product> findByCategory(Integer categoryMediumCode){
        ProductDto productDto = ProductDto.builder()
                .categoryMediumCode(categoryMediumCode).build();
        return productRepository.findByCategoryMediumCode(productDto);
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    // 상품을 읽어오는 메서드 // Read
    public Product read(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("Product not found"));
    }

    // 상품 등록 메서드 // Create
    public void register(ProductDto productDto, String thumbnailPath) {
//        Product product = productRepository.findById(productDto.getProductId()).orElseThrow(() -> new IllegalStateException("Product not found"));

        // DTO를 엔티티로 변환
        Product product = Product.builder()
                .productId(productDto.getProductId())
                .productName(productDto.getProductName())
                .productEnglishName(productDto.getProductEnglishName())
                .productContent(productDto.getProductContent())
                .fixedPrice(productDto.getFixedPrice())
                .salePrice(productDto.getSalePrice())
                .hashTag(productDto.getHashTag())
                .isSoldOut(productDto.getIsSoldOut())
                .discountRate(productDto.getDiscountRate())
                .thumbnail(thumbnailPath) // 파일 경로만 엔티티에 설정
                .build();
        // 로그 출력
        log.info("등록! {}", product);
        productRepository.save(product);
    }

    //  상품을 수정하는 메서드 // Update
    public void modify(ProductDto newProduct) {
        Product findProduct = productRepository.findById(newProduct.getProductId()).orElseThrow(() -> new NoSuchElementException("Product not found"));
        findProduct.updateProducts(newProduct);
        productRepository.save(findProduct);
    }

    // 상품을 삭제하는 메서드 // Delete
    public void delete(List<Long> productId) {
        // 여러 개의 상품을 삭제할 경우
        if (productId.size() == 1) {
            // 하나의 상품을 삭제
            productRepository.deleteById(productId.get(0));
        } else {
            // 여러 개의 상품을 삭제
            List<Product> products = productRepository.findAllById(productId);
            productRepository.deleteAll(products);
        }
    }

    // 삭제할 상품을 읽어오는 메서드
    public List<Product> readAllById(List<Long> id) {
        return productRepository.findAllById(id);
    }

    // 페이징 처리 메서드
    public Page<Product> getPage(Integer page, Integer pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("productId").descending());
        return productRepository.findAll(pageable);
    }

    // 상품 검색 메서드: 정확히 일치하는 이름으로 검색
    public List<Product> findByName(String name) {
        List<Product> products = productRepository.findByProductNameContaining(name);
        if(products.isEmpty()){
            throw new NoSuchElementException("Product not found");
        }
        return products;
    }

}

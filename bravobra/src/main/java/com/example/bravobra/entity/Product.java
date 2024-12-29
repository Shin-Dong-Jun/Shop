package com.example.bravobra.entity;

import com.example.bravobra.domain.OptionId;
import com.example.bravobra.domain.product.Option;
import com.example.bravobra.dto.ProductDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "Product")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor  // 기본 생성자
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가 설정
    private long productId;  // 기본 키
    private int categoryBottomCode; // 소
    private int categoryMediumCode; // 중
    private int categoryTopCode; // 대
    private String productName;
    private String productEnglishName;
    private String productContent;
    private String thumbnail;
    private int fixedPrice;  // 정가
    private int salePrice;   // 판매가
    private String hashTag;
    private Boolean isSoldOut;   // 품절 여부
    private int discountRate;    // 할인율
    private LocalDateTime createdAt;  // 생성일자
    private LocalDateTime updatedAt;  // 수정일자

    public Product updateProducts(ProductDto newProduct) {
        this.productName = newProduct.getProductName();
        this.productEnglishName = newProduct.getProductEnglishName();
        this.productContent = newProduct.getProductContent();
        this.updatedAt = LocalDateTime.now();
        this.hashTag = newProduct.getHashTag();
        return this;
    }
}

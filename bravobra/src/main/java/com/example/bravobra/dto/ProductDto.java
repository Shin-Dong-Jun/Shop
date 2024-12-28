package com.example.bravobra.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDto {
    private Long productId; // 상품번호
    private Integer categoryTopCode;
    private Integer categoryMediumCode;
    private Integer categoryBottomCode;
    private String productName;
    private String productEnglishName; // 상품영어이름
    private String productContent; // 상품상세설명
    private Integer fixedPrice; // 상품정가
    private Integer salePrice; // 상품판매가
    private String hashTag; // 상품해시태그
    private Boolean isSoldOut; // 품절여부
    private Integer discountRate; // 할인율

    public ProductDtoBuilder toBuilder() {
        return new ProductDtoBuilder()
                .productId(this.productId)
                .categoryTopCode(this.categoryTopCode)
                .categoryMediumCode(this.categoryMediumCode)
                .categoryBottomCode(this.categoryBottomCode)
                .productName(this.productName)
                .productEnglishName(this.productEnglishName)
                .productContent(this.productContent)
                .fixedPrice(this.fixedPrice)
                .salePrice(this.salePrice)
                .hashTag(this.hashTag)
                .isSoldOut(this.isSoldOut)
                .discountRate(this.discountRate);
    }
}

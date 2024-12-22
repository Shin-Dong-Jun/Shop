package com.example.bravobra.dto;

import lombok.Builder;

@Builder
public record ProductDto(Long productId // 상품번호
        , String productName
        , String productEnglishName // 상품영어이름
        , String productContent // 상품상세설명
        , String thumbnail // 상품 썸네일
        , String image1 // 이미지1
        , String image2 // 이미지2
        , String image3 // 이미지3
        , String image4 // 이미지4
        , Integer fixedPrice // 상품정가
        , Integer salePrice // 상품판매가
        , String hashTag // 상품해시태그
        , Boolean isSoldOut // 품절여부
        , Integer discountRate // 할인율)
) {
}


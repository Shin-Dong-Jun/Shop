package com.example.bravobra.dto;
public record ItemCartDtoResponse(String color,
                                  Long cartId,
                                  Long optionId,
                                  Long itemId,
                                  String image,
                                  int qnt,
                                  String productName,
                                  int salePrice,
                                  String optionValues
,int fixedPrice
,int discountRate) {

}

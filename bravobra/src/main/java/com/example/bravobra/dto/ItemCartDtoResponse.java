package com.example.bravobra.dto;

public record ItemCartDtoResponse(String color,
                                  Long cartId,
                                  Long optionId,
                                  Long itemId,
                                  int qnt,
                                  String productName,
                                  int fixedPrice,
                                  String optionValues) {

}

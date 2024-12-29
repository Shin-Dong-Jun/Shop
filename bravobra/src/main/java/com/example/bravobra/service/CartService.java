package com.example.bravobra.service;

import com.example.bravobra.domain.cart.Cart;
import com.example.bravobra.domain.product.Option;
import com.example.bravobra.dto.ItemCartDtoRequest;
import com.example.bravobra.dto.ItemCartDtoResponse;
import com.example.bravobra.repository.CartRepository;
import com.example.bravobra.repository.OptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final OptionRepository optionRepository;

    public void addCart(ItemCartDtoRequest itemCartDtoRequests, Long userId) {

        Option option = optionRepository.findByOptionId(itemCartDtoRequests.color(), itemCartDtoRequests.size(), itemCartDtoRequests.itemId())
                .orElseThrow(() -> new IllegalStateException("옵션이 없습니다."));

        cartRepository.findByOptionIdAndUserId(option, userId).ifPresentOrElse(cart -> {
                    cart.increaseQnt(option);
                },
                () -> {
                    Cart cart = Cart.builder()
                            .optionId(option)
                            .optionValues(itemCartDtoRequests.optionValues())
                            .userId(userId)
                            .qnt(itemCartDtoRequests.qnt())
                            .build();
                    cartRepository.save(cart);
                });
    }

    public void updateCart(int qnt, Long cartId, Long userId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalStateException("장바구니가 없습니다."));
        cart.updateQnt(qnt);
    }

    public List<ItemCartDtoResponse> getCartList(Long userId) {
        var itemCartDtoResponses = cartRepository.findbyCartListDto(userId);

        return itemCartDtoResponses;
    }

    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public void deleteListCart(List<Long> cartIds) {
        cartRepository.deleteAllById(cartIds);
    }
}

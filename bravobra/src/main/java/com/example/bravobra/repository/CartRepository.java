package com.example.bravobra.repository;

import com.example.bravobra.domain.OptionId;
import com.example.bravobra.domain.cart.Cart;
import com.example.bravobra.domain.product.Option;
import com.example.bravobra.dto.ItemCartDtoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByOptionIdAndUserId(Option optionId, long l);

    @Query("select new com.example.bravobra.dto.ItemCartDtoResponse(o.color, c.id) from Cart c join  c.optionId o")
    List<ItemCartDtoResponse> findbyCartListDto();
}

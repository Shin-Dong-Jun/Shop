package com.example.bravobra.repository;

import com.example.bravobra.domain.cart.Cart;
import com.example.bravobra.domain.product.Option;
import com.example.bravobra.dto.ItemCartDtoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByOptionIdAndUserId(Option optionId, long l);

    @Query("select new com.example.bravobra.dto.ItemCartDtoResponse(o.color, c.id, o.optionId.optionId, o.optionId.productId, p.thumbnail ,c.qnt, p.productName, p.salePrice, c.optionValues) from Cart c join  c.optionId o join o.product p where c.userId = :userId")
    List<ItemCartDtoResponse> findbyCartListDto(@Param("userId") Long userId);

    @Query("select c from Cart c join fetch c.optionId o  where c.id = :cartId")
    Optional<Cart> findByCartIdWithOptionAndProduct(@Param("cartId") Long cartId);

}

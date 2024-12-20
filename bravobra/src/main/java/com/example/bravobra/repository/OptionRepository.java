package com.example.bravobra.repository;

import com.example.bravobra.domain.OptionId;
import com.example.bravobra.domain.product.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OptionRepository extends JpaRepository<Option, OptionId> {


    @Query("select o from Option o where o.color = :color and o.size = :size and o.optionId.ProductId = :productId")
    Optional<Option> findByOptionId(@Param("color") String color, @Param("size") String size, @Param("productId")Long productId);
}

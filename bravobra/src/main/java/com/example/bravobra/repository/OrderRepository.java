package com.example.bravobra.repository;

import com.example.bravobra.domain.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query( "SELECT o FROM Order o JOIN FETCH o.orderProduct op  JOIN FETCH op.option opt JOIN FETCH opt.product p WHERE o.userId = :userId")
    Page<Order> findByUserIdWithOrderProduct(@Param("userId") Long userId, Pageable pageable);
}

package com.example.bravobra.repository;

import com.example.bravobra.domain.order.Order;
import com.example.bravobra.domain.order.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    OrderProduct findByOrder(Order order);
}

package com.example.bravobra.domain.order;

import com.example.bravobra.domain.product.Option;
import com.example.bravobra.domain.state.OrderState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "option_id", referencedColumnName = "optionId"),
            @JoinColumn(name = "product_id", referencedColumnName = "p_id")
    })
    private Option option;

    private int qtn;
    private int totalOrderPrice;
    private int totalDiscountPrice;
    @Enumerated
    @Column(name = "op_status")
    private OrderState orderState;
}

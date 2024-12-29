package com.example.bravobra.domain.order;

import com.example.bravobra.domain.product.Option;
import com.example.bravobra.domain.state.OrderState;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;


    @ManyToOne(fetch = FetchType.LAZY)
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

    public void addOrder(Order order) {
        this.order = order;
    }
}

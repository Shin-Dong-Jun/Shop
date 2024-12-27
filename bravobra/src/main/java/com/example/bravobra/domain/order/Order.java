package com.example.bravobra.domain.order;

import com.example.bravobra.domain.state.OrderState;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProduct = new ArrayList<>();

    @Column(name = "order_number", length = 40)
    private String orderNo;

    @Column(name = "total_p_price")
    private int totalProductPrice;
    @Column(name = "total_discount_price")
    private int totalDiscountPrice;
    @Column(name = "total_order_price")
    private int totalOrderPrice;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @CreationTimestamp
    @Column(name = "order_datetime", columnDefinition = "timestamp")
    private LocalDateTime orderDatetime;

    private int qnt;

    public void addOrderProduct(OrderProduct orderProduct){
        this.orderProduct.add(orderProduct);
        orderProduct.addOrder(this);
    }
}

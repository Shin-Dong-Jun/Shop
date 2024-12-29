package com.example.bravobra.domain.cart;

import com.example.bravobra.domain.product.Option;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@AllArgsConstructor
public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;
    // 유저 fk
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "option_id", referencedColumnName = "optionId", unique = false),
            @JoinColumn(name = "product_id", referencedColumnName ="p_id" ,unique = false)
    })
    private Option optionId;

    private int qnt;
    @Column(name = "p_option")
    private String optionValues;

    public void increaseQnt(Option option) {
        qnt =+ option.getQnt();
    }

    public void updateQnt(int qnt) {
        this.qnt = qnt;
    }
}
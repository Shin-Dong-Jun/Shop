package com.example.bravobra.domain.product;

import com.example.bravobra.domain.OptionId;
import com.example.bravobra.domain.state.Category;
import com.example.bravobra.entity.Product;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@AllArgsConstructor
@Table(name = "options")
public class Option {

    @EmbeddedId
    private OptionId optionId;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_id")
    private Product product;

    private String color;
    private String size;
// 브라, 팬티
    @Enumerated(EnumType.STRING)
    private Category type;

    private int qnt;

    @Builder.Default
    @ColumnDefault("'N'")
    @Column(name = "is_soldout", columnDefinition = "varchar(1)")
    private String soldout = "N";

//    @PrePersist
//    public void prePersist() {
//        if (soldout == null) {
//            soldout = "N";
//        }
//
//    }
}
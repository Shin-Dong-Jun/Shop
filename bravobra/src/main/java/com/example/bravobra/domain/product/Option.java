package com.example.bravobra.domain.product;

import com.example.bravobra.domain.OptionId;
import com.example.bravobra.domain.state.Category;
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

    private String color;
    private String size;
// 브라, 팬티
    @Enumerated(EnumType.STRING)
    private Category type;

    private int qnt;

    @ColumnDefault("'N'")
    @Column(name = "is_soldout", columnDefinition = "varchar(1)")
    private String soldout;

//    @PrePersist
//    public void prePersist() {
//        if (soldout == null) {
//            soldout = "N";
//        }
//
//    }
}
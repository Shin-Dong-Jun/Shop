package com.example.bravobra.domain.product;

import com.example.bravobra.domain.OptionId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Option {

    @EmbeddedId
    private OptionId optionId;
    private String color;
    private String size;

    private String type;

    private int qnt;
    @Column(name = "is_soldout", columnDefinition = "varchar(1) default 'N'")
    private String soldout;
}
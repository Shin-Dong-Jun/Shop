package com.example.bravobra.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;


@Embeddable
@Data
public class OptionId implements Serializable {
    private Long optionId;

    @Column(name = "p_id")
    private Long productId;
    

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OptionId optionId1 = (OptionId) o;
        return Objects.equals(optionId, optionId1.optionId) && Objects.equals(productId, optionId1.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionId, productId);
    }
}
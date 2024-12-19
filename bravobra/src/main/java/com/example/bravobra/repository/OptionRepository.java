package com.example.bravobra.repository;

import com.example.bravobra.domain.OptionId;
import com.example.bravobra.domain.product.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, OptionId> {


}

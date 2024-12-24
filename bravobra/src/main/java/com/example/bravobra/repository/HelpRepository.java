package com.example.bravobra.repository;

import com.example.bravobra.domain.help.Help;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HelpRepository extends JpaRepository<Help, Long> {
   Help findByHelpId(Long helpId);

   List<Help> findAll();
}

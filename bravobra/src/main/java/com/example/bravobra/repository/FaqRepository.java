package com.example.bravobra.repository;

import com.example.bravobra.entity.Faq;
import com.example.bravobra.entity.Notice;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FaqRepository extends JpaRepository<Faq, Long> {

    //@Query(value = "SELECT * FROM faq WHERE title LIKE %?1%", nativeQuery = true)
    //쿼리메서드
    List<Faq> findByTitleContaining(String title);

    //@Query("SELECT * FROM faq order by DESE")
    List<Faq> findAll(Sort sort);


    Page<Faq> findAll(Pageable pageable);
}

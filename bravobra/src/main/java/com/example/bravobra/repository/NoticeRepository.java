package com.example.bravobra.repository;

import com.example.bravobra.entity.Faq;
import com.example.bravobra.entity.Notice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {



    List<Notice> findByTitleContaining(String title);


//    //@Query("SELECT * FROM notice order by isPin DESE, noticeId DESE")
//    List<Notice> findByIsPin();

    //@Query("SELECT * FROM faq order by DESE")
    List<Notice> findAll(Sort sort);


    @Query("SELECT n FROM Notice n ORDER BY n.fix desc, n.wDate desc")
    List<Notice> findUrgent();




}

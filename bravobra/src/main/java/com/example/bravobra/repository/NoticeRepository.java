package com.example.bravobra.repository;

import com.example.bravobra.entity.Faq;
import com.example.bravobra.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByTitleContaining(String title);


//    //@Query("SELECT * FROM notice order by isPin DESE, noticeId DESE")
//    List<Notice> findByIsPin();
}

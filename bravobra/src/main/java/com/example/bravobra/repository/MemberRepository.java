package com.example.bravobra.repository;


import com.example.bravobra.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email); //쿼리 메서드

    List<Member> findAllByEmail(String email); // 조회할때 필요.

    Optional<Member> findByPhoneNumberAndName(String phoneNumber, String name);

    boolean existsByPhoneNumber(String phoneNumber); // 휴대폰 번호 중복 체크

    boolean existsByNickName(String nickName);

}

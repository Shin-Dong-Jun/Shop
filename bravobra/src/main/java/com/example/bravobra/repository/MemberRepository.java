package com.example.bravobra.repository;


import com.example.bravobra.domain.Member;
import com.example.bravobra.domain.MemberType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    boolean existsByMemberType(MemberType memberType);

    Optional<Member> findByEmailAndNameAndPhoneNumber(String email, String name, String phoneNumber);

    @Modifying
    @Query("UPDATE Member m SET m.password = :password WHERE m.email = :email")
    void updatePassword(@Param("email") String email, @Param("password") String password);


}

package com.example.bravobra.repository;

import com.example.bravobra.domain.help.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}

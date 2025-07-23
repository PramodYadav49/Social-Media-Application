package com.pramod.social.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pramod.social.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}

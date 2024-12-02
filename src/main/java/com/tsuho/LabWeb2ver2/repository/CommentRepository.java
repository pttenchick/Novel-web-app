package com.tsuho.LabWeb2ver2.repository;

import com.tsuho.LabWeb2ver2.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByChapterId(Integer chapterId);
    List<Comment> findByUserId(Integer userId);
}
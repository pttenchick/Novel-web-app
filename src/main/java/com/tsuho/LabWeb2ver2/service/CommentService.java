package com.tsuho.LabWeb2ver2.service;

import com.tsuho.LabWeb2ver2.model.Comment;
import com.tsuho.LabWeb2ver2.service.DTO.CommentDto;
import com.tsuho.LabWeb2ver2.views.ViewModelComment;

import java.util.List;

import java.util.List;

public interface CommentService {
    ViewModelComment addComment(CommentDto commentDto);
    ViewModelComment updateComment(Integer commentId, CommentDto commentDto);
    List<ViewModelComment> findByChapterId(Integer chapterId);
    List<ViewModelComment> findByUserId(Integer userId);
}
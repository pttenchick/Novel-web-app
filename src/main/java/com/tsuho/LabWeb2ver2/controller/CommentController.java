package com.tsuho.LabWeb2ver2.controller;

import com.tsuho.LabWeb2ver2.service.CommentService;
import com.tsuho.LabWeb2ver2.service.DTO.CommentDto;
import com.tsuho.LabWeb2ver2.views.ViewModelComment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/novels/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<ViewModelComment> addComment(@RequestBody CommentDto commentDto) {
        ViewModelComment viewModelComment = commentService.addComment(commentDto);
        return new ResponseEntity<>(viewModelComment, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{commentId}")
    public ResponseEntity<ViewModelComment> updateComment(@PathVariable Integer commentId, @RequestBody CommentDto commentDto) {
        ViewModelComment viewModelComment = commentService.updateComment(commentId, commentDto);
        return new ResponseEntity<>(viewModelComment, HttpStatus.OK);
    }

    @GetMapping("/{chapterId}")
    public ResponseEntity<List<ViewModelComment>> getCommentsByChapterId(@PathVariable Integer chapterId) {
        List<ViewModelComment> comments = commentService.findByChapterId(chapterId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
package com.tsuho.LabWeb2ver2.service.impl;

import com.tsuho.LabWeb2ver2.model.Chapter;
import com.tsuho.LabWeb2ver2.model.Comment;
import com.tsuho.LabWeb2ver2.model.User;
import com.tsuho.LabWeb2ver2.repository.ChapterRepository;
import com.tsuho.LabWeb2ver2.repository.CommentRepository;
import com.tsuho.LabWeb2ver2.repository.UserRepository;
import com.tsuho.LabWeb2ver2.service.CommentService;
import com.tsuho.LabWeb2ver2.service.DTO.CommentDto;
import com.tsuho.LabWeb2ver2.utils.ValidationUtil;
import com.tsuho.LabWeb2ver2.views.ViewModelComment;
import jakarta.validation.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private ValidationUtil validationUtil;
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ViewModelComment addComment(CommentDto commentDto) {

        if (!this.validationUtil.isValid(commentDto)){
            this.validationUtil
                    .violations(commentDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }
        else {

            Comment comment = modelMapper.map(commentDto, Comment.class);

            // Получаем главу по ID из DTO
            Chapter chapter = chapterRepository.findById(commentDto.getChapterId());
            if (chapter != null) {
                comment.setChapter(chapter);
            } else {

                logger.error("Глава с ID {} не найдена", commentDto.getChapterId());
                return null;
            }

            // Получаем пользователя по ID из DTO
            User user = userRepository.findById(commentDto.getUserId());
            if (user != null) {
                comment.setUser(user); // Устанавливаем связь с пользователем
            } else {

                logger.error("Пользователь с ID {} не найден", commentDto.getUserId());
                return null;
            }

            comment = commentRepository.save(comment);
            return modelMapper.map(comment, ViewModelComment.class);
        }

        logger.error("Что-то пошло не так с созданием коммента!");
        return null;
    }

    @Override
    public ViewModelComment updateComment(Integer commentId, CommentDto commentDto) {
        Comment existingComment = commentRepository.findById(commentId).orElse(null);
        if (existingComment != null) {
            if (commentDto.getContent() != null) {
                existingComment.setContent(commentDto.getContent());
            }
            commentRepository.save(existingComment);
            return modelMapper.map(existingComment, ViewModelComment.class);
        }
        return null;
    }

    @Override
    public List<ViewModelComment> findByChapterId(Integer chapterId) {
        List<Comment> comments = commentRepository.findByChapterId(chapterId);
        return comments.stream().map(comment -> modelMapper.map(comment, ViewModelComment.class)).collect(Collectors.toList());
    }

    @Override
    public List<ViewModelComment> findByUserId(Integer userId) {
        List<Comment> comments = commentRepository.findByUserId(userId);
        return comments.stream().map(comment -> modelMapper.map(comment, ViewModelComment.class)).collect(Collectors.toList());
    }
}
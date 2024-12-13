package com.tsuho.LabWeb2ver2.config;

import com.tsuho.LabWeb2ver2.model.*;
import com.tsuho.LabWeb2ver2.repository.ChapterRepository;
import com.tsuho.LabWeb2ver2.repository.GenreRepository;
import com.tsuho.LabWeb2ver2.repository.NovelRepository;
import com.tsuho.LabWeb2ver2.repository.UserRepository;
import com.tsuho.LabWeb2ver2.service.DTO.ChapterDto;
import com.tsuho.LabWeb2ver2.service.DTO.CommentDto;
import com.tsuho.LabWeb2ver2.service.DTO.NovelDto;
import com.tsuho.LabWeb2ver2.views.ViewModelChapter;
import com.tsuho.LabWeb2ver2.views.ViewModelComment;
import com.tsuho.LabWeb2ver2.views.ViewModelListNovels;
import com.tsuho.LabWeb2ver2.views.ViewModelNovel;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ModelMappingConfig {

    @Autowired
    private NovelRepository novelRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<NovelDto, Novel>() {

            @Override
            protected void configure() {
                using(context -> {
                    String authorName = (String) context.getSource();
                    return userRepository.findByName(authorName);
                }).map(source.getAuthorName(), destination.getUser());

                using(context -> {
                    List<String> genreNames = (List<String>) context.getSource();
                    List<Genre> genres = new ArrayList<>();
                    for (String genreName : genreNames) {
                        Genre genre = genreRepository.findByName(genreName);
                        genres.add(genre);
                    }
                    return genres;
                }).map(source.getGenres(), destination.getGenres());
            }
        });

        modelMapper.addMappings(new PropertyMap<Novel, ViewModelNovel>() {
            @Override
            protected void configure() {
                // Маппинг имени автора
                map().setNameAuthor(source.getName());

                // Маппинг списка жанров
                using(context -> {
                    List<Genre> genres = (List<Genre>) context.getSource();
                    List<String> genreNames = new ArrayList<>();
                    for (Genre genre : genres) {
                        genreNames.add(genre.getName());
                    }
                    return genreNames;
                }).map(source.getGenres(), destination.getGenres());

                // Маппинг списка глав
                using(context -> {
                    List<Chapter> chapters = (List<Chapter>) context.getSource();
                    List<ViewModelChapter> viewModelChapters = new ArrayList<>();
                    for (Chapter chapter : chapters) {
                        // Преобразуем каждую главу в вью-модель
                        ViewModelChapter viewModelChapter = new ViewModelChapter();
                        viewModelChapter.setName(chapter.getName());
                        viewModelChapter.setContent(chapter.getContent());
                        viewModelChapter.setPublicationDate(chapter.getPublicationDate());

                        // Если у вас есть комментарии, вы можете также маппировать их
                        // Пример:
                        List<ViewModelComment> viewModelComments = chapter.getComments().stream()
                                .map(comment -> new ViewModelComment(comment.getUser ().getName(), comment.getContent(), comment.getCommentDate()))
                                .collect(Collectors.toList());
                        viewModelChapter.setComments(viewModelComments);

                        viewModelChapters.add(viewModelChapter);
                    }
                    return viewModelChapters;
                }).map(source.getChapters(), destination.getChapters());
            }
        });

        modelMapper.addMappings(new PropertyMap<Comment, ViewModelComment>() {
            @Override
            protected void configure(){
                map().setUserName(source.getUser().getName());
            }
        });

        modelMapper.addMappings(new PropertyMap<CommentDto, Comment>() {
            @Override
            protected void configure(){
                using(context -> {
                    Integer chapterId = (Integer) context.getSource();
                    return chapterRepository.findById(chapterId);

                }).map(source.getChapterId(), destination.getChapter());

                using(context -> {
                    Integer userId = (Integer) context.getSource();
                    return userRepository.findById(userId);
                }).map(source.getUserId(), destination.getUser());
            }
        });

        modelMapper.addMappings(new PropertyMap<Novel, ViewModelListNovels>() {
            @Override
            protected void configure(){
                map().setTitle(source.getName());
            }
        });

        modelMapper.addMappings(new PropertyMap<ChapterDto, Chapter>() {
            @Override
            protected void configure(){
                using(context -> {
                   String novelTitle = (String) context.getSource();
                   return novelRepository.findByName(novelTitle);

                }).map(source.getNovelTitle(), destination.getNovel());
            }
        });

        modelMapper.addMappings(new PropertyMap<Chapter, ViewModelChapter>() {
            @Override
            protected void configure(){
                using(context -> {
                    List<Comment> comments = (List<Comment>) context.getSource();
                    List<ViewModelComment> list = new ArrayList<>();
                    for(Comment comm : comments){
                        list.add(modelMapper.map(comm, ViewModelComment.class));
                    }

                    return list;
                }).map(source.getComments(), destination.getComments());
            }
        });

        return modelMapper;
    }

}

package com.tsuho.LabWeb2ver2.service.impl;

import com.tsuho.LabWeb2ver2.model.Author;
import com.tsuho.LabWeb2ver2.model.Genre;
import com.tsuho.LabWeb2ver2.model.Novel;
import com.tsuho.LabWeb2ver2.repository.AuthorRepository;
import com.tsuho.LabWeb2ver2.repository.GenreRepository;
import com.tsuho.LabWeb2ver2.repository.NovelRepository;
import com.tsuho.LabWeb2ver2.service.DTO.NovelDto;
import com.tsuho.LabWeb2ver2.service.NovelService;
import com.tsuho.LabWeb2ver2.utils.ValidationUtil;
import com.tsuho.LabWeb2ver2.views.ViewModelListNovels;
import com.tsuho.LabWeb2ver2.views.ViewModelNovel;
import jakarta.validation.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NovelServiceImpl implements NovelService {

    private static final Logger logger = LogManager.getLogger(NovelServiceImpl.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private NovelRepository novelRepository;

    @Autowired
    private ValidationUtil validationUtil;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ViewModelNovel addNovel(NovelDto novelDto) {
        if (!this.validationUtil.isValid(novelDto)){
            this.validationUtil
                    .violations(novelDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }
        else {
            Novel novel = this.modelMapper.map(novelDto, Novel.class);

            // Получение автора по имени
            Author author = authorRepository.findByName(novelDto.getAuthorName());
            if (author != null) {
                novel.setAuthor(author);
            } else {
                logger.error("Автор с именем {} не найден", novelDto.getAuthorName());
                return null; // или выбросьте исключение
            }

            // Получение жанров и установка их в новеллу
            List<Genre> genres = new ArrayList<>();
            for (String genreName : novelDto.getGenres()) {
                Genre genre = genreRepository.findByName(genreName);
                if (genre != null) {
                    genres.add(genre);
                    logger.info("Добавлен жанр: {}", genre.getName());
                } else {
                    logger.warn("Жанр с именем {} не найден", genreName);
                }
            }
            novel.setGenres(genres);
            this.novelRepository.saveAndFlush(novel);
            // Верните созданную новеллу в нужном формате
            return this.modelMapper.map(novel, ViewModelNovel.class);
        }

        logger.error("Что-то пошло не так с созданием новеллы!");
        return null;
    }

    @Override
    public ViewModelNovel findByName(String title) {
       Novel novel = novelRepository.findByName(title);
        if (novel== null) {
            logger.error("Новелла с таким именем не найден");
            return null;
        }
        //Можно добавить скрытие емейл
        return new ViewModelNovel(novel.getName(), novel.getDescription(), novel.getAuthor().getName(), novel.getGenres(), novel.getStatus(),novel.getCoverImage(), novel.getPublicationDate(), novel.getChapters());
    }

    @Override
    public List<ViewModelListNovels> findByAuthor(String nameAuthor) {

        Author author = authorRepository.findByName(nameAuthor);

        List<Novel> novel = novelRepository.findByAuthor(author);

        if (novel== null) {
            logger.error("Новеллы от этого автора не найдены");
            return null;
        }
        List<ViewModelListNovels> viewModelList = new ArrayList<>();

        for (Novel value : novel) {
            viewModelList.add(new ViewModelListNovels(value.getName(), value.getStatus(), value.getAuthor().getName(), value.getCoverImage()));
        }

        return  viewModelList;
    }

    public List<ViewModelListNovels> findByGenre(String name){
        Genre genre = genreRepository.findByName(name);
        //Стоит ли тут сразу имя кидать?
        List<Novel> novels = novelRepository.findByGenres(genre);
        List<ViewModelListNovels> viewModelList = new ArrayList<>();
        for (Novel value : novels) {
            viewModelList.add(new ViewModelListNovels(value.getName(), value.getStatus(), value.getAuthor().getName(), value.getCoverImage()));
        }

        return  viewModelList;
    }
}

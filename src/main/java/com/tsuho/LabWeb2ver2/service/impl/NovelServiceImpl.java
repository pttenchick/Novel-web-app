package com.tsuho.LabWeb2ver2.service.impl;

import com.tsuho.LabWeb2ver2.model.Genre;
import com.tsuho.LabWeb2ver2.model.Novel;
import com.tsuho.LabWeb2ver2.model.User;
import com.tsuho.LabWeb2ver2.repository.GenreRepository;
import com.tsuho.LabWeb2ver2.repository.NovelRepository;
import com.tsuho.LabWeb2ver2.repository.UserRepository;
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
    private UserRepository userRepository;

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
            this.novelRepository.saveAndFlush(novel);
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

        return this.modelMapper.map(novel, ViewModelNovel.class);
    }

    @Override
    public List<ViewModelListNovels> findByAuthor(String nameAuthor) {

        User user = userRepository.findByName(nameAuthor);
        List<Novel> novels = novelRepository.findByUser(user);

        if (novels == null) {
            logger.error("Новеллы от этого автора не найдены");
            return null;
        }
        List<ViewModelListNovels> viewModelList = new ArrayList<>();

        for (Novel novel: novels) {
            viewModelList.add(this.modelMapper.map(novel, ViewModelListNovels.class));
        }
        return viewModelList;
    }

    public List<ViewModelListNovels> findByGenre(String name){
        Genre genre = genreRepository.findByName(name);
        List<Novel> novels = novelRepository.findByGenres(genre);
        List<ViewModelListNovels> viewModelList = new ArrayList<>();

        for (Novel novel: novels) {
            viewModelList.add(this.modelMapper.map(novel, ViewModelListNovels.class));
        }
        return viewModelList;
    }
}

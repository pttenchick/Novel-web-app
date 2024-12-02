package com.tsuho.LabWeb2ver2.service;

import com.tsuho.LabWeb2ver2.service.DTO.NovelDto;
import com.tsuho.LabWeb2ver2.views.ViewModelListNovels;
import com.tsuho.LabWeb2ver2.views.ViewModelNovel;

import java.util.List;

public interface NovelService {

    ViewModelNovel addNovel(NovelDto novelDto);

    ViewModelNovel findByName(String title);

    List<ViewModelListNovels> findByAuthor(String nameAuthor);

    List<ViewModelListNovels> findByGenre(String name);
}
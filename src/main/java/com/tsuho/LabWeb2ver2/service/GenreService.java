package com.tsuho.LabWeb2ver2.service;

import com.tsuho.LabWeb2ver2.service.DTO.GenreDto;
import com.tsuho.LabWeb2ver2.views.ViewModelGenre;

public interface GenreService {

    void addGenre(GenreDto genreDto);

    ViewModelGenre findGenreByName(String name);

}

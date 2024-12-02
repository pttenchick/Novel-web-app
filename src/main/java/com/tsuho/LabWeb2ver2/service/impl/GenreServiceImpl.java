package com.tsuho.LabWeb2ver2.service.impl;

import com.tsuho.LabWeb2ver2.model.Genre;
import com.tsuho.LabWeb2ver2.views.ViewModelGenre;
import jakarta.validation.ConstraintViolation;
import com.tsuho.LabWeb2ver2.repository.GenreRepository;
import com.tsuho.LabWeb2ver2.service.DTO.GenreDto;
import com.tsuho.LabWeb2ver2.service.GenreService;
import com.tsuho.LabWeb2ver2.utils.ValidationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    private static final Logger logger = LogManager.getLogger(GenreServiceImpl.class);

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ValidationUtil validationUtil;

    @Override
    public void addGenre(GenreDto genreDto) {
        if (!this.validationUtil.isValid(genreDto)) {
            this.validationUtil
                    .violations(genreDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return; // Если валидация не прошла, выходим из метода
        }

        Genre genre = new Genre();
        genre.setName(genreDto.getName());
        genre.setDescription(genreDto.getDescription());

        // Проверяем на уникальность названия жанра
        if (genreRepository.findByName(genre.getName()) != null) {
            logger.error("Жанр с именем {} уже существует", genre.getName());
            return; // или выбросьте исключение
        }

        genreRepository.save(genre);
        logger.info("Жанр {} успешно добавлен", genre.getName());
    }

    @Override
    public ViewModelGenre findGenreByName(String name) {

       Genre genre = genreRepository.findByName(name);

        return new ViewModelGenre(genre.getName(), genre.getDescription());
    }
}

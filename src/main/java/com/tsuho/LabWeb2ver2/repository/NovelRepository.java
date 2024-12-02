package com.tsuho.LabWeb2ver2.repository;

import com.tsuho.LabWeb2ver2.model.Author;
import com.tsuho.LabWeb2ver2.model.Genre;
import com.tsuho.LabWeb2ver2.model.Novel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NovelRepository extends JpaRepository<Novel, String> {
    Novel findByName(String name);

    List<Novel> findByAuthor(Author author);

    List<Novel> findByGenres(Genre genre);
}


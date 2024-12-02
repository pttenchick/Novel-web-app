package com.tsuho.LabWeb2ver2.repository;

import com.tsuho.LabWeb2ver2.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, String> {
    Genre findByName(String name);
}


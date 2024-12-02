package com.tsuho.LabWeb2ver2.repository;

import com.tsuho.LabWeb2ver2.model.Author;
import com.tsuho.LabWeb2ver2.views.ViewModelUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {
    Author findByName(String name);
}

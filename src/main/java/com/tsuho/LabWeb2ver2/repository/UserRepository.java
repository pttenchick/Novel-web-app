package com.tsuho.LabWeb2ver2.repository;

import com.tsuho.LabWeb2ver2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByName(String name);

    User findById(int id);
}


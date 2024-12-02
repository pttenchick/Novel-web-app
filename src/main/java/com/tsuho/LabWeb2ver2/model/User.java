package com.tsuho.LabWeb2ver2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    //Связь проверена // Каскад сделан
    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    //Связь проверена // Каскад сделан
    @OneToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "role", nullable = false, length = 10)
    private String role;


}

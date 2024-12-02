package com.tsuho.LabWeb2ver2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "authors")
public class Author  {

    private String name;
    private String biography;
    private String profilePicture;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id")
    private Integer id;

    //Связь проверена // Каскад сделан
    @OneToOne(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;
    //Связь проверена
    @OneToMany(mappedBy = "author")
    private List<Novel> novels = new ArrayList<>();

    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    @Column(name = "biography", columnDefinition = "text")
    public String getBiography() {
        return biography;
    }

    @Column(name = "profile_picture", length = 255)
    public String getProfilePicture() {
        return profilePicture;
    }

}
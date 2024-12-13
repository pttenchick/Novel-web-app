package com.tsuho.LabWeb2ver2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "novels")
public class Novel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "novel_id")
    private Integer id;

    //Связь проверена
    @OneToMany(mappedBy = "novelsForGenre")
    private List<Genre> genres = new ArrayList<>();

    //Связь проверена // Каскад не нужен
    @OneToMany(mappedBy = "novel")
    private List<Chapter> chapters = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "cover_image", length = 255)
    private String coverImage;

    @Column(name = "publication_date", length = 20)
    private String publicationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private Status status;

}

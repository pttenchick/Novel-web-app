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

    private String name;
    private String description;
    private String coverImage;
    private String publicationDate;
    private String status;

    //Связь проверена
    @ManyToMany
    @JoinTable(name = "novels_genres",
            joinColumns = @JoinColumn(name = "novelId"),
            inverseJoinColumns = @JoinColumn(name = "genreId"))
    private List<Genre> genres = new ArrayList<>();

    //Связь проверена // Каскад не нужен
    @OneToMany(mappedBy = "novel")
    private List<Chapter> chapters = new ArrayList<>();

    @Column(name = "title", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    //Связь проверена
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "author_id")
    public Author author;

    @Column(name = "cover_image", length = 255)
    public String getCoverImage() {
        return coverImage;
    }

    @Column(name = "publication_date", length = 20)
    public String getPublicationDate() {
        return publicationDate;
    }

    @Column(name = "status", length = 50)
    public String getStatus() {
        return status;
    }




}

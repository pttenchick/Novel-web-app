package com.tsuho.LabWeb2ver2.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "chapters")
public class Chapter{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chapter_id")
    private Integer id;

    //Связь проверена Каскад не нужен
    @OneToMany(mappedBy = "chapter")
    private List<Comment> comments = new ArrayList<>();

    //Связь проверена // Каскад сделан
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "novel_id")
    private Novel novel;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    @Column(name = "chapter_number", nullable = false)
    private Integer chapterNumber;

    @Column(name = "publication_date")
    private String publicationDate;

}



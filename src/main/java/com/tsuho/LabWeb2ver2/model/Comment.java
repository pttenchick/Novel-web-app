package com.tsuho.LabWeb2ver2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Integer id;

//СВязь проверена // Каскад сделан
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chapter_id") // Вместо chapters
    private Chapter chapter; // Теперь поле типа Chapter

    //Связь проверена // Каскад сделан
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id") // Вместо users
    private User user; // Теперь поле типа User

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "comment_date", nullable = false)
    private String commentDate;

}

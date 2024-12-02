package com.tsuho.LabWeb2ver2.model;
/*
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Entity
@Table(name = "genre_novels")
public class GenreNovel {

    private Integer novelsId;
    private Integer genresId;
    private Novel novel; // Ссылка на сущность Novel
    private Genre genre; // Ссылка на сущность Genre

    @Id
    @ManyToOne
    @JoinColumn(name = "novels_id", referencedColumnName = "novel_id", nullable = false)
    public Novel getNovel() {
        return novel;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "genres_id", referencedColumnName = "genre_id", nullable = false)
    public Genre getGenre() {
        return genre;
    }

    @Column(name = "novels_id", insertable = false, updatable = false)
    public Integer getNovelsId() {
        return novelsId;
    }

    @Column(name = "genres_id", insertable = false, updatable = false)
    public Integer getGenresId() {
        return genresId;
    }
}

*/
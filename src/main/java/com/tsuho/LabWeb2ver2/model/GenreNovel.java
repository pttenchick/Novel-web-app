package com.tsuho.LabWeb2ver2.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Entity
@Table(name = "genres_novels")
public class GenreNovel {

    @Id
    @ManyToOne
    @JoinColumn(name = "novels_id")
    private Novel novelsForGenre;

    @Id
    @ManyToOne
    @JoinColumn(name = "genres_id")
    private Genre genresForNovel;

}

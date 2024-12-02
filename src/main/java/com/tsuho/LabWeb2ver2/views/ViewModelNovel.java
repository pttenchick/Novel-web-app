package com.tsuho.LabWeb2ver2.views;


import com.tsuho.LabWeb2ver2.model.Chapter;
import com.tsuho.LabWeb2ver2.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewModelNovel {

    String name;
    String descriptions;
    String nameAuthor;
    List<Genre> genres;
    String status;
    String coverImage;
    String publicationDate;
    List<Chapter> chapters;

}

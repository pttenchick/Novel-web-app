package com.tsuho.LabWeb2ver2.views;


import lombok.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ViewModelChapter {

    private String name;
    private String content;
    private String publicationDate;
    private List<ViewModelComment> comments;
}
package com.tsuho.LabWeb2ver2.views;

import com.tsuho.LabWeb2ver2.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
public class ViewModelChapter {

    private String name;
    private String content;
    private String publicationDate;
    private List<Comment> comments;
}
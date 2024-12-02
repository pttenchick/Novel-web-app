package com.tsuho.LabWeb2ver2.views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ViewModelComment {

    private String content;
    private Integer chapterId;
    private Integer userId;
    private String commentDate;
}
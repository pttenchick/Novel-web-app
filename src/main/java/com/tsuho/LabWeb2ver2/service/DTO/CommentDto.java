package com.tsuho.LabWeb2ver2.service.DTO;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private String content;
    private Integer chapterId;
    private Integer userId;
    private String commentDate;
}
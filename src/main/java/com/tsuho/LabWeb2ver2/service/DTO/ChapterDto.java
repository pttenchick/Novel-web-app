package com.tsuho.LabWeb2ver2.service.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChapterDto {
    private Integer id;
    private String name;
    private String content;
    private String publicationDate;
    private String novelTitle;
}
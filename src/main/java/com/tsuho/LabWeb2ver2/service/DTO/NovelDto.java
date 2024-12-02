package com.tsuho.LabWeb2ver2.service.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@NoArgsConstructor
@Getter
public class NovelDto {

    @NotNull(message = "Поле не должно быть пустым!")
    String name;
    String publicationDate;
    String status;
    String authorName;
    List<String> genres;
}

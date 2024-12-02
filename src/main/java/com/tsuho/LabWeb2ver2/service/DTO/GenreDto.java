package com.tsuho.LabWeb2ver2.service.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {

    @NotBlank(message = "Имя жанра не должно быть пустым")
    @Size(max = 255, message = "Имя жанра не должно превышать 255 символов")
    private String name;

    private String description;

}
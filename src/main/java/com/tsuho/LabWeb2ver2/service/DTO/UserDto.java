package com.tsuho.LabWeb2ver2.service.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    @NotNull(message = "Поле не должно быть пустым!")
    private String name;
    private String email;
    private String passwordHash;
    private String role;

}

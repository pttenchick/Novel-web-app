package com.tsuho.LabWeb2ver2.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ViewModelUser {

    Integer id;
    String name;
    String email;

    public ViewModelUser(Integer id, String name){
        this.email= "";
        this.id = id;
        this.name = name;
    }
}

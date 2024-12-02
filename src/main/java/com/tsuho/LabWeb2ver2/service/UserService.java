package com.tsuho.LabWeb2ver2.service;

import com.tsuho.LabWeb2ver2.model.User;
import com.tsuho.LabWeb2ver2.service.DTO.UserDto;
import com.tsuho.LabWeb2ver2.views.ViewModelUser;


public interface UserService {

    void addUser(UserDto userDto);

    ViewModelUser findByName(String name);

    ViewModelUser getById(int id);

}

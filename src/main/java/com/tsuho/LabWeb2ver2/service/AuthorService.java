package com.tsuho.LabWeb2ver2.service;

import com.tsuho.LabWeb2ver2.views.ViewModelUser;

public interface AuthorService {

    //void addAuthor(String displayName);

    ViewModelUser findByName(String name);
}

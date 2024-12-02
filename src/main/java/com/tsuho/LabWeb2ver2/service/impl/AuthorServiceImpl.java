package com.tsuho.LabWeb2ver2.service.impl;

import com.tsuho.LabWeb2ver2.model.Author;
import com.tsuho.LabWeb2ver2.repository.AuthorRepository;
import com.tsuho.LabWeb2ver2.service.AuthorService;
import com.tsuho.LabWeb2ver2.utils.ValidationUtil;
import com.tsuho.LabWeb2ver2.views.ViewModelUser;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private static final Logger logger = LogManager.getLogger(AuthorServiceImpl.class);

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ValidationUtil validationUtil;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ViewModelUser findByName(String name) {
        Author author = authorRepository.findByName(name);

        if(author == null){
            logger.error("Автор с таким именем не найден");
            return null;
        }

        return new ViewModelUser(author.getId(), author.getName());
    }

}

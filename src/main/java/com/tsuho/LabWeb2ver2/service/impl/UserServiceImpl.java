package com.tsuho.LabWeb2ver2.service.impl;

import com.tsuho.LabWeb2ver2.model.Author;
import com.tsuho.LabWeb2ver2.model.User;
import com.tsuho.LabWeb2ver2.repository.AuthorRepository;
import com.tsuho.LabWeb2ver2.repository.UserRepository;
import com.tsuho.LabWeb2ver2.service.DTO.UserDto;
import com.tsuho.LabWeb2ver2.service.UserService;
import com.tsuho.LabWeb2ver2.utils.ValidationUtil;
import com.tsuho.LabWeb2ver2.views.ViewModelUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.validation.ConstraintViolation;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ValidationUtil validationUtil;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void addUser(UserDto userDto) {

        if (!this.validationUtil.isValid(userDto)){
            this.validationUtil
                    .violations(userDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }
        else {
            User user = this.modelMapper.map(userDto, User.class);
//
//            Author author = new Author();
//            author.setName(userDto.getName());
//            author.setUser(user);
//            author.setId(user.getId());
//            authorRepository.save(author);
//
//            user.setAuthor(author);

            this.userRepository.saveAndFlush(user);
        }

    }

    @Override
    public ViewModelUser findByName(String name) {
        User user = userRepository.findByName(name);
        if (user == null) {
            logger.error("Пользователь с таким именем не найден");
            return null;
        }
        //Можно добавить скрытие емейл
        return new ViewModelUser(user.getId(), user.getName(), user.getEmail());

    }

    @Override
    public ViewModelUser getById(int id) {
        User user = userRepository.findById(id);
        if (user == null) {
            logger.error("Пользователь с таким ID не найден");
            return null;
        }
        return new ViewModelUser(user.getId(), user.getName(), user.getEmail());
    }


}

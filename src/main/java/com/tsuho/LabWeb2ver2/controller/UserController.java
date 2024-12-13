package com.tsuho.LabWeb2ver2.controller;


import com.tsuho.LabWeb2ver2.service.UserService;
import com.tsuho.LabWeb2ver2.views.ViewModelUser;
import com.tsuho.LabWeb2ver2.service.DTO.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorService authorService;

    @PostMapping("createUser")
    private ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        this.userService.addUser(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @GetMapping("getUserByName/{name}")
    private ResponseEntity<ViewModelUser> getUserByName(@PathVariable("name") String name) {
        ViewModelUser viewModelUser = this.userService.findByName(name);
        return new ResponseEntity<>(viewModelUser, HttpStatus.OK);
    }

    @GetMapping("getUserById/{id}")
    private ResponseEntity<ViewModelUser> getUserById(@PathVariable("id") int id) {
        ViewModelUser viewModelUser = this.userService.getById(id);
        return new ResponseEntity<>(viewModelUser, HttpStatus.OK);
    }

    @GetMapping("searchAuthorByName/{name}")
    private ResponseEntity<ViewModelUser> findAuthorByName(@PathVariable("name") String name){
        ViewModelUser author = this.authorService.findByName(name);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }
}
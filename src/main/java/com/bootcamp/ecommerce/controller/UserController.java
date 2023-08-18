package com.bootcamp.ecommerce.controller;

import com.bootcamp.ecommerce.model.UserDto;
import com.bootcamp.ecommerce.response.ResponseData;
import com.bootcamp.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @GetMapping()
    public ResponseData<List<UserDto>> getUser(){
        return userService.getUser();
    }

    @PutMapping
    public String putUser(){
        return "ini method put";
    }

    @PostMapping
    public UserDto postUser(@RequestBody UserDto userDto){
        return userService.postUser(userDto);
    }
}

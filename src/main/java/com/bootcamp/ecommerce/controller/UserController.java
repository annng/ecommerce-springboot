package com.bootcamp.ecommerce.controller;

import com.bootcamp.ecommerce.model.UserDto;
import com.bootcamp.ecommerce.response.ResponseData;
import com.bootcamp.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @GetMapping()
    public ResponseEntity<ResponseData<List<UserDto>>> getUser(){
        return userService.getUser();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> putUser(@PathVariable Long id, @Valid @RequestBody UserDto user){
        return userService.updateUser(id, user);
    }

    @PostMapping
    public ResponseEntity<UserDto> postUser(@Valid @RequestBody UserDto userDto){
        return userService.postUser(userDto);
    }
}

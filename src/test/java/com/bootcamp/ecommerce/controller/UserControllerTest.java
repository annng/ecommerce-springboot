package com.bootcamp.ecommerce.controller;


import com.bootcamp.ecommerce.entity.User;
import com.bootcamp.ecommerce.model.UserDto;
import com.bootcamp.ecommerce.repository.UserRepository;
import com.bootcamp.ecommerce.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RequestMapping
public class UserControllerTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    private MockMvc mockMvc;

    @Test
    @Rollback(value = false)
    @Order(1)
    void addUser(){
        User saved = User.builder()
                .id(1L)
                .name("Pengguna")
                .address("Semarang")
                .build();

        when(userRepository.save(any(User.class))).thenReturn(saved);

        ResponseEntity<UserDto> resp = userService.postUser(UserDto.builder()
                .name("Pengguna")
                .address("Semarang")
                .build());

        ResponseEntity<UserDto> expected = new ResponseEntity<>(UserDto.builder()
                .id(1L)
                .name("Pengguna")
                .address("Semarang").build(), HttpStatus.OK);

        Assertions.assertEquals(expected, resp);
    }

    @Test
    @Rollback(value = false)
    @Order(2)
    void getUserById(){
        User saved = User.builder()
                .id(1L)
                .name("Pengguna")
                .address("Semarang")
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(saved));

        ResponseEntity<UserDto> resp = userService.getUser(1L);
        ResponseEntity<UserDto> expected = new ResponseEntity<>(UserDto.builder()
                .id(1L)
                .name("Pengguna")
                .address("Semarang")
                .build(), HttpStatus.OK);
        Assertions.assertEquals(resp, expected);
    }
}

package com.bootcamp.ecommerce.controller;


import com.bootcamp.ecommerce.entity.User;
import com.bootcamp.ecommerce.model.UserDto;
import com.bootcamp.ecommerce.repository.UserRepository;
import com.bootcamp.ecommerce.service.UserService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUserTest() throws Exception{

    }

    @Test
    @Rollback(value = false)
    @Order(1)
    void addUser(){
        User saved = User.builder()
                .id(3L)
                .name("Pengguna")
                .address("Semarang")
                .build();

        when(userRepository.save(Mockito.mock(User.class))).thenReturn(saved);

        ResponseEntity<UserDto> resp = userService.postUser(UserDto.builder().build());
        assertEquals(resp, UserDto.builder().build());
    }

    @Test
    @Rollback(value = false)
    @Order(2)
    void getUserById(){
        User saved = User.builder()
                .id(3L)
                .name("Pengguna")
                .address("Semarang")
                .build();

        when(userRepository.save(Mockito.mock(User.class))).thenReturn(saved);

        ResponseEntity<UserDto> resp = userService.getUser(3L);
        assertEquals(resp, new ResponseEntity<>(UserDto.builder().build(), HttpStatus.OK));
    }
}

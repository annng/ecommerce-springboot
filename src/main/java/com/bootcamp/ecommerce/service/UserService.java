package com.bootcamp.ecommerce.service;

import com.bootcamp.ecommerce.entity.User;
import com.bootcamp.ecommerce.model.UserDto;
import com.bootcamp.ecommerce.repository.UserRepository;
import com.bootcamp.ecommerce.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<UserDto> getUser(Long id) {
        Optional<com.bootcamp.ecommerce.entity.User> userDb = userRepository.findById(id);
        User user = new User();
        if (userDb.isPresent()) {
            user = userDb.get();
        }

        return new ResponseEntity<>(UserDto.builder()
                .id(user.getId())
                .address(user.getAddress())
                .name(user.getName())
                .build(), HttpStatus.OK);
    }

    public ResponseEntity<ResponseData<List<UserDto>>> getUser() {
        ResponseData<List<UserDto>> response = new ResponseData<>();

        List<com.bootcamp.ecommerce.entity.User> userDb = userRepository.findAll();

        List<UserDto> userDtos = userDb.stream().map(item -> UserDto.builder()
                .id(item.getId()).address(item.getAddress()).name(item.getName())
                .build()).collect(Collectors.toList());

        response.setApiMessage("Successfull retrieve data");
        response.setData(userDtos);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<UserDto> updateUser(Long userId, UserDto userDto) {
        Optional<User> userDb = userRepository.findById(userId);
        User user = userDb.get();
        if (userDb.isPresent()) {
            user.setName(userDto.getName());
            user.setAddress(userDto.getAddress());
            userRepository.save(user);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userDto, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<UserDto> postUser(UserDto userDto) {
        User user = User.builder()
                .name(userDto.getName())
                .address(userDto.getAddress())
                .build();

        userRepository.save(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


}

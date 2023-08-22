package com.bootcamp.ecommerce.service;

import com.bootcamp.ecommerce.entity.User;
import com.bootcamp.ecommerce.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Rollback(value = false)
    @Order(1)
    public void createUser(){
        User user = User.builder()
                .name("Mahmud")
                .address("Semarang")
                .build();

        userRepository.save(user);
        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    @Rollback(value = false)
    @Order(2) // will running based on order
    public void getUserById(){
        Optional<User> user = userRepository.findById(1L);

        Assertions.assertThat(user.get().getId()).isEqualTo(1L);
    }

    @Test
    @Rollback(value = false)
    @Order(3)
    public void updateById(){
        Optional<User> user = userRepository.findById(1L);

        if(user.isPresent()){
            User user1 = user.get();

            user1.setAddress("Jakarta");
            User userUpdated = userRepository.save(user1);

            Assertions.assertThat(userUpdated.getAddress()).isEqualTo("Jakarta");
        }
    }

    @Test
    @Rollback(value = false)
    @Order(4)
    public void deleteUserById(){
        Optional<User> user = userRepository.findById(1L);

        if(user.isPresent()) {
            userRepository.deleteById(1L);
        }

        Optional<User> deleteUser = userRepository.findById(1L);

        assertEquals(deleteUser, Optional.empty());
    }
}
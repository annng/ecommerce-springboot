package com.bootcamp.ecommerce.service;

import com.bootcamp.ecommerce.entity.Product;
import com.bootcamp.ecommerce.entity.User;
import com.bootcamp.ecommerce.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
public class ProductServiceTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @Rollback(value = false)
    @Order(1)
    public void createUser(){
        Product product = Product.builder()
                .title("Sample Product")
                .images("sample-image.jpg")
                .description("This is a sample product description.")
                .price(1000L)
                .build();


        productRepository.save(product);
        Assertions.assertThat(product.getId()).isGreaterThan(0);
    }

    @Test
    @Rollback(value = false)
    @Order(2)
    public void getProductById(){
        Optional<Product> product = productRepository.findById(1L);

        Assertions.assertThat(product.get().getId()).isEqualTo(1L);
    }
}

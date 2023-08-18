package com.bootcamp.ecommerce.controller;

import com.bootcamp.ecommerce.model.CartDto;
import com.bootcamp.ecommerce.model.ProductDto;
import com.bootcamp.ecommerce.model.UserDto;
import com.bootcamp.ecommerce.response.ResponseData;
import com.bootcamp.ecommerce.service.CartService;
import com.bootcamp.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/user/{id}")
    public List<CartDto> getCart(@PathVariable Long id){
        return cartService.getCarts(id);
    }

    @PostMapping()
    public CartDto addCart(Long productId, Long userId, Integer qty){
        return cartService.addCart(productId, userId, qty);
    }

    @DeleteMapping("delete/{id}")
    public ResponseData<CartDto> deleteCart(@PathVariable Long id){
        return cartService.deleteCart(id);
    }
}

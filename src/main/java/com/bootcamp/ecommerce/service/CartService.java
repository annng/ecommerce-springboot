package com.bootcamp.ecommerce.service;

import com.bootcamp.ecommerce.entity.Cart;
import com.bootcamp.ecommerce.entity.Product;
import com.bootcamp.ecommerce.entity.User;
import com.bootcamp.ecommerce.model.CartDto;
import com.bootcamp.ecommerce.model.ProductDto;
import com.bootcamp.ecommerce.repository.CartRepository;
import com.bootcamp.ecommerce.repository.ProductRepository;
import com.bootcamp.ecommerce.repository.UserRepository;
import com.bootcamp.ecommerce.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<List<CartDto>> getCarts(Long userId) {

        List<Cart> cartDb = cartRepository.getCartByUserId(userId);

        List<CartDto> carts = new ArrayList<>();

        for (Cart item : cartDb) {
            ProductDto product = ProductDto.builder()
                    .id(item.getProduct().getId())
                    .title(item.getProduct().getTitle())
                    .images(item.getProduct().getImages())
                    .description(item.getProduct().getDescription())
                    .build();

            carts.add(CartDto.builder()
                    .id(item.getId())
                    .product(product)
                    .qty(item.getQty())
                    .build());
        }


        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    public ResponseEntity<CartDto> addCart(Long productId, Long userId, Integer qty) {

        //find product data by productId
        Optional<Product> productDb = productRepository.findById(productId);
        Product product = new Product();

        Optional<User> userDb = userRepository.findById(userId);
        User user = new User();
        Cart cart = new Cart();

        if (productDb.isPresent() && userDb.isPresent()) {
            product = productDb.get();
            user = userDb.get();

            cart = Cart.builder()
                    .user(user)
                    .qty(qty)
                    .product(product)
                    .build();

            cartRepository.save(cart);
        }


        //convert entity to model
        ProductDto productDto = ProductDto.builder()
                .id(productId)
                .title(product.getTitle())
                .description(product.getDescription())
                .images(product.getImages())
                .build();

        return new ResponseEntity<>(CartDto.builder()
                .id(cart.getId())
                .userId(userId)
                .qty(cart.getQty())
                .product(productDto)
                .build(), HttpStatus.OK);
    }

    public ResponseEntity<ResponseData<CartDto>> deleteCart(Long cartId) {


        ResponseData<CartDto> response = new ResponseData<>();
        //find product data by productId
        Optional<Cart> productDb = cartRepository.findById(cartId);
        Cart product = new Cart();

        if (productDb.isPresent()) {
            cartRepository.deleteById(cartId);
            response.setApiMessage("Cart successfully deleted");
            response.setData(null);
        } else {
            response.setApiMessage("Data not found");
            response.setData(null);
        }


        return new ResponseEntity(response, HttpStatus.OK);
    }


}

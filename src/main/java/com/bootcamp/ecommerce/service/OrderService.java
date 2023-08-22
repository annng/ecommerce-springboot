package com.bootcamp.ecommerce.service;

import com.bootcamp.ecommerce.entity.Order;
import com.bootcamp.ecommerce.entity.Product;
import com.bootcamp.ecommerce.model.OrderDto;
import com.bootcamp.ecommerce.model.ProductDto;
import com.bootcamp.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public ResponseEntity<List<OrderDto>> getOrders() {
        List<Order> order = orderRepository.findAll();

        return new ResponseEntity<>(order.stream().map(item -> OrderDto.builder().id(item.getId())
                        .title(item.getProductTitle())
                        .images(item.getProductImages())
                        .description(item.getProductDescription())
                        .qty(item.getQty())
                        .price(item.getPrice())
                        .build())
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    public ResponseEntity<OrderDto> getOrder(Long id) {
        Optional<Order> productDb = orderRepository.findById(id);
        Order order = new Order();
        if (productDb.isPresent()) {
            order = productDb.get();
        }

        return new ResponseEntity<>(OrderDto.builder()
                .id(order.getId())
                .title(order.getProductTitle())
                .images(order.getProductImages())
                .description(order.getProductDescription())
                .qty(order.getQty())
                .price(order.getPrice())
                .build(), HttpStatus.OK);
    }

    public ResponseEntity<OrderDto> putProduct(OrderDto orderDto) {
        Order order = Order.builder()
                .productTitle(orderDto.getTitle())
                .productImages(orderDto.getImages())
                .productDescription(orderDto.getDescription())
                .qty(orderDto.getQty())
                .price(orderDto.getPrice())
                .build();

        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }


}

package com.bootcamp.ecommerce.controller;

import com.bootcamp.ecommerce.model.OrderDto;
import com.bootcamp.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/{id}")
    public OrderDto getOrderDetail(@PathVariable Long id){
        return orderService.getOrder(id);
    }


    @GetMapping()
    public List<OrderDto> getOrders(){
        return orderService.getOrders();
    }

}

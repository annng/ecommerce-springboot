package com.bootcamp.ecommerce.controller;

import com.bootcamp.ecommerce.entity.Product;
import com.bootcamp.ecommerce.model.ProductDto;
import com.bootcamp.ecommerce.model.UserDto;
import com.bootcamp.ecommerce.response.ResponseData;
import com.bootcamp.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseData<ProductDto> getProductDetail(@PathVariable Long id){
        return productService.getProduct(id);
    }


    @GetMapping()
    public List<ProductDto> getProducts(){
        return productService.getProducts();
    }

    @PostMapping
    public ProductDto addProducts(@Valid @RequestBody ProductDto product){
        return productService.postProduct(product);
    }

    @PutMapping("/{id}")
    public ProductDto updateProducts(@PathVariable Long id, @RequestBody ProductDto product){
        return productService.putProduct(id, product);
    }

}

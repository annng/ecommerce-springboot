package com.bootcamp.ecommerce.service;

import com.bootcamp.ecommerce.entity.Product;
import com.bootcamp.ecommerce.model.ProductDto;
import com.bootcamp.ecommerce.repository.ProductRepository;
import com.bootcamp.ecommerce.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<ProductDto> getProducts() {
        List<Product> productDb = productRepository.findAll();
        List<ProductDto> products = new ArrayList<>();

        for (Product item : productDb) {
            products.add(ProductDto.builder()
                    .id(item.getId())
                    .title(item.getTitle())
                    .images(item.getImages())
                    .description(item.getDescription())
                    .build());
        }

        return products;
    }

    public ResponseData<ProductDto> getProduct(Long id) {
        ResponseData<ProductDto> response = new ResponseData<>();


        Optional<Product> productDb = productRepository.findById(id);

        Product product = new Product();
        if (productDb.isPresent()) {
            product = productDb.get();


            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .title(product.getTitle())
                    .images(product.getImages())
                    .description(product.getDescription())
                    .build();

            response.setApiMessage("Data Successfully to show");
            response.setData(productDto);
        } else {
            response.setApiMessage("Data is Empty");
            response.setData(null);
        }

        return response;
    }

    public ProductDto postProduct(ProductDto productDto) {

        Product product = Product.builder()
                .title(productDto.getTitle())
                .images(productDto.getImages())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .build();

        productRepository.save(product);
        return productDto;
    }

    public ProductDto putProduct(Long idProduct, ProductDto productDto) {
        Optional<Product> productDb = productRepository.findById(idProduct);

        if (productDb.isPresent()) {

            Product product = productDb.get();

            product.setTitle(productDto.getTitle());
            product.setImages(productDto.getImages());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());

            productRepository.save(product);
        }
        return productDto;
    }


}

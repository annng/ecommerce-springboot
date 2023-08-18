package com.bootcamp.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(nullable = false)
    @Size(min = 3, max = 100)
    private String title;

    @Column(nullable = true)
    @Size(max = 255)
    private String images;

    @Column(nullable = true)
    @Size(max = 255)
    private String description;


    @Column(nullable = false)
    @Size(max = 10)
    private Long price;
}

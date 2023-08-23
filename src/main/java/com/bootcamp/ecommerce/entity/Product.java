package com.bootcamp.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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


    @Column()
    @NotNull
    @Size(min = 5, max = 100)
    private String title;

    @Column()
    @Size(max = 255)
    private String images;

    @Column()
    @Size(max = 255)
    private String description;


    @Column()
    @NotNull
    @Size(max = 10)
    private Long price;
}
